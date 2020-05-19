package it.unina.p2.esercitazione.rmi.server;

import java.rmi.RemoteException;

public class CallbackWorker extends Thread {

	private IClientCallback client;
	private Contatto contatto;
	
	public CallbackWorker(IClientCallback client, Contatto contatto) {
		this.client = client;
		this.contatto = contatto;
	}
	
	public void run() {
		
			try {
				
				System.out.println("[CallbackWorker] Callback contatto aggiornato: "+contatto);
				
				client.aggiornamentoContatto(contatto);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
}
