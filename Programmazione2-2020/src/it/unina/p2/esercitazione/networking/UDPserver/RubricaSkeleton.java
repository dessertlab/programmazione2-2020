package it.unina.p2.esercitazione.networking.UDPserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RubricaSkeleton implements IRubrica {

	IRubrica rubrica_impl;
	
	final int UDPport = 5000;
		
	public RubricaSkeleton(IRubrica rubrica_impl) {
		this.rubrica_impl = rubrica_impl;
	}
	
	public void runSkeleton() throws IOException {
		
		DatagramSocket UDPsocket = new DatagramSocket(UDPport);
		
		
		while(true) {

			// NOTA: Creiamo un nuovo array di byte e un nuovo DatagramPacket per ogni richiesta
			// (ogni thread lavora su una richiesta distinta)
			byte [] data = new byte[65508];
			DatagramPacket UDPpacket = new DatagramPacket(data, data.length);

			System.out.println("[Skeleton] Skeleton in attesa di richieste");
			
			UDPsocket.receive(UDPpacket);
			
			RubricaWorker worker = new RubricaWorker(UDPsocket, UDPpacket, this);
			
			System.out.println("[Skeleton] Avvio worker thread...");
			worker.start();
		}
	}
	
	@Override
	public void aggiungiContatto(Contatto c) throws ContattoAlreadyExistent {

		try {
			this.rubrica_impl.aggiungiContatto(c);
		} catch (IOException e) {
			// Ignore IOException - it is meant for the client
		}
	}

	@Override
	public Contatto cercaContatto(String nome, String cognome) throws ContattoNotFound {
		
		Contatto contatto = null;
		
		try {
			contatto = this.rubrica_impl.cercaContatto(nome, cognome);
		} catch (IOException e) {
			// Ignore IOException - it is meant for the client
		}
		
		return contatto;
	}

	@Override
	public int totaleContatti() {
		
		int totale = 0;
		
		try {
			totale = this.rubrica_impl.totaleContatti();
		} catch (IOException e) {
			// Ignore IOException - it is meant for the client
		}
		
		return totale;
	}

}
