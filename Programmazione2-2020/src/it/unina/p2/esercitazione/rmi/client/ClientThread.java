package it.unina.p2.esercitazione.rmi.client;

import java.io.IOException;

import it.unina.p2.esercitazione.rmi.server.Contatto;
import it.unina.p2.esercitazione.rmi.server.ContattoAlreadyExistent;
import it.unina.p2.esercitazione.rmi.server.ContattoNotFound;
import it.unina.p2.esercitazione.rmi.server.IRubrica;

public class ClientThread extends Thread {

	private IRubrica rubrica;
	private Contatto [] contatti_da_aggiungere;
	private String nome_da_cercare;
	
	public ClientThread(IRubrica rubrica, Contatto [] contatti_da_aggiungere, String nome_da_cercare) {
		this.rubrica = rubrica;
		this.contatti_da_aggiungere = contatti_da_aggiungere;
		this.nome_da_cercare = nome_da_cercare;
	}
	
	public void run() {
		try {
			
			
			for(int i = 0; i<contatti_da_aggiungere.length; i++) {
				
				System.out.println("[Client] Aggiunta contatto: "+contatti_da_aggiungere[i]);
				
				rubrica.aggiungiContatto(contatti_da_aggiungere[i]);
			}
			
			
			try {
				Contatto[] risultati = rubrica.cercaContatto(nome_da_cercare, null);
				
				for(Contatto c : risultati) {
					System.out.println("[Client] Contatti trovati: "+c);
				}
				
			} catch (ContattoNotFound e) {
				
				System.err.println("[Client] Ricerca del contatto fallita");
			}
			
			
			
		} catch(ContattoAlreadyExistent e) {
			
			System.err.println("[Client] Impossibile aggiungere contatto già esistente");
			
		} catch (IOException e) {
			
			System.err.println("[Client] Errore di rete");
			e.printStackTrace();

		}
		
		
		try {
			
			System.out.println("[Client] Totale contatti: "+ rubrica.totaleContatti());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
