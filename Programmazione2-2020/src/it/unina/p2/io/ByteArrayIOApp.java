package it.unina.p2.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayIOApp {

	public static void main(String[] args) throws IOException {

		
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			
			String s = "Questo è un test ...";
			outStream.write(s.getBytes());
			
			System.out.println("Flusso di output "+outStream);
			System.out.println("Dimensione: "+outStream.size());
			
			
			
			
			ByteArrayInputStream inStream;
			
			inStream = new ByteArrayInputStream(outStream.toByteArray());
			
			int inBytes = inStream.available();
			byte inBuf[] = new byte[inBytes];
			
			int bytesRead = inStream.read(inBuf, 0, inBytes);
			
			System.out.println(bytesRead+" byte letti");
			System.out.println("Sono: "+new String(inBuf));
	}

}
