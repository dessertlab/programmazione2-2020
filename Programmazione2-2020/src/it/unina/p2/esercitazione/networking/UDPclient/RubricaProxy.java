package it.unina.p2.esercitazione.networking.UDPclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import it.unina.p2.esercitazione.networking.UDPserver.Contatto;
import it.unina.p2.esercitazione.networking.UDPserver.ContattoAlreadyExistent;
import it.unina.p2.esercitazione.networking.UDPserver.ContattoNotFound;
import it.unina.p2.esercitazione.networking.UDPserver.IRubrica;

public class RubricaProxy implements IRubrica {
	
	final int UDPport = 5000;
	final String address = "127.0.0.1";
	
	
	@Override
	public void aggiungiContatto(Contatto c) throws ContattoAlreadyExistent, IOException {

		try( DatagramSocket UDPsocket = new DatagramSocket() ) {

			String request_str = "AGGIUNGI," + c.toString();
			byte [] request_data = request_str.getBytes();

			InetAddress inetAddress = InetAddress.getByName(address);

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length, inetAddress, UDPport);

			System.out.println("[Proxy] Invio richiesta: " + request_str);
			
			UDPsocket.send(UDPpacket);

			
			UDPsocket.setSoTimeout(3000);

			byte [] response_data = new byte[65508];

			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length);

			UDPsocket.receive(UDPresponse);

			
			
			String response_str = new String(response_data,0,UDPresponse.getLength());

			System.out.println("[Proxy] Ricezione risposta: " + response_str);

			StringTokenizer tokenizer = new StringTokenizer(response_str, ",");

			String status = tokenizer.nextToken();
			
			

			if(status.equals("OK")) {
				return;
			}
			else if(status.equals("ALREADYEXISTENT")) {

				throw new ContattoAlreadyExistent();
			}
			else {

				System.err.println("[Proxy] Messaggio malformato: " + response_str);
			}

		}
	}

	@Override
	public Contatto cercaContatto(String nome, String cognome) throws ContattoNotFound, IOException {

		Contatto contatto = null;
		

		try( DatagramSocket UDPsocket = new DatagramSocket() ) {

			String request_str = "TROVA," + (nome != null ? nome : "") + "," + (cognome != null ? cognome : "");
			byte [] request_data = request_str.getBytes();

			InetAddress inetAddress = InetAddress.getByName(address);

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length, inetAddress, UDPport);

			System.out.println("[Proxy] Invio richiesta: " + request_str);

			UDPsocket.send(UDPpacket);


			
			UDPsocket.setSoTimeout(3000);

			byte [] response_data = new byte[65508];

			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length);

			UDPsocket.receive(UDPresponse);

			
			
			String response_str = new String(response_data,0,UDPresponse.getLength());
			
			System.out.println("[Proxy] Ricezione risposta: " + response_str);

			StringTokenizer tokenizer = new StringTokenizer(response_str, ",");

			String status = tokenizer.nextToken();

			
			
			if(status.equals("OK")) {
				
				String contatto_csv = tokenizer.nextToken();
				
				contatto = new Contatto(contatto_csv);
				
			}
			else if(status.equals("NOTFOUND")) {

				throw new ContattoNotFound();
			}
			else {

				System.err.println("[Proxy] Messaggio malformato: " + response_str);
			}

		}
		
		return contatto;
	}

	@Override
	public int totaleContatti() throws IOException {


		int totale = 0;
		

		try( DatagramSocket UDPsocket = new DatagramSocket() ) {

			String request_str = "TOTALE";
			byte [] request_data = request_str.getBytes();

			InetAddress inetAddress = InetAddress.getByName(address);

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length, inetAddress, UDPport);

			System.out.println("[Proxy] Invio richiesta: " + request_str);

			UDPsocket.send(UDPpacket);


			
			UDPsocket.setSoTimeout(3000);

			byte [] response_data = new byte[65508];

			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length);

			UDPsocket.receive(UDPresponse);
			
			
			String response_str = new String(response_data,0,UDPresponse.getLength());

			System.out.println("[Proxy] Ricezione risposta: " + response_str);

			StringTokenizer tokenizer = new StringTokenizer(response_str, ",");

			String status = tokenizer.nextToken();
			
			

			if(status.equals("OK")) {
			
				try {
					String totale_str = tokenizer.nextToken();

					totale = Integer.parseInt(totale_str);
				}
				catch(NoSuchElementException | NumberFormatException e) {
					
					System.err.println("[Proxy] Messaggio malformato: " + response_str);
				}
			}

			else {
				
				System.err.println("[Proxy] Messaggio malformato: " + response_str);
			}

		}
		
		return totale;
	}

}
