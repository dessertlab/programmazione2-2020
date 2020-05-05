package it.unina.p2.networking.brokersportello.broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class BrokerWorker extends Thread {
	
	
	private DatagramPacket UDPpacket;
	private DatagramSocket UDPsocket;
	private BrokerSkeleton brokerSkeleton;

	public BrokerWorker(DatagramSocket UDPsocket, DatagramPacket UDPpacket, BrokerSkeleton brokerSkeleton) {
		super();
		this.UDPsocket = UDPsocket;
		this.UDPpacket = UDPpacket;
		this.brokerSkeleton = brokerSkeleton;
	}
	
	public void run() {
		
		byte [] request_data = UDPpacket.getData();
		int request_len = UDPpacket.getLength();
		InetAddress address = UDPpacket.getAddress();
		int port = UDPpacket.getPort();

		String request_str = new String( request_data, 0, request_len );


		System.out.println("[BrokerWorker] Ricevuto richiesta: "+request_str);

		StringTokenizer tok = new StringTokenizer(request_str, " ,");


		String token_request = tok.nextToken();

		if(token_request.equals("INOLTRA")) {

			String clientID_str = tok.nextToken();
			String requestID_str = tok.nextToken();

			int clientID = Integer.parseInt(clientID_str);
			int requestID = Integer.parseInt(requestID_str);

			ClientRequest cReq = new ClientRequest(clientID, requestID);

			
			
			
			String response_str = null;

			try {
				
				int return_value = brokerSkeleton.inoltraRichiesta(cReq);
				
				response_str = "OK " + return_value;
				
				
			} catch (IDClientInvalidoException e) {
				
				/* da trasmettere al proxy... */
				
				response_str = "EXCEPTION CLIENT_ID_INVALIDO";
			}
			/*catch(AltraException e) {
				response_str = "EXCEPTION ALTRA_EXCEPTION";
			}*/

			
			
			byte [] response_data = response_str.getBytes();
			
			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length, address, port);
			
			
			System.out.println("[BrokerWorker] Invio risposta: "+ response_str);
				
			try {
				UDPsocket.send(UDPresponse);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (token_request.equals("SOTTOSCRIVI")) {

			String port_str = tok.nextToken();
			
			int subscribe_port = Integer.parseInt(port_str);
			
			brokerSkeleton.sottoscrivi(subscribe_port);
		}
		else {

			// ERRORE!!!

			System.err.println("[BrokerWorker] Richiesta malformata!");
		}
	}
}
