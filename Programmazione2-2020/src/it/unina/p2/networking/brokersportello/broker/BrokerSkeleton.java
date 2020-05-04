package it.unina.p2.networking.brokersportello.broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public abstract class BrokerSkeleton implements IBroker {

	DatagramSocket UDPsocket;
	
	
	
	public void runSkeleton() throws IOException {
		
		
		UDPsocket = new DatagramSocket(5000);

		
		while(true) {

			System.out.println("[BrokerSkeleton] In attesa di richieste...");


			byte [] request_data = new byte[1024];		

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length);


			UDPsocket.receive(UDPpacket);




			int request_len = UDPpacket.getLength();
			InetAddress address = UDPpacket.getAddress();
			int port = UDPpacket.getPort();

			String request_str = new String( request_data, 0, request_len );


			System.out.println("[BrokerSkeleton] Ricevuto richiesta: "+request_str);

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
					
					int return_value = this.inoltraRichiesta(cReq);
					
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
				
				
				System.out.println("[BrokerSkeleton] Invio risposta: "+ response_str);
					
				UDPsocket.send(UDPresponse);
				
			}
			else if (token_request.equals("SOTTOSCRIVI")) {

				String port_str = tok.nextToken();
				
				int subscribe_port = Integer.parseInt(port_str);
				
				this.sottoscrivi(subscribe_port);
			}
			else {

				// ERRORE!!!

				System.err.println("[BrokerSkeleton] Richiesta malformata!");
			}


		}
	}


}
