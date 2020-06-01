package it.unina.p2.jmsrmi.proxyJMS_RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.jmsrmi.servizi.Interfaccia;

public class ServerJMS {

	static Interfaccia servizio;
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		Registry rmiRegistry = LocateRegistry.getRegistry(8150);
		servizio = (Interfaccia) rmiRegistry.lookup("servizio");
		
		ReceiverJMS receiver = new ReceiverJMS();
		Thread t = new Thread(receiver);
		System.out.println("Thread started to serve a client. Thread ID: "+t.getId());
		t.start();
	}
}
