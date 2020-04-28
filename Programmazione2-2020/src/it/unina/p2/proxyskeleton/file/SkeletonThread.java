package it.unina.p2.proxyskeleton.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SkeletonThread implements Runnable {

	Interfaccia servizio;
	Socket socket;
	
	public SkeletonThread(Socket s , Interfaccia i){
		socket = s;
		servizio = i;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String comando = dis.readUTF();
			if(comando.equalsIgnoreCase("write")){
				int toWrite = dis.readInt();
				System.out.println("SERVERMSG->Scritto valore: " + toWrite);
				this.servizio.salvaSuFile(toWrite);
			}
			else if(comando.equalsIgnoreCase("read")){
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				int read = this.servizio.filePop();
				System.out.println("SERVERMSG->Letto valore : " + read);
				dos.writeInt(read);
				dos.flush();
			}
			else{
				System.out.println("Comando : '" + comando + "' non riconosciuto.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
