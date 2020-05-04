package it.unina.p2.networking.brokersportello.broker;

import java.io.IOException;

public class BrokerMain {

	public static void main(String[] args) {
		
		BrokerSkeleton brokerSkeleton = new BrokerImpl();
		
		try {
		
			brokerSkeleton.runSkeleton();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
