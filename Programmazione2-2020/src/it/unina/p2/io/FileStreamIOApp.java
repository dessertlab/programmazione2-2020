package it.unina.p2.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileStreamIOApp {

	public static void main(String[] args) throws IOException {

		File file = new File("/Users/rnatella/myFile.txt");

		try {
		    // Crea un nuovo file se non esiste 
		    if (file.createNewFile()) 
		        System.out.println("File created"); 
		    else
		        System.out.println("File already exists");
		} catch (IOException x) {    
		    System.err.format("I/O error: %s%n", x.getMessage());
		}

		
		//Operazione di scrittura
		String s = "Testo di prova\n";

		byte data[] = s.getBytes();

		OutputStream out = null;
		try {
		    out = new BufferedOutputStream(new FileOutputStream(file));
		    
		    out.write(data, 0, data.length);
		} catch (IOException x) {
		    System.err.println(x);
		} finally {
		    if (out != null) {
		        out.flush();
		        out.close();
		    }
		}

		
		
		
		//Operazione di lettura
		InputStream in = null;
		try {
			
		    in = new FileInputStream(file);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    String line = null;
		    while ((line = reader.readLine()) != null) 
		        System.out.println(line);
		    
		} catch (IOException x) {
		    System.err.println(x);
		} finally {
		    if (in != null) 
		    	in.close();
		}

	}

}
