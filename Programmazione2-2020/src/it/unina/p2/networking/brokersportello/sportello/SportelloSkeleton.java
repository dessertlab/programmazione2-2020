package it.unina.p2.networking.brokersportello.sportello;

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


			Thread worker = new SportelloWorker(UDPsocket, UDPpacket, this);
			worker.start();


		}
	}



	public void setPort(int port) {

		this.port = port;
	}

	public int getPort() {
		return this.port;
	}
	
}
