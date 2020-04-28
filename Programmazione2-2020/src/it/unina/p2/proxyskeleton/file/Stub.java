package it.unina.p2.proxyskeleton.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Stub implements Interfaccia {
	
	private Socket s;
	
	public Stub(int port){
		try {
			s = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvaSuFile(int id) {
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			dos.writeUTF("write");
			dos.writeInt(id);
			dos.flush();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	@Override
	public int filePop() {
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
			DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
			dos.writeUTF("read");
			dos.flush();
			int returnMe = dis.readInt();
			s.close();
			return returnMe;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
