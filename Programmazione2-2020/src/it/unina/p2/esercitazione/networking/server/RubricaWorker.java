package it.unina.p2.esercitazione.networking.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class RubricaWorker extends Thread {

	private DatagramSocket UDPsocket;
	private DatagramPacket UDPpacket;
	private RubricaSkeleton skeleton;
	
	public RubricaWorker(DatagramSocket UDPsocket, DatagramPacket UDPpacket, RubricaSkeleton skeleton) {
		
		this.UDPsocket = UDPsocket;
		this.UDPpacket = UDPpacket;
		this.skeleton = skeleton;
	}

	public void run() {

		int length = UDPpacket.getLength();
		byte [] data = UDPpacket.getData();
		InetAddress clientAddress = UDPpacket.getAddress();
		int clientPort = UDPpacket.getPort();
		
		
		
		String message = new String(data, 0, length);

		StringTokenizer tokenizer = new StringTokenizer(message,",");
		
		String firstToken = tokenizer.nextToken();
		
		
		
		if(firstToken.equals("AGGIUNGI")) {
			
			System.out.println("[Worker] Richiesta AGGIUNGI");
			
			
			String contatto_csv = tokenizer.nextToken();
			
			Contatto c = new Contatto(contatto_csv);
			
			
			String response_str;

			try {
				skeleton.aggiungiContatto(c);
				
				response_str = "OK";
				
				System.out.println("[Worker] Nuovo contatto aggiunto: " + c);
				
			} catch (ContattoAlreadyExistent e) {

				response_str = "ALREADYEXISTENT";
				
				System.out.println("[Worker] Contatto già esistente: " + c);
			}
			
			
			byte [] response_data = response_str.getBytes();
			
			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length, clientAddress, clientPort);
			
			try {
				System.out.println("[Worker] Risposta: " + response_str);
				
				UDPsocket.send(UDPresponse);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(firstToken.equals("TROVA")) {
			
			System.out.println("[Worker] Richiesta TROVA CONTATTO");
			
			String nome = null;
			String cognome = null;
			
			if(tokenizer.hasMoreTokens())
				nome = tokenizer.nextToken();
			
			if(tokenizer.hasMoreTokens())
				cognome = tokenizer.nextToken();
			
			
			String response_str;
			
			try {
				Contatto c = skeleton.cercaContatto(nome, cognome);
				
				response_str = "OK," + c.toString();
				
				
				System.out.println("[Worker] Contatto trovato: " + c);

			} catch (ContattoNotFound e1) {

				response_str = "NOTFOUND";
				
				System.out.println("[Worker] Contatto non trovato");
			}
			
			
			byte [] response_data = response_str.getBytes();
			
			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length, clientAddress, clientPort);
			
			try {
				
				System.out.println("[Worker] Risposta: " + response_str);

				UDPsocket.send(UDPresponse);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(firstToken.equals("TOTALE")) {
			
			System.out.println("[Worker] Richiesta TOTALE");
			
			
			int totale = skeleton.totaleContatti();
			
			String response_str = "OK," + Integer.toString(totale);
			
			byte [] response_data = response_str.getBytes();
			
			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length, clientAddress, clientPort);
			
			try {
				System.out.println("[Worker] Risposta: " + response_str);

				UDPsocket.send(UDPresponse);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
		
			System.err.println("[Worker] Messaggio malformato: " + message);
		}

	}

}
