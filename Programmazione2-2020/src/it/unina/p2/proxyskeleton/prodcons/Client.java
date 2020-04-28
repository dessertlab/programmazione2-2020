package it.unina.p2.proxyskeleton.prodcons;

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
		executor.awaitTermination(30, TimeUnit.SECONDS);
	}
}

class Worker implements Runnable{

	@Override
	public void run() {
		Random r = new Random();
		for(int i = 0; i < 5; i++){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stub stub = new Stub();
			if(i % 2 == 0){
				int produci = r.nextInt(50) + 1;
				System.out.println("Client manda richiesta produzione : " + produci);
				stub.produci(produci);
			}
			else{
				int consuma = stub.consuma();
				System.out.println("Client manda richiesta e consuma : " + consuma);
			}
		}
		
	}
	
}