package it.unina.p2.esercitazione.networking.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class RubricaImpl implements IRubrica {

	String rubrica_path; 
	
	TreeSet<Contatto> contatti = new TreeSet<Contatto>();
	
	
	
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
		
		
		
		try {
			if(cercaContatto(contatto.getNome(), contatto.getCognome()) != null ) {
					
					throw new ContattoAlreadyExistent();
			}
		} catch (ContattoNotFound e) {
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
	public synchronized Contatto cercaContatto(String nome, String cognome) throws ContattoNotFound {

		if(nome == null && cognome == null)
			throw new ContattoNotFound();
		

		for(Contatto c : contatti) {
			
			if((nome == null || c.getNome().equals(nome)) && 
			   (cognome == null || c.getCognome().equals(cognome) ) ) {
				
				return c;
			}
		}


		throw new ContattoNotFound();
	}

	@Override
	public synchronized int totaleContatti() {

		return contatti.size();
	}

	

}
