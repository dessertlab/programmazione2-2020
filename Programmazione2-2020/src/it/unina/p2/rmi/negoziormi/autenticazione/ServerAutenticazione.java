package it.unina.p2.rmi.negoziormi.autenticazione;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerAutenticazione {

	public static void main(String[] args) throws RemoteException {

		
		Autenticazione autenticazione = new Autenticazione();

		autenticazione.aggiungiUtente("Pippo", "pass123");
		autenticazione.aggiungiUtente("Pluto", "ciao");
		autenticazione.aggiungiUtente("Paperino", "prova");
		
		autenticazione.setAdmin("Paperino");
		
		
		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		rmiRegistry.rebind("autenticazione", autenticazione);
		
	}

}
