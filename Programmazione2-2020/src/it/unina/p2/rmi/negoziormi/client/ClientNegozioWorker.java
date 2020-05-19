package it.unina.p2.rmi.negoziormi.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import it.unina.p2.rmi.negoziormi.autenticazione.IAutenticazione;
import it.unina.p2.rmi.negoziormi.autenticazione.Token;
import it.unina.p2.rmi.negoziormi.server.Articolo;
import it.unina.p2.rmi.negoziormi.server.Categoria;
import it.unina.p2.rmi.negoziormi.server.ICliente;
import it.unina.p2.rmi.negoziormi.server.NotAuthenticatedException;

public class ClientNegozioWorker extends Thread {

	private String username;
	private String password;
	private IAutenticazione autenticazione;
	private ICliente negozio;
	
	public ClientNegozioWorker(String username, String password, IAutenticazione autenticazione, ICliente negozio) {
		super();
		this.username = username;
		this.password = password;
		this.autenticazione = autenticazione;
		this.negozio = negozio;
	}
	
	
	
	public void run() {
		
		

		System.out.println("[ClientNegozio] Autenticazione");

		Token t;


		try {

			t = autenticazione.autentica(username, password);


			
			for(int i=0; i<3; i++) {

				int r = new Random().nextInt( Categoria.values().length - 1 );
				Categoria c = Categoria.values()[r];
				
				System.out.println("[ClientNegozio] Ricerca articoli: " + c);

				
				Articolo x = new Articolo("", c, 0);



				ArrayList<Articolo> lista = negozio.ricerca(x, t);

				System.out.println("[ClientNegozio] Risultato ricerca: "+lista);
				
				
				
				System.out.println("[ClientNegozio] Aggiunta articolo: "+lista.get(0));

				negozio.aggiungiCarrello(lista.get(0), t);
				
			}
			
			
			int totale = negozio.acquista(t);
			
			System.out.println("[ClientNegozio] Costo totale carrello: "+totale);




		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAuthenticatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
