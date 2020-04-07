package it.unina.p2.threads;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Example {

	private final static int NUMTHREADS = 10;
	
	public static void main(String[] args) throws InterruptedException {
		Example m = new Example();
		m.arrayOfThreads();
		m.threadPool();
		
		//A simple thread using runnable
		MyThread mt = new MyThread(80085);
		
		Thread thread = new Thread(mt);
		thread.start();
	}
	
	
	
	/***ARRAY OF THREADS USING INHERITANCE 
	 * @throws InterruptedException ***/
	private void arrayOfThreads() throws InterruptedException{
		ExtThread[] threads = new ExtThread[NUMTHREADS];
		for(int i = 0; i < NUMTHREADS; i++){
			threads[i] = new ExtThread(i);
			threads[i].start();
		}
		
		//Await termination of all the threads.
		
		for(int i = 0; i < NUMTHREADS; i++){
			threads[i].join();
		}
	}
	
	/*** Using Executors and thread pools 
	 * @throws InterruptedException ***/
	private void threadPool() throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(NUMTHREADS);
		for(int i = 0; i < NUMTHREADS; i++){
			service.execute(new MyThread(i));
		}
		service.shutdown();
		service.awaitTermination(20, TimeUnit.SECONDS);
	}

}
