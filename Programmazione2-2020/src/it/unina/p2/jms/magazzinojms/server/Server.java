package it.unina.p2.jms.magazzinojms.server;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

import it.unina.p2.jms.magazzinojms.utilities.Utils;


public class Server {

	final static String INTPROPERTY_REQ = "val_req";
	final static String INTPROPERTY_RES = "val_res";
	final static String TIPOMSG = "tipo";
	
	public static void main(String[] args) throws NamingException, JMSException {
		Magazzino magazzino = new Magazzino();
		Context jndiContext = Utils.getContext();
		
		QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
		Queue richieste = (Queue) jndiContext.lookup("richiesta");
		
		QueueConnection qc = qcf.createQueueConnection();

		QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Set-up del receiver per la coda di messaggi Risposte
		QueueReceiver qr = qs.createReceiver(richieste);
		MyListener listener = new MyListener(magazzino);
		qr.setMessageListener(listener);
		
		qc.start();// tells the messaging provider to start message delivery
		
		System.out.println("[SERVER-LOG]Server inizializzato");

	}
	
	private static class MyListener implements MessageListener{


		Magazzino magazzino;
		
		public MyListener(Magazzino magazzino) {
			this.magazzino = magazzino;
		}

		@Override
		public void onMessage(Message message) {
			TextMessage msg = (TextMessage)message;
			
			
			try {
				if(msg.getStringProperty(TIPOMSG).equalsIgnoreCase("preleva")){
					Thread t = new Thread(new Worker(magazzino,"preleva"));
					t.start();
				}
				else if(msg.getStringProperty(TIPOMSG).equalsIgnoreCase("deposita")){
					Thread t = new Thread(new Worker(magazzino,"deposita",msg.getIntProperty(INTPROPERTY_REQ)));
					t.start();
				}
				else {
					System.out.println("Proprieta' non riconosciuta o indisponibile");
				}
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
}

}