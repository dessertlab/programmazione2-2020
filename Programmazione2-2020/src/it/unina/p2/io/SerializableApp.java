package it.unina.p2.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableApp {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		Person john = new Person("John", "uomo", 36);
		
		
		// Create an object output stream to write objects to a file
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("/Users/rnatella/person.ser"));
		
		// Serializes the john object 
		oos.writeObject(john); 

		// Close the object output stream 
		oos.close();

		// Create an object input stream to read objects from a file
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("/Users/rnatella/person.ser")); 

		
		// Read an object from the stream
		Object obj = ois.readObject(); 

		System.out.println(obj);
		
		Person p = null;
		if(obj instanceof Person) {
			p = (Person)obj;
			System.out.println("Oggetto di classe 'Person'");
		}

		// Close the object input stream
		ois.close(); 

	}

}
