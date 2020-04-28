package it.unina.p2.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;


/*
 * Esempio di token.txt

prova
ciao mondo
123.12;444;123ciao

 */

public class StreamTokenApp {

	public static void main(String[] args) throws IOException {

		BufferedReader inData = new BufferedReader(new FileReader("/Users/rnatella/token.txt"));
		
		StreamTokenizer st = new StreamTokenizer(inData);
		
		st.ordinaryChar('.');
		st.ordinaryChar(';');

			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				
				String s=null;
				
				switch(st.ttype) {
				
				case StreamTokenizer.TT_NUMBER:
					System.out.print ( "Number:	");
					s = Double.toString(st.nval);
					
					double d = Double.parseDouble(s);
					
					break;
					
				case StreamTokenizer.TT_WORD:
					System.out.print ( "Word:	");
					s = st.sval; 
					break;
					
					
				default: 
					System.out.print ( "Other:	");
					s = String.valueOf((char)st.ttype); 
					
				}
				
				System.out.println( s );		
			}
			
			inData.close();		
		
	}

}
