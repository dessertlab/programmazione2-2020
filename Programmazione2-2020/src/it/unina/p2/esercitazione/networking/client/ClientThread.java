package it.unina.p2.esercitazione.networking.client;

import java.io.IOException;

import it.unina.p2.esercitazione.networking.server.Contatto;
import it.unina.p2.esercitazione.networking.server.ContattoAlreadyExistent;
import it.unina.p2.esercitazione.networking.server.ContattoNotFound;

public class ClientThread extends Thread {

	private RubricaProxy rubrica;
	private Contatto [] contatti_da_aggiungere;
	private String nome_da_cercare;
	
	public ClientThread(RubricaProxy rubrica, Contatto [] contatti_da_aggiungere, String nome_da_cercare) {
		this.rubrica = rubrica;
		this.contatti_da_aggiungere = contatti_da_aggiungere;
		this.nome_da_cercare = nome_da_cercare;
	}
	
	public void run() {
		try {
			
			
			for(int i = 0; i<contatti_da_aggiungere.length; i++) {
				rubrica.aggiungiContatto(contatti_da_aggiungere[i]);
			}
			
			
			try {
				Contatto c = rubrica.cercaContatto(nome_da_cercare, null);
				
				System.out.println("[Client] Contatto trovato: "+c);
				
			} catch (ContattoNotFound e) {
				
				System.err.println("[Client] Ricerca del contatto fallita");
			}
			
			
			
		} catch(ContattoAlreadyExistent e) {
			
			System.err.println("[Client] Impossibile aggiungere contatto già esistente");
			
		} catch (IOException e) {
			
			System.err.println("[Client] Errore di rete");
			e.printStackTrace();

		}
		
		
		try {
			
			System.out.println("[Client] Totale contatti: "+ rubrica.totaleContatti());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
