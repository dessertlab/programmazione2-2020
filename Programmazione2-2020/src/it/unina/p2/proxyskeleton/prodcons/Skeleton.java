package it.unina.p2.proxyskeleton.prodcons;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Skeleton implements Interfaccia {
	public void runSkeleton(){
		ServerSocket s;
		try {
			s = new ServerSocket(8585);
			while(true){
				Socket socket = s.accept();
				Thread t = new Thread(new SkeletonThread(socket,this));
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
