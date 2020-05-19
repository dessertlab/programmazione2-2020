package it.unina.p2.rmi.negoziormi.autenticazione;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;

public class Autenticazione extends UnicastRemoteObject implements IAutenticazione {

	protected Autenticazione() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2955816035380565933L;
	
	private HashMap<String,String> users = new HashMap<>();
	private HashSet<String> admins = new HashSet<>();
	
	
	private HashSet<Token> tokens = new HashSet<>();

	@Override
	public Token autentica(String user, String password) throws RemoteException {
		

		if(users.containsKey(user) && users.get(user).equals(password)) {
			
			System.out.println("[Autenticazione] Richiesta autenticazione utente: "+user);
			
			Token t = new Token( user, ((int)(1000000*Math.random())), System.currentTimeMillis()+10000 );
			
			tokens.add(t);
			
			return t;
		}
		
		return null;
	}

	
	
	@Override
	public boolean valida(Token t) throws RemoteException {

		System.out.println("[Autenticazione] Richiesta validazione token: "+t.getNomeutente());

		
		if( tokens.contains(t) ) {
			
			System.out.println("[Autenticazione] Validazione riuscita: "+t.getNomeutente());
			
			return true;
		}
		
		
		System.out.println("[Autenticazione] Validazione fallita: "+t.getNomeutente());
		
		return false;
	}
	
	
	@Override
	public boolean validaAdmin(Token t) throws RemoteException {
		
		if(this.valida(t)) {
			
			if(admins.contains(t.getNomeutente())) {
				
				return true;
			}
		}
		
		System.out.println("[Autenticazione] Validazione admin fallita: "+t.getNomeutente());

		return false;
	}
	
	
	public void aggiungiUtente(String username, String password) {
		
		users.put(username, password);
	}
	
	
	public void setAdmin(String username) {
		
		admins.add(username);
	}

}
