package it.unina.p2.networking;
import java.net.*;
import java.io.*;
public class GetFile {
  URL[] pages;
   
  public GetFile() {
      try {
    	  int numberOfPages = 3; // Example with 3 pages to read
    	  pages= new URL[numberOfPages];
    	  
    	  
    	  // creates a URL with string representation. 
    	  pages[0] = new URL("https://www.google.it"); 
  

    	  // creates a URL with a protocol,hostname,and path 
    	  pages[1] = new URL("http", "wpage.unina.it", 
                      "/roberto.pietrantuono/"); 

    	  pages[2] = new URL("https://www.google.co.in/?gfe_rd=cr&ei=ptYq" + 
                  "WK26I4fT8gfth6CACg#q=programmazione+java"); 

     
      } catch (MalformedURLException e) {
		System.out.println("Bad URL ");
		
       }

  }

  public static void main(String args[]) {
	
	  URLConnection conn=null;
	  InputStreamReader in;
	  BufferedReader data;
	  String line;
	  StringBuffer buf=new StringBuffer();
	  GetFile urlw=new GetFile();

	  try {
		  conn=urlw.pages[0].openConnection();
		  conn.connect();
		  System.out.println("\n\nConnessione aperta primo URL\n");
		  in = new InputStreamReader(conn.getInputStream());
		  data=new BufferedReader(in);
		  
		  System.out.println("Lettura dati...\n\n");

		  while ((line=data.readLine())!=null) {
			  buf.append(line+"\n");
		  }
	
		  System.out.print (buf.toString());
		 
	  } catch(IOException e){
	 	  System.out.println("Errore IO primo URL: "+e.getMessage());
      }		
	  
	  try {
		  conn=urlw.pages[1].openConnection();
		  conn.connect();
		  System.out.println("\n\nConnessione aperta secondo URL\n");
		  in = new InputStreamReader(conn.getInputStream());
		  data=new BufferedReader(in);
		  System.out.println("Lettura dati...\n\n");
		 
		  while ((line=data.readLine())!=null) {
			  buf.append(line+"\n");
		  }
	
		  System.out.print (buf.toString());
	    
	  } catch(IOException e){
	 	  System.out.println("Errore IO secondo URL: "+e.getMessage());
      }		
	  
	  try {
		  conn=urlw.pages[2].openConnection();
		  conn.connect();
		  System.out.println("\n\nConnessione aperta terzo URL\n");
		  in = new InputStreamReader(conn.getInputStream());
		  data=new BufferedReader(in);
		  System.out.println("Lettura dati...\n\n");

		  while ((line=data.readLine())!=null) {
			  buf.append(line+"\n");
		  }
	
		  System.out.print (buf.toString());
	    
		  // print the string representation of the URL. 
		     System.out.println(urlw.pages[0].toString()); 
		     System.out.println(urlw.pages[1].toString()); 
		     System.out.println(); 
		     System.out.println("Parti dell'URL 3\n\n"); 

		     // retrieve the protocol for the URL 
		     System.out.println("Protocol:- " + urlw.pages[2].getProtocol()); 

		     // retrieve the hostname of the url 
		     System.out.println("Hostname:- " + urlw.pages[2].getHost()); 

		     // retrieve the defalut port 
		     System.out.println("Default port:- " + 
		    		 urlw.pages[2].getDefaultPort()); 

		     // retrieve the query part of URL 
		     System.out.println("Query:- " + urlw.pages[2].getQuery()); 

		     // retrive the path of URL 
		     System.out.println("Path:- " + urlw.pages[2].getPath()); 

		     // retrive the file name 
		     System.out.println("File:- " + urlw.pages[2].getFile()); 

		     // retrieve the reference pages[3]
		     System.out.println("Reference:- " + urlw.pages[2].getRef()); 
		     
	  } catch(IOException e){
	 	  System.out.println("Errore IO terzo URL: "+e.getMessage());
      }		
 }// main
}// class
