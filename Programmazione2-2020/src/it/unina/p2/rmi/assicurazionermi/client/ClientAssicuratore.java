package it.unina.p2.rmi.assicurazionermi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.rmi.assicurazionermi.server.IAssicuratore;
import it.unina.p2.rmi.assicurazionermi.server.Polizza;

public class ClientAssicuratore {

	public static void main(String[] args) throws RemoteException {

		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		try {
			
			
			IAssicuratore assicurazione = (IAssicuratore) rmiRegistry.lookup("assicurazione");

			Polizza p1 = new Polizza("Pippo", "Panda", "AB123CD", System.currentTimeMillis()+10000);
		
			Polizza p2 = new Polizza("Paperino", "Punto", "XY456WZ", System.currentTimeMillis()+10000);
			
			Polizza p3 = new Polizza("Topolino", "Bravo", "RS789PQ", System.currentTimeMillis()+10000);

			
			System.out.println("[ClientAssicuratore] Registrazione polizza: " + p1);
			
			assicurazione.registra(p1);
			
			
			System.out.println("[ClientAssicuratore] Registrazione polizza: " + p2);

			assicurazione.registra(p2);
			
			System.out.println("[ClientAssicuratore] Registrazione polizza: " + p3);

			assicurazione.registra(p3);
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
