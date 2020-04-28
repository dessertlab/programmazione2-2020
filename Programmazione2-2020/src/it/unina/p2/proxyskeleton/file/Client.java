package it.unina.p2.proxyskeleton.file;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0; i < 10; i++){
			executor.execute(new Worker());
		}
		
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}
}

class Worker implements Runnable{

	@Override
	public void run() {
		Random r = new Random();
		int casual = r.nextInt(10);
		if(casual < 5){
			Stub s = new Stub(8150);
			int writing = r.nextInt(100) + 1;
			System.out.println("Generato numero da scrivere : " + writing);
			s.salvaSuFile(writing);
		}
		else{
			Stub s = new Stub(8150);
			System.out.println("Segnalato filePop");
			int value = s.filePop();
			System.out.println("Letto valore: " + value);
		}		
	}
	
}