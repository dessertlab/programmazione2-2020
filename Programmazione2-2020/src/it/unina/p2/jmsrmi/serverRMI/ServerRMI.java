package it.unina.p2.jmsrmi.serverRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.jmsrmi.servizi.Interfaccia;

public class ServerRMI {

	public static void main(String[] args) throws RemoteException {
		Registry rmiRegistry = LocateRegistry.createRegistry(8150);
		Interfaccia servizio = new Servizio();
		rmiRegistry.rebind("servizio", servizio);
		System.out.println("[SERVER-RMI-LOG]Server avviato su porta->8150");

	}

}
