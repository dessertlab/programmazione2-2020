package it.unina.p2.rmi.negoziormi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.unina.p2.rmi.negoziormi.autenticazione.IAutenticazione;
import it.unina.p2.rmi.negoziormi.autenticazione.Token;
import it.unina.p2.rmi.negoziormi.server.Articolo;
import it.unina.p2.rmi.negoziormi.server.Categoria;
import it.unina.p2.rmi.negoziormi.server.IAdmin;
import it.unina.p2.rmi.negoziormi.server.NotAuthenticatedException;

public class ClientAdmin {

	public static void main(String[] args) throws RemoteException, NotBoundException, NotAuthenticatedException {


		Registry rmiRegistry = LocateRegistry.getRegistry();

		IAutenticazione autenticazione = (IAutenticazione) rmiRegistry.lookup("autenticazione");
		
		IAdmin negozio = (IAdmin) rmiRegistry.lookup("negozio");
		
		
		
		Token t = autenticazione.autentica("Paperino", "prova");
		
		
		Articolo a1 = new Articolo("CK",Categoria.MAGLIETTA, 30);
		
		Articolo a2 = new Articolo("Nike", Categoria.SCARPE, 40);
		
		Articolo a3 = new Articolo("Ferrari", Categoria.CAPPELLO, 50);
		
		
		negozio.aggiungiArticolo(a1, t);
		negozio.aggiungiArticolo(a2, t);
		negozio.aggiungiArticolo(a3, t);
		
	}

}
