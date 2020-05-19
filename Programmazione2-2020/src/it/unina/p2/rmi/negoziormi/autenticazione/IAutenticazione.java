package it.unina.p2.rmi.negoziormi.autenticazione;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAutenticazione extends Remote {

	public Token autentica(String user, String password) throws RemoteException;
	public boolean valida(Token t) throws RemoteException;
	public boolean validaAdmin(Token t) throws RemoteException;
	
}
