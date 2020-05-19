package it.unina.p2.rmi.assicurazionermi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import it.unina.p2.rmi.assicurazionermi.server.IVerifica;

public class ClientVerifica {
	

	public static void main(String[] args) throws RemoteException, InterruptedException {

		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		
		try {
			
			IVerifica verifica = (IVerifica) rmiRegistry.lookup("verifica");
			
			ArrayList<String> lista_target = verifica.getTarghe();
			
			
			Thread worker[] = new Thread[3];
			
			for(int i=0; i<3; i++) {
				worker[i] = new VerificaWorker(lista_target, verifica);
				worker[i].start();
			}
			
			
			
			for(int i=0; i<3; i++) {
				worker[i].join();
			}
			
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
