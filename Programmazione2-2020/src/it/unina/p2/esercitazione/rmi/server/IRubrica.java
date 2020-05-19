package it.unina.p2.esercitazione.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRubrica extends Remote {
	public void aggiungiContatto(Contatto c) throws RemoteException, ContattoAlreadyExistent;
	public Contatto [] cercaContatto(String nome, String cognome) throws RemoteException, ContattoNotFound;
	public int totaleContatti() throws RemoteException;
	
	public void registraCallback(IClientCallback client) throws RemoteException;
	public void rimuoviCallback(IClientCallback client) throws RemoteException;
}
