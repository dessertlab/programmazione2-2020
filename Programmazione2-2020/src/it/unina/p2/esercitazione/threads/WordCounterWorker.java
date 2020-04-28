package it.unina.p2.esercitazione.threads;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class WordCounterWorker extends Thread {

	private HashMap<String,Integer> token_counters;
	private ArrayBlockingQueue<char[]> vettore_buffer;
	private long iterations;
	
	public WordCounterWorker(ArrayBlockingQueue<char[]> vettore_buffer, HashMap<String,Integer> counters, long iterations) {
		
		this.vettore_buffer = vettore_buffer;
		this.token_counters = counters;
		this.iterations = iterations;
		
	}
	
	public void run(){



		try {

			for( int i=0; i<iterations; i++ ) {
				

				char [] block = vettore_buffer.take();

				
				StringTokenizer tokenizer = new StringTokenizer(new String(block));


				while(tokenizer.hasMoreTokens()) {

					String token = tokenizer.nextToken();

					if(token.length() > 3) {

						
						synchronized(token_counters) {

							if(!token_counters.containsKey(token)) {

								token_counters.put(token, 1);
							}
							else {

								int count = token_counters.get(token);
								count++;
								token_counters.put(token, count);
							}

						}

					}
				}

			}

		} catch (InterruptedException e) {

			System.out.println("Interrupted...");

		}
		
		

		
	}

}

