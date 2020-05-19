package it.unina.p2.rmi.negoziormi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.unina.p2.rmi.negoziormi.autenticazione.Token;

public interface IAdmin extends Remote {

	public void aggiungiArticolo(Articolo a, Token t) throws RemoteException, NotAuthenticatedException;
}
