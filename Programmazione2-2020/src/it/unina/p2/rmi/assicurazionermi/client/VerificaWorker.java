package it.unina.p2.rmi.assicurazionermi.client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.unina.p2.rmi.assicurazionermi.server.IVerifica;
import it.unina.p2.rmi.assicurazionermi.server.TargaNotFoundException;

public class VerificaWorker extends Thread {
	
	private ArrayList<String> lista_targhe;

	private IVerifica verifica;
	

	public VerificaWorker(ArrayList<String> lista_targhe, IVerifica verifica) {
		super();
		this.lista_targhe = lista_targhe;
		this.verifica = verifica;
	}
	

	
	
	public void run() {
		
		for(int i=0; i<3; i++) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("RAND:"+(int)(100*Math.random()));
			
			int k = ((int)(100*Math.random())) % lista_targhe.size();
						
			String targa = lista_targhe.get( k );
			
			
			System.out.println("[VerificaWorker] Controllo della targa " + targa + " ("+k+")");

			try {
				
				
				boolean valida = verifica.verifica(targa);
				
				if(valida) {
					System.out.println("[VerificaWorker] La targa "+targa+" è valida");
				}
				else {
					System.out.println("[VerificaWorker] La targa "+targa+" NON è valida");
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TargaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
