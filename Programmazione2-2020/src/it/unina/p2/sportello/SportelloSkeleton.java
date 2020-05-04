package it.unina.p2.sportello;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;



public abstract class SportelloSkeleton implements ISportello {

	private DatagramSocket UDPsocket;
	private int port = 6000;
	
	
	
	public void runSkeleton() throws IOException {
		
		
		UDPsocket = new DatagramSocket(this.port);

		
		while(true) {

			System.out.println("[SportelloSkeleton] In attesa di richieste...");


			byte [] request_data = new byte[1024];		

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length);


			UDPsocket.receive(UDPpacket);




			int request_len = UDPpacket.getLength();

			String request_str = new String( request_data, 0, request_len );


			System.out.println("[SportelloSkeleton] Ricevuto richiesta: "+request_str);

			StringTokenizer tok = new StringTokenizer(request_str, " ");


			String token_request = tok.nextToken();

			if(token_request.equals("SERVI")) {

				String clientID_str = tok.nextToken();
				String requestID_str = tok.nextToken();

				int clientID = Integer.parseInt(clientID_str);
				int requestID = Integer.parseInt(requestID_str);

				this.serviRichiesta(clientID, requestID);
				
			}
			else {

				// ERRORE!!!

				System.err.println("[SportelloSkeleton] Richiesta malformata!");
			}


		}
	}



	public void setPort(int port) {

		this.port = port;
	}

	public int getPort() {
		return this.port;
	}
	
}
