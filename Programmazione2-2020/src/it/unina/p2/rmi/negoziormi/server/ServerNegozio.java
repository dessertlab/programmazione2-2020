package it.unina.p2.rmi.negoziormi.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.rmi.negoziormi.autenticazione.IAutenticazione;

public class ServerNegozio {

	public static void main(String[] args) throws RemoteException, NotBoundException {

		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		
		IAutenticazione autenticazione = (IAutenticazione) rmiRegistry.lookup("autenticazione");
		
		
		Negozio negozio = new Negozio(autenticazione);
		
		
		rmiRegistry.rebind("negozio", negozio);
		
	}

}
