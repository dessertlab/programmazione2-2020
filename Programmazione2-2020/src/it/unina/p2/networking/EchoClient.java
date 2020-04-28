package it.unina.p2.networking;
/**
 *semplice programma echo che mostra l'uso delle socket in Java
 *
 *il client chiede una stringa in ingresso, la invia al server e attende che le venga reinviata
*/

import  java.net.Socket;
import java.net.UnknownHostException;
import	java.io.InputStreamReader;
import	java.io.BufferedReader;
import	java.io.OutputStreamWriter;
import	java.io.BufferedWriter;
import	java.io.IOException;
import java.io.Writer;
import  java.net.InetAddress;
import java.lang.*;
import java.util.HashMap;
import java.util.Map; 

public class EchoClient {
  private static int localPort;
  private static int serverPort;

  public static void main(String[] args) {
    
    Socket socket=null;
    String address;

    //richiede in ingresso indirizzo server, porto server e porto locale da cui inviare
    if (args.length != 3) {
      System.out.println("Usage: java EchoClient <server address> <server port> <local port>");
      return;
    }else{
      address = args[0];
      serverPort = Integer.parseInt(args[1]);
      localPort= Integer.parseInt(args[2]);
      System.out.println("indirizzo remoto="+address+" porto remoto="+serverPort+" porto locale="+localPort);
    
      
    }
    //inizializza la socket
    
    try {
    	socket = new Socket(address, serverPort, InetAddress.getLocalHost() ,localPort);
    }
    catch (IOException e) {
      System.err.println("Exception: couldn't create stream socket");
      e.printStackTrace();
      System.exit(1);
    }
			
    //crea uno stream per leggere da tastiera, uno per inviare attraverso la socket, uno per leggere da socket
    try {
      //lettore da riga di comando
      BufferedReader kbd_reader=new BufferedReader(new InputStreamReader(System.in));
      //scrittore su socket
	  BufferedWriter 	writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	  //lettore da socket
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      while(true) {
    		//lettura della stringa inserita
    		System.out.println("\ninserire la frase da inviare al server");    	
		    String str = kbd_reader.readLine();
	    	System.out.println("stringa in ingresso: " + str);
      	    System.out.println("invio al server...");
    	
   			//invio della frase al server ("\n"-terminated)
			writer.write(str + "\n",0,str.length()+1);
			System.out.println("frase inviata!!");
    		//svuota il buffer
			writer.flush();

    		//lettura della risposta del server
			System.out.println("lettura dal server...");    
			String answer=reader.readLine();      
			System.out.println("frase ricevuta dal server: " + answer);

    		//vuoi continuare o termino??
    		System.out.println("vuoi continuare (y/n)?");
    		String response = kbd_reader.readLine();
   			if (response.equalsIgnoreCase("n"))  {
   				String str_stop = "****\n";
   				writer.write(str_stop,0,str_stop.length());
   				break;
   			}
      }
      
      //chiusura degli stream e della socket
      writer.close();
      reader.close();
      socket.close(); 
    
    }
    catch (IOException e) {
      System.err.println("Exception: eccezione di I/O nella comunicazione con il server");
    }
    
  }
}