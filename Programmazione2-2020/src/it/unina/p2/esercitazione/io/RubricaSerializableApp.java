package it.unina.p2.esercitazione.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

public class RubricaSerializableApp {

	final String rubrica_path = "/Users/rnatella/rubrica.ser"; 
	
	TreeSet<ContattoSerializable> contatti = new TreeSet<ContattoSerializable>();
	
	public RubricaSerializableApp() throws IOException, ClassNotFoundException {
		
		File rubrica_file = new File(rubrica_path);
		
		if(rubrica_file.exists()) {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.rubrica_path)); 

			ContattoSerializable contatto = null;
		
			while( (contatto = (ContattoSerializable)ois.readObject()) != null ) {
			
				contatti.add(contatto);
			}
		
			ois.close(); 
		}

	}
	
	
	public void addContatto(ContattoSerializable contatto) {

		contatti.add(contatto);
		
		try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.rubrica_path)) ) {
			
			for(ContattoSerializable c : contatti) {
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
	
	
	
	public void stampaContatti() {
		
		for(ContattoSerializable contatto : contatti) {
			System.out.println(contatto);
		}
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		RubricaSerializableApp rubrica = new RubricaSerializableApp();
		
		
		rubrica.addContatto(new ContattoSerializable("Roberto", "Natella", 123, "prova@prova.it"));
		rubrica.addContatto(new ContattoSerializable("Roberto", "Pietrantuono", 456, "test@test.it"));
		rubrica.addContatto(new ContattoSerializable("Marcello", "Cinque", "aaa@aaa.it"));
		rubrica.addContatto(new ContattoSerializable("Stefano", "Russo", 789));


		rubrica.stampaContatti();

	}

}
