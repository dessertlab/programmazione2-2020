package it.unina.p2.esercitazione.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.unina.p2.esercitazione.rmi.server.Contatto;

public class ClientCallback extends UnicastRemoteObject implements it.unina.p2.esercitazione.rmi.server.IClientCallback {

	protected ClientCallback() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4538339251128068709L;

	@Override
	public void aggiornamentoContatto(Contatto c) throws RemoteException {

		System.out.println("[ClientCallback] Ricevuto aggiornamento contatto: "+c);

	}

}
