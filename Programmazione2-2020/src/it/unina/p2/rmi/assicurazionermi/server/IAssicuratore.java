package it.unina.p2.rmi.assicurazionermi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAssicuratore extends Remote {

	public void registra(Polizza p) throws RemoteException;
	public void rinnova(String targa, long scadenza) throws RemoteException, TargaNotFoundException;
}
