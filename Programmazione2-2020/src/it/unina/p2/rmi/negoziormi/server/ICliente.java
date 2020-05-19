package it.unina.p2.rmi.negoziormi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.unina.p2.rmi.negoziormi.autenticazione.Token;

public interface ICliente extends Remote {

	public ArrayList<Articolo> ricerca(Articolo a, Token t) throws RemoteException, NotAuthenticatedException;
	public void aggiungiCarrello(Articolo a, Token t) throws RemoteException, NotAuthenticatedException;
	public int acquista(Token t) throws RemoteException, NotAuthenticatedException;
	
}
