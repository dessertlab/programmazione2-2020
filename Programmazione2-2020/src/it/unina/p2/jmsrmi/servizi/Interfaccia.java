package it.unina.p2.jmsrmi.servizi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interfaccia extends Remote {
	public void deposita(int i) throws RemoteException;
	public int preleva() throws RemoteException;
}
