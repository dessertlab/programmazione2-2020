package it.unina.p2.esercitazione.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;


public class RubricaThreadedApp {

	final String rubrica_path = "/Users/rnatella/rubrica.ser"; 
	
	TreeSet<Contatto> contatti = new TreeSet<Contatto>();
	
	public RubricaThreadedApp() throws IOException, ClassNotFoundException {
		
		File rubrica_file = new File(rubrica_path);
		
		if(rubrica_file.exists()) {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rubrica_path)); 

			Contatto contatto = null;
		
			while( (contatto = (Contatto)ois.readObject()) != null ) {
			
				contatti.add(contatto);
			}
		
			ois.close(); 
		}

	}
	
	
	public synchronized void addContatto(Contatto contatto) {

		contatti.add(contatto);
		
		try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.rubrica_path)) ) {
			
			for(Contatto c : contatti) {
				oos.writeObject(c);
			}
			
			// Marca la fine della lista dei contatti
			// (alla lettura, consente il test "!= null")
			oos.writeObject(null);
			
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public synchronized void stampaContatti() {
		
		for(Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		RubricaThreadedApp rubrica = new RubricaThreadedApp();
		
		
		Contatto [] contatti = new Contatto[4];
		
		contatti[0] = new Contatto("Roberto", "Natella", 123, "prova@prova.it");
		contatti[1] = new Contatto("Roberto", "Pietrantuono", 456, "test@test.it");
	    contatti[2] = new Contatto("Marcello", "Cinque", "aaa@aaa.it");
		contatti[3] = new Contatto("Stefano", "Russo", 789);
		

		
		Thread [] threads = new Thread[4];
		
		for(int i=0; i<4; i++) {
			threads[i] = new RubricaWorker(rubrica, contatti[i]);
			threads[i].start();
		}
		

		for(int i=0; i<4; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		rubrica.stampaContatti();

	}

}
