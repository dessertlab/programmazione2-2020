package it.unina.p2.esercitazione.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientCallback extends Remote {

	public void aggiornamentoContatto(Contatto c) throws RemoteException;
}
