package it.unina.p2.networking.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiverMT {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8585);
		System.out.println("Server (receiver) started");
		while(true){
			Socket s = server.accept();
			Thread t = new Thread(new Worker(s));
			t.start();
		}
	}
}

