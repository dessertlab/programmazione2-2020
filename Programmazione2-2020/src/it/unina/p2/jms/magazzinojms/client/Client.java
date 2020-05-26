package it.unina.p2.jms.magazzinojms.client;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

import it.unina.p2.jms.magazzinojms.utilities.Utils;

public class Client {
	
	final static String INTPROPERTY_REQ = "val_req";
	final static String INTPROPERTY_RES = "val_res";
	private final static String TIPOMSG = "tipo";
	private static final int N = 10;
	
	public static void main(String[] args) throws NamingException, JMSException {
		
		Context jndiContext = Utils.getContext();
		
		QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
		Queue richieste = (Queue) jndiContext.lookup("richiesta");
		Queue risposte = (Queue) jndiContext.lookup("risposta");
		
		QueueConnection qc = qcf.createQueueConnection();
		qc.start();
		
		QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Set-up del receiver per la coda di messaggi Risposte
		QueueReceiver qr = qs.createReceiver(risposte);
		MyListener listener = new MyListener();
		qr.setMessageListener(listener);
		//Set up del sender per la coda di messaggi Richieste.
		QueueSender sender = qs.createSender(richieste);
		TextMessage message = qs.createTextMessage();
		
		//Generazione random e ciclica delle richieste.
		for(int i = 0; i < N; i++){
			if(Math.random() < 0.5){
				//CASO DEPOSITA
				message.setStringProperty(TIPOMSG, "deposita");
				Random r = new Random();
				int randomValue = r.nextInt(100);
				message.setIntProperty(INTPROPERTY_REQ, randomValue);
				sender.send(message);
				System.out.println("[CLIENT-LOG]Mandato messaggio deposita con valore : " + randomValue);
			}
			else{
				//CASO PRELEVA
				message.setStringProperty(TIPOMSG, "preleva");
				sender.send(message);
				System.out.println("[CLIENT-LOG]Mandato messaggio preleva ");
			}
		}
		
	}
	
	private static class MyListener implements MessageListener{

		@Override
		public void onMessage(Message message) {
			TextMessage msg = (TextMessage)message;
			try {
				System.out.println("Ricevuto messaggio dalla coda 'risposte' : " + msg.getIntProperty(INTPROPERTY_RES));
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
