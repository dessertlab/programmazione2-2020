package it.unina.p2.networking.echo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

class Worker implements  Runnable {
    final static int BUF_SIZE = 2048;

    static final byte[] EOL = {(byte)'\r', (byte)'\n' };

    // buffer delle richieste
    byte[] buf;
    // Socket per il client che stiamo servendo
    private Socket s;

    Worker(Socket s) {
//        buf = new byte[BUF_SIZE];
        this.s = s;
    }



    public synchronized void run() { 
    	System.out.println("New Worker for client on port:"+s.getPort());
    	int count=0;
            try {
            	while(true) {
    				// Create a reader & read a line from the client
    				BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
    				//System.out.println("\nReading...");
    				String text_line=reader.readLine() + "\n";
    				//System.out.print("stringa ricevuta: "+text_line);
    				//System.out.println("Done!!");
    				if(text_line.equalsIgnoreCase("****\n")) { 
    					System.out.println("Client exiting!!...Closing socket... ");
    					break;
    				}
    				// Create a writer & write back to the client
    				BufferedWriter 	writer= new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    				//System.out.println("Echoing to client...");
    				writer.write(text_line,0,text_line.length());
    				System.out.println(count+") "+s.getPort()+" received string: "+text_line+" reply sent"); 				
    				// Empty the buffer
    				writer.flush();
    				count++;
    				}

//          Close client socket e start waiting once again
			s.close();
           } catch (Exception e) {
                e.printStackTrace();
           }
}
        }
    
