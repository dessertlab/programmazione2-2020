package it.unina.p2.networking.brokersportello.client;

import it.unina.p2.broker.ClientRequest;
import it.unina.p2.broker.IBroker;
import it.unina.p2.broker.IDClientInvalidoException;

public class ClientWorker extends Thread {

	private IBroker brokerProxy;
	
	public ClientWorker(IBroker brokerProxy) {
		this.brokerProxy = brokerProxy;
	}

	public void run() {
		
		System.out.println("[ClientWorker] Thread avviato");

		for(int i=0; i<3; i++) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int clientID = (((int)(100*Math.random()))% 50) +1;
			int requestID = (((int)(100*Math.random()))% 50) +1;
			
			System.out.println("[ClientWorker] Invio richiesta: "+clientID+", "+requestID);
			
			
			ClientRequest clientreq = new ClientRequest( clientID, requestID );

			try {
				
				int return_val = brokerProxy.inoltraRichiesta(clientreq);
				
				System.out.println("[ClientWorker] Ricevuto "+return_val);
				
			} catch (IDClientInvalidoException e) {
				
				System.err.println("Il tuo ID non è valido");
				e.printStackTrace();
			}
		}
	}
}
