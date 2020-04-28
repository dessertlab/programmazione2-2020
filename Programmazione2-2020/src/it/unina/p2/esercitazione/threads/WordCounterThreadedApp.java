package it.unina.p2.esercitazione.threads;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

public class WordCounterThreadedApp {
	

	public static void main(String[] args) {

	    HashMap<String,Integer> token_counters = new HashMap<String,Integer>(); // 40000

	    
	    int block_size = 1024;
	    
	    int block_queue_size = 10;
	    
	    int num_threads = 4;
	    
	    

	    System.out.println("Block size: "+block_size);
	    System.out.println("Block queue size: "+block_queue_size);
	    System.out.println("Num threads: "+num_threads);

	    
	    // Vettore di buffer con coda circolare (put/take)
	    ArrayBlockingQueue<char[]> vettore_buffer = new ArrayBlockingQueue<>(block_queue_size);

	    
	    

        long t1, t2;
        
        t1=System.currentTimeMillis();
    

        try {
        	
        	/* APERTURA I/O READERS */
        	
        	String filename = "/Users/rnatella/Downloads/divina_commedia.txt";
        	
        	File file = new File(filename);        	
        	
        	
            FileReader reader = new FileReader(file);
            BufferedReader buff_reader = new BufferedReader(reader);
            
            
            
            
            /* INIZIALIZZAZIONE CONTEGGIO DEL NUMERO DI BLOCCHI */
            
    	    long blocks_total = (long)Math.ceil( file.length() / (double)block_size);
    	    long blocks_per_thread = (long)Math.ceil(blocks_total / (double)num_threads);
    	    
    	    
    	    System.out.println("File size: " + file.length());
    	    System.out.println("Total blocks: " + blocks_total);
    	    
    	    
    	    
            
            
            /* CREAZIONE THREAD */
            
    	    Thread[] threads = new Thread[num_threads];

    	    
    	    int i;
    	    for(i=0; i<num_threads; i++) {
    	    	
    	    	/* Numero di iterazioni per lo i-esimo thread */
    	    	/* Nota: può non essere esattamente uguale per tutti i thread, per via dell'arrotondamento */
    	    	
    	    	long iterations = Math.min(blocks_per_thread, blocks_total);

    	    	blocks_total -= blocks_per_thread;

    	    	
    	    	threads[i] = new WordCounterWorker(vettore_buffer, token_counters, iterations);
    	    	threads[i].start();
    	    	
    	    	System.out.println("Created new thread "+threads[i].getName()+", iterations="+iterations);
    	    	
    	    }
    	    
            
    	    
    	    
    	    /* LETTURA DEL FILE E PRODUZIONE DEI BLOCCHI */
            
    	    char [] block = new char[block_size];

    	        	    
            while( buff_reader.read(block) != -1 ) {
            	
            	vettore_buffer.put(block);
            	
            	
            	/* Nota: ad ogni inserimento, NON bisogna riusare lo stesso array di caratteri,
            	 * altrimenti la read() potrebbe sovrascrivere i dati mentre un thread li sta
            	 * ancora analizzando.
            	 * 
            	 * Per cui, si alloca un NUOVO buffer per ogni blocco da leggere. Si lascia che
            	 * sia l'allocatore di memoria a deallocare i buffer quando i thread ne hanno finito
            	 * l'analisi, e che sia l'allocatore a riusare la memoria dei buffer precedentemente
            	 * allocati.
            	 */
            	block = new char[block_size];
            }
            
            System.out.println("Producer done");

            
            buff_reader.close();
            reader.close();
            
            
            /* ATTESA DELLA TERMINAZIONE DEI THREAD */
            
            for(i=0; i<num_threads; i++) {
            	threads[i].join();
                System.out.println("Consumer done");
            }


            
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Error -- " + e.toString());
        }
        
        
        
        
        
        
        t2=System.currentTimeMillis();
        
        
                    
        for(String token : token_counters.keySet()) {
        	
        	int count = token_counters.get(token);
        	
        	if(count > 10) {
        		System.out.println(token + "\t" + count);
        	}
        }
        
        
        System.out.println("Totale parole: "+token_counters.size());
        System.out.println("Tempo di Elaborazione: "+(t2-t1)+" ms");


	}

}
