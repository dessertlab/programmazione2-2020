package it.unina.p2.jms.magazzinojms.server;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

import it.unina.p2.jms.magazzinojms.utilities.Utils;

public class Worker implements Runnable{

	Magazzino magazzino;
	String comando;
	int valore;
	
	
	public Worker(Magazzino magazzino, String comando) {
		super();
		this.magazzino = magazzino;
		this.comando = comando;
	}

	

	public Worker(Magazzino magazzino, String comando, int valore) {
		super();
		this.magazzino = magazzino;
		this.comando = comando;
		this.valore = valore;
	}

	@Override
	public void run() {
		if(comando.equalsIgnoreCase("deposita")){
			magazzino.Deposita(valore);
		}
		else{
			int valorePrelevato = magazzino.Preleva();
			Context jndiContext;
			try {
				jndiContext = Utils.getContext();

				
				QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
				Queue risposte = (Queue) jndiContext.lookup("risposta");
				
				QueueConnection qc = qcf.createQueueConnection();
				qc.start();
				
				QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				
				QueueSender sender = qs.createSender(risposte);
				TextMessage message = qs.createTextMessage();
				message.setIntProperty(Server.INTPROPERTY_RES, valorePrelevato);
				sender.send(message);

				sender.close();
				qs.close();
				qc.close();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
