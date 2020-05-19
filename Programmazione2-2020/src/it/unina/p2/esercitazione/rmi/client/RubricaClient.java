package it.unina.p2.esercitazione.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.unina.p2.esercitazione.rmi.server.Contatto;
import it.unina.p2.esercitazione.rmi.server.ContattoAlreadyExistent;
import it.unina.p2.esercitazione.rmi.server.IRubrica;


public class RubricaClient {
    
	
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {

		Registry rmiRegistry = LocateRegistry.getRegistry();
		
		IRubrica rubrica = (IRubrica) rmiRegistry.lookup("rubrica");
		
		
		
		Contatto contatto_da_sovrascrivere = new Contatto("Roberto", "Natella", 111, "test@test.com");
		
		try {
		
			System.out.println("[Client] Aggiunta contatto (sarà sovrascritto): "+contatto_da_sovrascrivere);

			rubrica.aggiungiContatto(contatto_da_sovrascrivere);
			
		} catch (ContattoAlreadyExistent e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ClientCallback client_callback = new ClientCallback();
		
		System.out.println("[Client] Registrazione callback");
		
		rubrica.registraCallback(client_callback);
		
		
		
		Contatto [] contatti_da_aggiungere = null;
		String nome_da_cercare = null;
		
		Thread [] threads = new Thread[3];
		
		
		contatti_da_aggiungere = new Contatto[2];
		contatti_da_aggiungere[0] = new Contatto("Roberto", "Natella", 123, "prova@prova.it");
		contatti_da_aggiungere[1] = new Contatto("Roberto", "Pietrantuono", 456, "test@test.it");
		nome_da_cercare = "Roberto";
		
		threads[0] = new ClientThread(rubrica, contatti_da_aggiungere, nome_da_cercare);
		threads[0].start();
		
		
				
		contatti_da_aggiungere = new Contatto[2];
		contatti_da_aggiungere[0] = new Contatto("Marcello", "Cinque", "aaa@aaa.it");
		contatti_da_aggiungere[1] = new Contatto("Stefano", "Russo", 789);
		nome_da_cercare = "Stefano";
		
		threads[1] = new ClientThread(rubrica, contatti_da_aggiungere, nome_da_cercare);
		threads[1].start();


		
		contatti_da_aggiungere = new Contatto[2];
		contatti_da_aggiungere[0] = new Contatto("Domenico", "Cotroneo", "ccc@ccc.com");
		contatti_da_aggiungere[1] = new Contatto("Luigi", "De Simone", 111);
		nome_da_cercare = "Pietro";
		
		threads[2] = new ClientThread(rubrica, contatti_da_aggiungere, nome_da_cercare);
		threads[2].start();
		
		

		for(int i = 0; i<threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		/* Attende l'esecuzione di callback dal server */
		System.out.println("[Client] In attesa prima di terminare...");
		Thread.sleep(5000);
		
		
		
		System.out.println("[Client] Rimozione callback");
		
		rubrica.rimuoviCallback(client_callback);
		
		UnicastRemoteObject.unexportObject(client_callback, false);
	}

}
