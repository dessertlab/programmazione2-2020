package it.unina.p2.esercitazione.rmi.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.TreeSet;

import it.unina.p2.esercitazione.rmi.client.ClientCallback;

public class RubricaImpl extends UnicastRemoteObject implements IRubrica {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6048752880104738837L;

	private String rubrica_path; 
	
	private TreeSet<Contatto> contatti = new TreeSet<Contatto>();
	
	private ArrayList<IClientCallback> client_registrati = new ArrayList<>();
	
	
	
	public RubricaImpl(String rubrica_path) throws IOException, ClassNotFoundException {
		
		this.rubrica_path = rubrica_path;
		
		
		File rubrica_file = new File(this.rubrica_path);
		
		if(rubrica_file.exists()) {

			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(this.rubrica_path)));

			String line = null;
			
			while( (line = bis.readLine()) != null ) {
			
				if(line.equals(""))
					continue;
				
				Contatto contatto = new Contatto(line);
								
				contatti.add(contatto);
			}
		
			bis.close(); 
		}

	}
	

	@Override
	public synchronized void aggiungiContatto(Contatto contatto) throws ContattoAlreadyExistent {
		
		System.out.println("[RubricaImpl] Richiesta aggiunta contatto: "+contatto);

		
		/* Prima versione: se il contatto è già presente, solleva una eccezione */
		/* 
		try {
		
			Contatto [] contatti_esistenti = cercaContatto(contatto.getNome(), contatto.getCognome());
		
			if(contatti_esistenti.length > 0) {
					
					System.out.println("[RubricaImpl] Contatto già esistente: "+contatto);
					
					throw new ContattoAlreadyExistent();
			}
		} catch (ContattoNotFound e) {
		}

		*/
		
		
		/* Seconda versione: se il contatto è già presente, lo sovrascrive, e chiama la callback sui client registrati */
		
		for(Contatto c : contatti) {
			
			if(c.getNome().equals(contatto.getNome()) && 
			   c.getCognome().equals(contatto.getCognome()) ) {
				
				
				
				System.out.println("[RubricaImpl] Contatto già esistente: "+c);

				contatti.remove(c);

				
				
				for(IClientCallback client : client_registrati) {
					
					System.out.println("[RubricaImpl] Invocazione callback: "+c);
					
					Thread callback_thread = new CallbackWorker(client, c);
					callback_thread.start();
				}
				
				break;
			}
		}

		
		
		contatti.add(contatto);



		try ( BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.rubrica_path))) ) {
			
			for(Contatto c : contatti) {
				bos.write(c.toString());
				bos.write('\n');
			}
			
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}

	
	
	@Override
	public synchronized Contatto[] cercaContatto(String nome, String cognome) throws ContattoNotFound {

		System.out.println("[RubricaImpl] Ricerca contatto: "+nome+", "+cognome);
		
		
		if(nome == null && cognome == null)
			throw new ContattoNotFound();
		
		
		ArrayList<Contatto> risultati = new ArrayList<>();
		
		boolean found = false;
		

		for(Contatto c : contatti) {
			
			if((nome == null || c.getNome().equals(nome)) && 
			   (cognome == null || c.getCognome().equals(cognome) ) ) {
				
				risultati.add(c);
				found = true;
			}
		}

		if(found == false) {
			throw new ContattoNotFound();
		}
		
		
		System.out.println("[RubricaImpl] Risultati ricerca: "+risultati);
		
		
		Contatto [] risultati_array = new Contatto[risultati.size()];
		risultati_array = risultati.toArray(risultati_array);

		return risultati_array;
	}

	@Override
	public synchronized int totaleContatti() {
		
		System.out.println("[RubricaImpl] Richiesta numero contatti: "+contatti.size());

		return contatti.size();
	}


	@Override
	public synchronized void registraCallback(IClientCallback client) throws RemoteException {
		
		client_registrati.add(client);
		
		System.out.println("[RubricaImpl] Registrazione client callback (totale registrazioni: "+client_registrati.size()+")");
		
	}

	
	@Override
	public synchronized void rimuoviCallback(IClientCallback client) throws RemoteException {

		client_registrati.remove(client);
				
		System.out.println("[RubricaImpl] Rimozione client callback (totale registrazioni: "+client_registrati.size()+")");

	}
	

}
