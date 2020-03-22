package it.unina.p2.esercitazione.io;

import java.io.*;
import java.util.*;

public class WordCounterApp {

	public static void main(String[] args) {

		
        try {
        	
        	String filename = "/Users/rnatella/Downloads/divina_commedia.txt";
        	
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            StreamTokenizer tokenizer = new StreamTokenizer(buff);
            
            HashMap<String,Integer> token_counters = new HashMap<String,Integer>(); // 40000
            
            int tot_parole = 0;
            
            long t1, t2;
            
            t1=System.currentTimeMillis();
            
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {

            	if(tokenizer.ttype == StreamTokenizer.TT_WORD) {
            	
            		String token = tokenizer.sval;
            		
            		if(token.length() > 3) {
            			
            			if(!token_counters.containsKey(token)) {
            				
            				token_counters.put(token, 1);
            			}
            			else {
            			
            				int count = token_counters.get(token);
            				count++;
            				token_counters.put(token, count);
            			}
            			
                		tot_parole++;            			
            		}

            	}
            }
            
            t2=System.currentTimeMillis();
            
            System.out.println("Tempo di Elaborazione: "+(t2-t1)+" ms");
            
                        
            for(String token : token_counters.keySet()) {
            	
            	int count = token_counters.get(token);
            	
            	if(count > 10) {
            		System.out.println(token + "\t" + count);
            	}
            }
            
            
            System.out.println("Totale parole: "+tot_parole);
            
            buff.close();
            file.close();
            
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }

	}

}
