package it.unina.p2.networking.serverMT;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Worker implements Runnable{

	Socket s;
	
	public Worker(Socket s){
		this.s = s;
	}
	
	@Override
	public void run() {
		DataInputStream dis;
		try {
			dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			String read = dis.readUTF();
			System.out.println("Server (thread) reads: " + read);
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			dos.writeUTF("I like trains");
			dos.flush();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
