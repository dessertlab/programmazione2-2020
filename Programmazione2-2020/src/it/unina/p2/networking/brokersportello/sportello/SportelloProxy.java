package it.unina.p2.networking.brokersportello.sportello;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SportelloProxy implements ISportello {

	
	private DatagramSocket UDPsocket;
	private int port = 6000;

	
	public SportelloProxy() {
		try {
			UDPsocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setPort(int port) {
		this.port = port;
	}
	
	
	public int getPort() {
		return this.port;
	}
	
	
	@Override
	public void serviRichiesta(int clientID, int requestID) {

		String request_str = "SERVI " + clientID + " " + requestID;
		
		byte [] request_data = request_str.getBytes();
		
		int request_len = request_data.length;
		
		
		try {
			DatagramPacket UDPpacket = new DatagramPacket(request_data, request_len, InetAddress.getLocalHost(), this.port);

			UDPsocket.send(UDPpacket);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
