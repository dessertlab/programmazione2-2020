package it.unina.p2.networking.brokersportello.broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class BrokerProxy implements IBroker {
	
	private DatagramSocket UDPsocket;

	
	public BrokerProxy() {
		try {
			UDPsocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public synchronized int inoltraRichiesta(ClientRequest cReq) throws IDClientInvalidoException {
		
		String request_str = "INOLTRA " + cReq.toString();
		
		byte [] request_data = request_str.getBytes();
		
		int request_len = request_data.length;
		
		
		
		
		try {
			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_len, InetAddress.getLocalHost(), 5000);
			
			try {
				UDPsocket.send(UDPpacket);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			/* ...ricevi risposta ...*/
			
			byte [] response_data = new byte[1024];
			
			DatagramPacket UDPresponse = new DatagramPacket(response_data, response_data.length);
			
			try {
				UDPsocket.receive(UDPresponse);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int response_len = UDPresponse.getLength();
			
			String response_str = new String(response_data, 0, response_len);
			
			System.out.println("[BrokerProxy] Ricevuta risposta: "+ response_str);
			
			
			StringTokenizer tok = new StringTokenizer(response_str, " ");
			
			String token_outcome = tok.nextToken();

			if(token_outcome.equals("OK")) {
			
				String ret_value_str = tok.nextToken();
				
				int ret_value = Integer.parseInt(ret_value_str);
			
				return ret_value;
			}
			else if(token_outcome.equals("EXCEPTION")) {
				
				throw new IDClientInvalidoException();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;

		
	}
	
	
	

	@Override
	public synchronized void sottoscrivi(int port) {


		String request_str = "SOTTOSCRIVI " + port;
		
		byte [] request_data = request_str.getBytes();
		
		int request_len = request_data.length;
		
		
		try {
			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_len, InetAddress.getLocalHost(), 5000);

			UDPsocket.send(UDPpacket);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
