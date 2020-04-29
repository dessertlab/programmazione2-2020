package it.unina.p2.esercitazione.networking.UDPserver;

import java.io.IOException;


public class RubricaSocketServer {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		IRubrica rubricaImpl = new RubricaImpl("/Users/rnatella/rubrica.csv");
		
		RubricaSkeleton skeleton = new RubricaSkeleton(rubricaImpl);
		
		System.out.println("[Skeleton] Avvio skeleton...");
		skeleton.runSkeleton();
		
		
	}

}
