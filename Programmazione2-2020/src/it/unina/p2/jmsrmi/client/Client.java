package it.unina.p2.jmsrmi.client;

public class Client {
	
	public static void main(String[] args){
		
		
		SenderJMS sender = new SenderJMS();
		ReceiverJMS receiver = new ReceiverJMS();
		Thread t1 = new Thread(sender);
		Thread t2 = new Thread(receiver);
		t1.start();
		t2.start();
	}
	
	
}
