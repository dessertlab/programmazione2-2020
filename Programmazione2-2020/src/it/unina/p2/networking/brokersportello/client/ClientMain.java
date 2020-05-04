package it.unina.p2.networking.brokersportello.client;

import it.unina.p2.networking.brokersportello.broker.BrokerProxy;
import it.unina.p2.networking.brokersportello.broker.IBroker;

public class ClientMain {

	public static void main(String[] args) {

		
		int num_thread = 3;
		
		
		IBroker brokerProxy = new BrokerProxy();
		
		
		
		Thread [] threads = new Thread[num_thread];
		
		for(int i=0; i<num_thread; i++) {
			
			threads[i] = new ClientWorker(brokerProxy);
			threads[i].start();
		}
		
		
		System.out.println("[ClientMain] Avviati client worker, in attesa...");
		
		for(int i=0; i<num_thread; i++) {
			
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
