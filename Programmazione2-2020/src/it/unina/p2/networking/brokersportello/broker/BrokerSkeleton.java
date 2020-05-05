package it.unina.p2.networking.brokersportello.broker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class BrokerSkeleton implements IBroker {

	DatagramSocket UDPsocket;
	
	
	
	public void runSkeleton() throws IOException {
		
		
		UDPsocket = new DatagramSocket(5000);

		
		while(true) {

			System.out.println("[BrokerSkeleton] In attesa di richieste...");


			byte [] request_data = new byte[1024];		

			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_data.length);


			UDPsocket.receive(UDPpacket);

			
			System.out.println("[BrokerSkeleton] Avvio worker...");


			Thread worker = new BrokerWorker(UDPsocket, UDPpacket, this);
			worker.start();
			
		}

	}


}
