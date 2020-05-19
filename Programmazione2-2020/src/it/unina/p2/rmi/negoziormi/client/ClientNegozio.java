package it.unina.p2.rmi.negoziormi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.rmi.negoziormi.autenticazione.IAutenticazione;
import it.unina.p2.rmi.negoziormi.server.ICliente;
import it.unina.p2.rmi.negoziormi.server.NotAuthenticatedException;

public class ClientNegozio {

	public static void main(String[] args) throws RemoteException, NotBoundException, NotAuthenticatedException, InterruptedException {


		Registry rmiRegistry = LocateRegistry.getRegistry();

		IAutenticazione autenticazione = (IAutenticazione) rmiRegistry.lookup("autenticazione");
		
		ICliente negozio = (ICliente) rmiRegistry.lookup("negozio");
		
		
		Thread worker[] = new Thread[3];
		

		worker[0] = new ClientNegozioWorker("Pippo", "pass123", autenticazione, negozio);
		worker[0].start();

		worker[1] = new ClientNegozioWorker("Pluto", "ciao", autenticazione, negozio);
		worker[1].start();
		
		worker[2] = new ClientNegozioWorker("Paperino", "prova", autenticazione, negozio);
		worker[2].start();
		
		
		for(int i=0; i<3; i++) {
			
			worker[i].join();
		}
		
	}

}
