package it.unina.p2.networking.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSender {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8585);
		DataOutputStream dos = new DataOutputStream((new BufferedOutputStream(socket.getOutputStream())));
		dos.writeUTF("We can wait 'till tomorrow");
		dos.flush();
		DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		String read = dis.readUTF();
		System.out.println("I read: " + read);
		socket.close();

	}

}
