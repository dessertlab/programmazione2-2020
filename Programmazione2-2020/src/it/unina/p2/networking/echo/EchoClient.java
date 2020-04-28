package it.unina.p2.networking.echo;
// EchoClient Class
// EchoClient.java
// Simple file for demonstrating Java socket programming...
// Client side: reads a commandline, sends it to the server
// and waits for an echo
// Usage	: java EchoClient <EM>  server address </EM> <BR>
// @author	Dom
// @version	v0.01 (23/11/1999)



// Imports
import  java.net.Socket;
import	java.io.InputStreamReader;
import	java.io.BufferedReader;
import	java.io.OutputStreamWriter;
import	java.io.BufferedWriter;
import	java.io.IOException;
import  java.net.InetAddress;

// 
public class EchoClient {
  private static int localPort;
  private static final int SERVERPORT=9000;

  public static void main(String[] args) {
    Socket          socket=null;
    String          address;

    // Check the command-line args for the host address
    if (args.length != 2) {
      System.out.println("Usage: java EchoClient <server address> <local port>");
      return;
    }else{
      address = args[0];
      localPort= Integer.parseInt(args[1]);
      System.out.println("Remote address="+address+" local port="+localPort);
    }
    // Initialize the socket and streams
    try {
      socket = new Socket(address, SERVERPORT, InetAddress.getLocalHost() ,localPort);
    }
    catch (IOException e) {
      System.err.println("Exception: couldn't create stream socket");
      System.exit(1);
    }
			
    // Process user input and server responses
    try {
      
      // Create the command-line reader
      BufferedReader kbd_reader=new BufferedReader(new InputStreamReader(System.in));
      // Create the writer
	  BufferedWriter 	writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	  // Create the reader
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      while(true) {
    		// Read a string from the command line  
    		System.out.println("\nInsert sentence to be echoed from server...");    	
		    String str = kbd_reader.readLine();
	    	System.out.println("Input String: " + str);
      	    System.out.println("Sending to server...");
    	
   			// Send the line to the sender ("\n"-terminated)
			writer.write(str + "\n",0,str.length()+1);
			System.out.println("String sent!!");
    		// Empty writer's buffer
			writer.flush();

    		// Read server's answer
			System.out.println("Reading server's answer...");    
			String answer=reader.readLine();      
			System.out.println("Answer from server: " + answer);

    		// Check for termination
    		System.out.println("Do you want to continue (y/n)?");
    		String response = kbd_reader.readLine();
   			if (response.equalsIgnoreCase("n"))  {
   				String str_stop = "****\n";
   				writer.write(str_stop,0,str_stop.length());
   				break;
   			}
      }
      
      // Cleanup
      writer.close();
      reader.close();
      socket.close(); 
    
    }
    catch (IOException e) {
      System.err.println("Exception: I/O error trying to talk to server");
    }
    
  }
}