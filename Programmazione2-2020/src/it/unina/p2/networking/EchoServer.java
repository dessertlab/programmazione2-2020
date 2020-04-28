package it.unina.p2.networking;
/**
 *semplice programma echo che mostra l'uso delle socket in Java
 *
 *il server si mette in ascolto su un porto di una stringa da un client di cui fare eco
*/

import	java.io.InputStreamReader;
import	java.io.BufferedReader;
import	java.io.OutputStreamWriter;
import	java.io.BufferedWriter;
import	java.io.IOException;
import  java.net.*;

public class EchoServer extends Thread {
	
	//porto di default del server
	public static final int	DEFAULT_PORT=9000;
	
	//massimo numero di client
	public static final int	DEFAULT_MAX_CLIENT_NUMBER=10;	

	private	int	port;	
	private	int	max_client_number;


	//costruttore; richiede porto del server e numero max di client consentiti
	public EchoServer(int port, int max_client_number){
		super("EchoServer on port " +port);
		try{
			System.out.println("Server active at address "+	InetAddress.getLocalHost()+ " on port "+port+" with max_num_clients="+max_client_number);	
		}catch(UnknownHostException e){
			System.err.println("Exception: unable to identify local address");
      		e.printStackTrace();
		}
		this.port=port;
		this.max_client_number=max_client_number;
	}
		
		
	//run
	public void run(){
		try{
			//creazione socket server
			ServerSocket 	server = new ServerSocket(port,max_client_number);
			
			//si pone in attesa di una richiesta di connessione di un client
			while(true){				
				Socket socket_from_client=server.accept();
				System.out.println("connessione dal client al porto: "+socket_from_client.getPort());
				
				while(true) {
				//lettura dello stream ricevuto dal client
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket_from_client.getInputStream()));
				System.out.println("\nleggo...");
				String text_line=reader.readLine() + "\n";
				System.out.print("stringa ricevuta: "+text_line);
				System.out.println("fatto!");
				//controlla se terminare o attendere altre stiringhe
				//in accordo con il codice del client *** indica il termine della comunicazione
				if(text_line.equalsIgnoreCase("****\n")) { 
					System.out.println("client terminato; chiusura socket... ");
					break;
				}
				
				//crea un buffer per la echo al client
				BufferedWriter 	writer= new BufferedWriter(new OutputStreamWriter(socket_from_client.getOutputStream()));
				System.out.println("reinvio al client...");
				writer.write(text_line,0,text_line.length());
				System.out.println("fatto!");
				
				//svuota il buffer
				writer.flush();
				
				}
								
				//chiude la socket e si pone in attesa per un'altra connessione da porto diverso
				socket_from_client.close();
			}// fine while
		}catch(IOException ioe){
			System.out.println("Errore di IO "+ioe);
		}// fine try/catch
	}// fine run
	
	
	//main
	public static void main(String str[]){
	
		EchoServer echo_server= new EchoServer(DEFAULT_PORT,DEFAULT_MAX_CLIENT_NUMBER);
		//avvio del server (run)
		if(echo_server!=null){
			echo_server.start();
		}
	}
	
	
}// fine classe
