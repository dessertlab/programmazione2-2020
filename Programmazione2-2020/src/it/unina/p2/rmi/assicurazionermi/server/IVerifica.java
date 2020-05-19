package it.unina.p2.rmi.assicurazionermi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IVerifica extends Remote {

	public ArrayList<String> getTarghe() throws RemoteException;
	public boolean verifica(String targa) throws RemoteException, TargaNotFoundException;
}
