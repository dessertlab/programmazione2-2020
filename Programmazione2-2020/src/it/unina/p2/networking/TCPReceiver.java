package it.unina.p2.networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8585);
		System.out.println("Server (receiver) started: "+server);
		Socket s = server.accept();
		System.out.println("s server "+s);
		DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		String read = dis.readUTF();
		System.out.println("Server reads: " + read);
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		
		dos.writeUTF("I like trains");
		
		dos.flush();
		s.close();
		server.close();
	}
}
