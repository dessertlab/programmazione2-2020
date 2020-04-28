package it.unina.p2.proxyskeleton.prodcons;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Stub implements Interfaccia {

	Socket socket;
	
	public Stub(){
		try {
			socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8585);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void produci(int i) {
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dos.writeUTF("produci");
			dos.writeInt(i);
			dos.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int consuma() {
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dos.writeUTF("consuma");
			dos.flush();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			int returnValue =  dis.readInt();
			dis.close();
			socket.close();
			return returnValue;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
