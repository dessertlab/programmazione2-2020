package it.unina.p2.rmi.assicurazionermi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerAssicuratore {

	public static void main(String[] args) {

		try {
			
			
			Assicurazione assicurazione = new Assicurazione();
			
			System.out.println("[ServerAssicurazione] Binding");
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			rmiRegistry.rebind("assicurazione", assicurazione);
			rmiRegistry.rebind("verifica", assicurazione);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
