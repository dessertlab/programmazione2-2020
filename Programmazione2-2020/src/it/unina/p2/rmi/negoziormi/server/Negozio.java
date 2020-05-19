package it.unina.p2.rmi.negoziormi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import it.unina.p2.rmi.negoziormi.autenticazione.IAutenticazione;
import it.unina.p2.rmi.negoziormi.autenticazione.Token;

public class Negozio extends UnicastRemoteObject implements ICliente, IAdmin {


	/**
	 * 
	 */
	private static final long serialVersionUID = -969550044306182100L;
	
	private ArrayList<Articolo> articoli = new ArrayList<>();
	private IAutenticazione autenticazione;
	
	private HashMap<String,ArrayList<Articolo>> carrelli = new HashMap<String,ArrayList<Articolo>>();


	protected Negozio(IAutenticazione autenticazione) throws RemoteException {
		super();
		
		this.autenticazione = autenticazione;
		
	}
	
	@Override
	public ArrayList<Articolo> ricerca(Articolo a, Token t) throws RemoteException, NotAuthenticatedException {
		
		System.out.println("[Negozio] Richiesta ricerca: "+t.getNomeutente());

		
		boolean valida = autenticazione.valida(t);
		
		

		if(!valida) {
			
			System.out.println("[Negozio] Utente "+t.getNomeutente()+" non autenticato");

			throw new NotAuthenticatedException();
		}
		
		
		System.out.println("[Negozio] Utente "+t.getNomeutente()+" autenticato");
		
		
		
		ArrayList<Articolo> lista = new ArrayList<>();
		
		for(Articolo x : articoli) {
			
			boolean categoria_found = false;
			boolean nome_found = false;
			
			
			if( a.getCategoria() == Categoria.NONSPECIFICATO || x.getCategoria().equals(a.getCategoria()) ) {
				
				categoria_found = true;
			}
			
			
			if( a.getNome() == null || x.getNome().contains(a.getNome()) ) {
				
				nome_found = true;
			}

			
			if(categoria_found && nome_found) {
				lista.add(x);
			}
			
		}
		
		
		System.out.println("[Negozio] Risultato ricerca: "+lista);
		
		return lista;
	}

	@Override
	public void aggiungiCarrello(Articolo a, Token t) throws RemoteException, NotAuthenticatedException {

		System.out.println("[Negozio] Richiesta aggiunta articolo: "+t.getNomeutente());

		
		boolean valida = autenticazione.valida(t);
		
		

		if(!valida) {
			
			System.out.println("[Negozio] Utente "+t.getNomeutente()+" non autenticato");

			throw new NotAuthenticatedException();
		}
		
		
		System.out.println("[Negozio] Utente "+t.getNomeutente()+" autenticato");
		
		
		String user = t.getNomeutente();
		
		if(carrelli.get(user) == null) {
			
			System.out.println("[Negozio] Creazione nuovo carrello utente:"+t.getNomeutente());

			ArrayList<Articolo> carrello = new ArrayList<>();
			
			carrelli.put(user, carrello);
		}
				
		System.out.println("[Negozio] Aggiunta articolo: "+a);
				
		carrelli.get(user).add(a);
		
	}

	@Override
	public int acquista(Token t) throws RemoteException, NotAuthenticatedException {

		System.out.println("[Negozio] Richiesta acquisto carrello: "+t.getNomeutente());

		
		boolean valida = autenticazione.valida(t);
		
		

		if(!valida) {
			
			System.out.println("[Negozio] Utente "+t.getNomeutente()+" non autenticato");

			throw new NotAuthenticatedException();
		}
		
		
		System.out.println("[Negozio] Utente "+t.getNomeutente()+" autenticato");
		
		
		String user = t.getNomeutente();
		
		int totale = 0;
		
		
		if(carrelli.get(user) != null) {
						
			System.out.println("[Negozio] Carrello utente: "+user);
			
			for(Articolo a : carrelli.get(user)) {
				
				System.out.println("[Negozio] "+a);
				
				totale += a.getCosto();
			}
			
			System.out.println("[Negozio] Valore totale carrello: "+totale);

		} else {
			
			System.out.println("[Negozio] Nessun carrello per l'utente: "+user);
		}
		
		
		carrelli.put(user, null);
		
		
		return totale;

		
	}

	@Override
	public void aggiungiArticolo(Articolo a, Token t) throws RemoteException, NotAuthenticatedException {


		System.out.println("[Negozio] Richiesta aggiunta articolo: "+t.getNomeutente());

		
		boolean valida = autenticazione.valida(t);
		
		

		if(!valida) {
			
			System.out.println("[Negozio] Utente "+t.getNomeutente()+" non autenticato");

			throw new NotAuthenticatedException();
		}
		
		
		System.out.println("[Negozio] Utente "+t.getNomeutente()+" autenticato");
		
		
		
		articoli.add(a);
		
	}

}
