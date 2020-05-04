package it.unina.p2.networking.brokersportello.sportello;

import java.io.IOException;

import it.unina.p2.broker.BrokerProxy;


public class SportelloMain {

	public static void main(String[] args) {

		if(args.length < 2) {
			System.err.println("[SportelloMain] Errore: specificare il numero di porto e il file su cui scrivere come parametri");
			System.exit(1);
		}
		
		
		int port = Integer.parseInt(args[0]);
		String filepath = args[1];
		
		
		BrokerProxy brokerProxy = new BrokerProxy();

		
		SportelloImpl sportelloSkeleton = new SportelloImpl();
		sportelloSkeleton.setPort(port);
		
		try {
			
			sportelloSkeleton.openFile(filepath);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
		
			brokerProxy.sottoscrivi(port);
			
			sportelloSkeleton.runSkeleton();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
