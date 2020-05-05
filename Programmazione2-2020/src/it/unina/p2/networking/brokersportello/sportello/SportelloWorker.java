package it.unina.p2.networking.brokersportello.sportello;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class SportelloWorker extends Thread {


	private DatagramSocket UDPsocket;
	private DatagramPacket UDPpacket;
	private SportelloSkeleton sportelloSkeleton;
	
	public SportelloWorker(DatagramSocket UDPsocket, DatagramPacket UDPpacket, SportelloSkeleton sportelloSkeleton) {
		super();
		this.UDPsocket = UDPsocket;
		this.UDPpacket = UDPpacket;
		this.sportelloSkeleton = sportelloSkeleton;
	}

	public void run() {
		

		byte [] request_data = UDPpacket.getData();
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

			sportelloSkeleton.serviRichiesta(clientID, requestID);
			
		}
		else {

			// ERRORE!!!

			System.err.println("[SportelloSkeleton] Richiesta malformata!");
		}
	}
}
