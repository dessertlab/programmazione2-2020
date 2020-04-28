package it.unina.p2.proxyskeleton.prodcons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SkeletonThread implements Runnable {
	
	Socket socket;
	Interfaccia servizioReale;

	public SkeletonThread(Socket socket, Interfaccia servizioReale) {
		super();
		this.socket = socket;
		this.servizioReale = servizioReale;
	}



	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String comando = dis.readUTF();
			if(comando.equalsIgnoreCase("produci")){
				int daProdurre = dis.readInt();
				this.servizioReale.produci(daProdurre);
				dis.close();
			}
			else if(comando.equalsIgnoreCase("consuma")){
				int returnValue = this.servizioReale.consuma();
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				dos.writeInt(returnValue);
				dos.close();
				dis.close();
			}
			else{
				System.out.println("comando sconosciuto: " + comando);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
