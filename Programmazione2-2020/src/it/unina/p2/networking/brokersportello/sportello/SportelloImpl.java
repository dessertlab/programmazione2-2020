package it.unina.p2.networking.brokersportello.sportello;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SportelloImpl extends SportelloSkeleton {

	
	private PrintWriter writer;


	public void openFile(String path) throws IOException {
		
		System.out.println("[Sportello] Scrittura su file: "+path);
		
		this.writer = new PrintWriter(new FileWriter(path));
	}
	
	
	@Override
	public void serviRichiesta(int clientID, int requestID) {
		
		System.out.println("[Sportello] Ricevuta richiesta: " + clientID + ", " + requestID);
		
		
		try {
			Thread.sleep((((int)(100*Math.random()))% 6) +5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(writer != null) {
			
			writer.println("ClID="+clientID+", req="+requestID);
			writer.flush();
		}
		
		
		System.out.println("[Sportello] Elaborazione terminata");
		
	}

}
