package it.unina.p2.networking.chat;
//Java implementation for multithreaded chat client 
//Save file as Client.java 

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Client 
{ 
	final static int ServerPort = 1234; 

	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
		Scanner scn = new Scanner(System.in); 
		
		// getting localhost ip 
		InetAddress ip = InetAddress.getByName("localhost"); 
		
		// establish the connection 
		Socket s = new Socket(ip, ServerPort); 
		
		// obtaining input and out streams 
		DataInputStream dis = new DataInputStream(s.getInputStream()); 
		DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

		System.out.println("Client started, with socket: "+s);
		
		// sendMessage thread 
		Thread sendMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 
				while (true) { 
					
					System.out.println("Please, type your message in this format: message#receipent"); 
					// read the message to deliver. 
					String msg = scn.nextLine(); 
					if(!s.isClosed()) {
						try { 
							// write on the output stream 
							dos.writeUTF(msg); 
						} catch (IOException e) { 
							e.printStackTrace(); 
						}
						if(msg.equals("logout")) 
							break;
						}
					else {
						System.out.println("Connection is closed");
						break;
					}
				} 
			} 
		}); 
		
		// readMessage thread 
		Thread readMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 

				while (true) { 
					try { 
						// read the message sent to this client 
						String msg = dis.readUTF(); 
						
						if(msg.equals("logout")){ 
							System.out.println("Logged out");
							s.close();
							break; 
						} 
						
						System.out.println("I received the following message "+msg);
						 
					} catch (IOException e) { 

						e.printStackTrace(); 
					} 
				} 
			} 
		}); 

		sendMessage.start(); 
		readMessage.start(); 

	} 
} 

