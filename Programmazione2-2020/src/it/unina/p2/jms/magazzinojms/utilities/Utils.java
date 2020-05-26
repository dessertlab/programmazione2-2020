package it.unina.p2.jms.magazzinojms.utilities;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Utils {

	public static Context getContext() throws NamingException{
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		properties.put("queue.richiesta","req");
		properties.put("queue.risposta","res");
		
		Context jndiContext = new InitialContext(properties);
		
		return jndiContext;
	}
}
