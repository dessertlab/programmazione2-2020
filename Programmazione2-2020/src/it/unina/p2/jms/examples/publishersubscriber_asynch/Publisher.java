package it.unina.p2.jms.examples.publishersubscriber_asynch;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class Publisher {

	public static void main(String[] args) throws NamingException, JMSException {
		//TOPIC - MESSAGE.
		//Soccer Ronaldo-is-great (as input argument)
		if(args.length < 2) return;
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("topic.soccer" , "soccernews");
		properties.put("topic.tennis" , "tennisnews");
		Context jndiContext = new InitialContext(properties);
		
		//TCF => TC => TS
		TopicConnectionFactory tcf = (TopicConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
		Topic soccer = (Topic)jndiContext.lookup("soccer");
		Topic tennis = (Topic)jndiContext.lookup("tennis");
		
		TopicConnection tc = tcf.createTopicConnection();		
		TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Selecting correct Topic.
		Topic selectedTopic; 
		if(args[0].equalsIgnoreCase("soccer")) selectedTopic = soccer;
		else if(args[0].equalsIgnoreCase("tennis")) selectedTopic = tennis;
		else { System.out.println("unknown topic"); return;}
		
		//Sending the message
		TopicPublisher publisher = ts.createPublisher(selectedTopic);
		TextMessage message = ts.createTextMessage(args[1]);
		message.setIntProperty("propInt", 10); // can be used by possible message selectors by subscribers  
		
		publisher.publish(message);
		
		System.out.println("I've published the message " + args[1]);

		//Some attribues of the message
		System.out.println("\nMessage id "+message.getJMSMessageID());
		System.out.println("Message id "+message.getJMSCorrelationID()); // not set
		System.out.println("Message id "+message.getJMSDeliveryMode()); //PERSISTENT, NON-PERSISTENT
		System.out.println("Message id "+message.getJMSExpiration());
		System.out.println("Message id "+message.getJMSPriority());
		System.out.println("Message id "+message.getJMSReplyTo());
		
		//Closing resources.
		publisher.close();
		ts.close();
		tc.close();

	}

}
