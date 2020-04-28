package it.unina.p2.proxyskeleton.file;

import java.net.ServerSocket;
import java.net.Socket;


public abstract class Skeleton implements Interfaccia{
	public void runSkeleton(){
		ServerSocket server = null;
		Socket s = null;
		try{
			server = new ServerSocket(8150);
			System.out.println("Server avviato.");
			while(true){
				s = server.accept();
				System.out.println("Accettata nuova connessione.");
				Thread t = new Thread(new SkeletonThread(s,this));
				t.start();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
