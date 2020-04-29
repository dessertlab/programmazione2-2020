package it.unina.p2.esercitazione.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class RubricaCSVApp {

	final String rubrica_path = "/Users/rnatella/rubrica.csv"; 
	
	TreeSet<ContattoCSV> contatti = new TreeSet<ContattoCSV>();
	
	public RubricaCSVApp() throws IOException, ClassNotFoundException {
		
		File rubrica_file = new File(rubrica_path);
		
		if(rubrica_file.exists()) {

			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(this.rubrica_path)));

			String line = null;
			
			while( (line = bis.readLine()) != null ) {
			
				if(line.equals(""))
					continue;
				
				ContattoCSV contatto = new ContattoCSV(line);
								
				contatti.add(contatto);
			}
		
			bis.close(); 
		}

	}
	
	
	public void addContatto(ContattoCSV contatto) {

		contatti.add(contatto);

		try ( BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.rubrica_path))) ) {
			
			for(ContattoCSV c : contatti) {
				bos.write(c.toString());
				bos.write('\n');
			}
			
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public void stampaContatti() {
		
		for(ContattoCSV contatto : contatti) {
			System.out.println(contatto);
		}
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		RubricaCSVApp rubrica = new RubricaCSVApp();
		
		
		rubrica.addContatto(new ContattoCSV("Roberto", "Natella", 123, "prova@prova.it"));
		rubrica.addContatto(new ContattoCSV("Roberto", "Pietrantuono", 456, "test@test.it"));
		rubrica.addContatto(new ContattoCSV("Marcello", "Cinque", "aaa@aaa.it"));
		rubrica.addContatto(new ContattoCSV("Stefano", "Russo", 789));


		rubrica.stampaContatti();

	}

}
