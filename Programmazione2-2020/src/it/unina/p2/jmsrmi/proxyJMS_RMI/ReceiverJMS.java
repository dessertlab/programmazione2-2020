package it.unina.p2.jmsrmi.proxyJMS_RMI;

import java.rmi.RemoteException;
import java.util.Hashtable;

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
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReceiverJMS implements Runnable {

	@Override
	public void run() {
		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.prod", "produzione");
		try {
			Context jndiContext = new InitialContext(properties);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			Queue prod = (Queue)jndiContext.lookup("prod");
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiverProd = qs.createReceiver(prod);
			MyListener listener = new MyListener();
			receiverProd.setMessageListener(listener);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
	
	private class MyListener implements MessageListener{

		@Override
		public void onMessage(Message arg0) {
			TextMessage msg = (TextMessage)arg0;
			try {
				String messagge = msg.getText();
				if(messagge.equalsIgnoreCase("Preleva")){
					System.out.println("[Receiver-server-jms]Ricevuto messaggio. ");
					int valorePrelevato = ServerJMS.servizio.preleva();
					Thread t = new Thread(new SenderJMS("Valore prelevato->" + valorePrelevato));
					t.start();
				}
				else{
					//Produzione.
					String[] splitted = messagge.split("-");
					Integer valoreDaDepositare = Integer.valueOf(splitted[1]);
					System.out.println("[Receiver-server-jms]Ricevuto valore da produrre->" + valoreDaDepositare );
					ServerJMS.servizio.deposita(valoreDaDepositare);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}