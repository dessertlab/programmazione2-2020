package it.unina.p2.networking.chat;

//Java implementation of Server side 
//It contains two classes : Server and ClientHandler 
//Save file as Server.java 

import java.io.*; 
import java.util.*; 
import java.net.*; 

//Server class 
public class Server 
{ 

	// ArrayList to store active clients 
	static ArrayList<ClientHandler> ar = new ArrayList<>(); 
	
	// counter for clients 
	static int i = 0; 

	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 1234 
		ServerSocket ss = new ServerSocket(1234); 
		
		Socket s; 
		
		System.out.println("Server started, waiting for incoming connections...");
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			 

			// Accept the incoming request 
			s = ss.accept(); 

			System.out.println("\n\nNew client connection request received : " + s); 
			
			// obtain input and output streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			
			System.out.println("Creating a new handler for this client..."); 

			// Create a new handler object for handling this request. 
			ClientHandler mtch = new ClientHandler(s,String.valueOf(i), dis, dos); 

			// Create a new Thread with this object. 
			Thread t = new Thread(mtch); 
			
			System.out.println("Adding this client to active client list"); 

			// add this client to active clients list 
			ar.add(mtch); 

			System.out.println("The list of active clients is: ");
			for (ClientHandler c:ar) {
				System.out.println(c.getName());
			}

			
			// start the thread. 
			t.start(); 

			// increment i for new client. 
			// i is used for naming only, and can be replaced 
			// by any naming scheme 
			i++; 

		} 
	} 
} 

//ClientHandler class 
class ClientHandler implements Runnable 
{ 
	Scanner scn = new Scanner(System.in); 
	private String name; 
	

	final DataInputStream dis; 
	final DataOutputStream dos; 
	Socket s; 
	boolean isloggedin; 
	
	// constructor 
	public ClientHandler(Socket s, String name, 
							DataInputStream dis, DataOutputStream dos) { 
		this.dis = dis; 
		this.dos = dos; 
		this.name = name; 
		this.s = s; 
		this.isloggedin=true; 
	} 

	public String getName() {
		return name;
	}
	
	@Override
	public void run() { 

		
		System.out.println("Handler for client: "+this.name+", with socket: "+s);
		System.out.println("Waiting for messages...\n");
		
		String received; 
		while (true) 
		{ 
			try
			{ 
				// receive the string 
				received = dis.readUTF(); 
				
				
				if(received.equals("logout")){ 
					System.out.println("Received logout message from client "+this.name);
					this.isloggedin=false; 
					System.out.println("Disconnecting from server...");
					this.dos.writeUTF("logout");
					System.out.println("Logged out");
					this.s.close(); 
					break; 
				} 
				
				// break the string into message and recipient part 
				StringTokenizer st = new StringTokenizer(received, "#"); 
				String MsgToSend = st.nextToken(); 
				String recipient = st.nextToken(); 

				System.out.println("Text Received from client "+this.name+ " to client "+recipient+": "+MsgToSend); 

				// search for the recipient in the connected devices list. 
				// ar is the arraylist storing client of active users 
				for (ClientHandler mc : Server.ar) 
				{ 
					// if the recipient is found, write on its 
					// output stream 
					if (mc.name.equals(recipient) && mc.isloggedin==true) 
					{ 
						mc.dos.writeUTF("from client "+this.name+": "+MsgToSend);
						break; 
					} 
				} 
			} catch (IOException e) { 
				
				e.printStackTrace(); 
			} 
			
		} 
		try
		{ 
			// closing resources 
			this.dis.close(); 
			this.dos.close(); 
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
} 

