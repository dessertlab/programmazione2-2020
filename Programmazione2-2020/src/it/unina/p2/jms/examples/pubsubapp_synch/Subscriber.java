package it.unina.p2.jms.examples.pubsubapp_synch;

import javax.naming.*;
import javax.jms.*;

import java.util.Hashtable;

public class Subscriber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		
		jndiProperties.put( "java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		jndiProperties.put( "java.naming.provider.url", "tcp://127.0.0.1:61616" );
		
		//		jndi topic name   queue-name
		jndiProperties.put( "topic.test", "mytesttopic" );
		
		try{
			Context jndiContext = new InitialContext ( jndiProperties);
			
			TopicConnectionFactory connFactory = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
			Topic topic = (Topic)jndiContext.lookup("test");
			
			TopicConnection topicConn = connFactory.createTopicConnection();
			TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
			TopicSubscriber sub = topicSession.createSubscriber(topic);
			TextMessage msg;
			
			topicConn.start();// tells the messaging provider to start message delivery
    		do{
            	System.out.println ("In attesa di messaggi!");
            	msg = (TextMessage)sub.receive();
            	System.out.println ("	+ messaggio ricevuto: " + msg.getText());
            }while (msg.getText().compareTo("fine") != 0); 
			
			sub.close();
			topicSession.close();
			topicConn.close();
			
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		
	}

}
