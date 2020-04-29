package it.unina.p2.esercitazione.networking.UDPclient;

import it.unina.p2.esercitazione.networking.UDPserver.Contatto;


public class RubricaSocketClient {
    
	
	public static void main(String[] args) {


		RubricaProxy rubrica = new RubricaProxy();
		
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

		
	}

}
