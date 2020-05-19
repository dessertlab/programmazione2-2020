package it.unina.p2.esercitazione.rmi.server;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RubricaServer {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		RubricaImpl rubrica = new RubricaImpl("/Users/rnatella/rubrica.csv");
		
		System.out.println("[RubricaServer] Server in esecuzione");
		
		rmiRegistry.rebind("rubrica", rubrica);
	}

}
