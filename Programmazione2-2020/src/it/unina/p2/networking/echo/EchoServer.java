package it.unina.p2.networking.echo;
// EchoServer Class
// EchoServer.java
// Simple file for demonstrating Java socket programming...
// Server side: waits for a client to send it a string and echoes 
// the string back to the client
// Usage	: java EchoServer <EM> &lt port &gt</EM> <EM> &lt max_client_number &gt</EM><BR> 

// @author	Dom
// @version	v0.01 (23/11/1999)
// References: Login magazine, november 1999

// Imports
import	java.net.ServerSocket;
import	java.net.Socket;
import	java.io.InputStreamReader;
import	java.io.BufferedReader;
import	java.io.OutputStreamWriter;
import	java.io.BufferedWriter;
import	java.io.IOException;

public class EchoServer {
	
	// Default port
	public static final int	DEFAULT_PORT=9000;
	
	// Default number of queued users' requests
	public static final int	DEFAULT_MAX_CLIENT_NUMBER=10;	
	
	
	// Server port
	private	int	port;	
	
	// Max length of requests queue
	private	int	max_client_number;

	// Default constructor
	public EchoServer(){
		this(DEFAULT_PORT);
	} 

	
	// Constructor with specified port 
	// @param	port	ECHO service PORT
	public EchoServer(int port){
		this(port,DEFAULT_MAX_CLIENT_NUMBER);
	}


	// Constructor with specified port and queue length
	// @param	port ECHO servce PORT
	// @param	max_client_number	Max number of clients
	public EchoServer(int port, int max_client_number){
		System.out.println("Server active on port "+port+" with max_num_clients="+max_client_number);
		this.port=port;
		this.max_client_number=max_client_number;
	}
		
		
	// Run method
	public void run(){
		try{
			// Create a server on the specified port
			ServerSocket 	server = new ServerSocket(port,max_client_number);
			
			// Infinite loop			
			while(true){
				// Wait for a connection
				Socket socket_from_client=server.accept();
				System.out.println("Accepted connection from client port: "+socket_from_client.getPort());
				
				while(true) {
				// Create a reader & read a line from the client
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket_from_client.getInputStream()));
				System.out.println("\nReading...");
				String text_line=reader.readLine() + "\n";
				System.out.print("*** Received string: "+text_line);
				System.out.println("Done!!");
				if(text_line.equalsIgnoreCase("****\n")) { 
					System.out.println("Client exiting!!...Closing socket and going back waiting... ");
					break;
				}
				
				// Create a writer & write back to the client
				BufferedWriter 	writer= new BufferedWriter(new OutputStreamWriter(socket_from_client.getOutputStream()));
				System.out.println("Echoing to client...");
				writer.write(text_line,0,text_line.length());
				System.out.println("Done!!");

				
				// Empty the buffer
				writer.flush();
				
				}
								
				// Close client socket e start waiting once again
				socket_from_client.close();
			}// fine while
		}catch(IOException ioe){
			System.out.println("Errore di IO "+ioe);
		}// fine try/catch
	}// fine run
	
		
		
		
		
	
	// Main 		
	public static void main(String str[]){
		if (str.length>2){
			System.out.println("Usage : java EchoServer <port> <max_client_number>");
			System.exit(0);
		}
		EchoServer	echo_server=null;
		if (str.length==1)
			echo_server= new EchoServer(converti(str[0],DEFAULT_PORT),DEFAULT_MAX_CLIENT_NUMBER);
		else if (str.length==2)
						echo_server= new EchoServer(converti(str[0],DEFAULT_PORT),converti(str[1],DEFAULT_MAX_CLIENT_NUMBER));
		else echo_server= new EchoServer(DEFAULT_PORT,DEFAULT_MAX_CLIENT_NUMBER);
		// Start the server
		if(echo_server!=null){
			echo_server.run();
		}
	}
	
	
	// Utility function 
	private	static final int converti(String str, int default_value){
		if(str==null) return default_value;
		try{
			return Integer.parseInt(str);
		}catch(NumberFormatException nfe){
			return default_value;
		}
	}
	
	
}// fine classe
