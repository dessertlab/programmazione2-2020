package it.unina.p2.proxyskeleton.counter;

public class Client{ 

	public static void main(String args[]) { 
	
		String host = args[0];  
		String operation = args[1];
	    if ((!operation.equals("sum")&&
	    	!operation.equals("get")&&
	    	!operation.equals("sqr")&&
	    	!operation.equals("inc"))||(args.length<2)) {
	    		System.out.println("Uso: java Client <host> <comando>. Comando ERRATO! \n Comandi accettati: sum <value>, get, inc, sqr");
	    } else {
	    	
	    	// creazione del proxy del contatore remoto
	    	ICounter counter = new CounterProxy(host,2500);
	    //	ICounter counter = new Counter();
	    	
	    	if (operation.equals("sum")) {
	    		try {
	    			int value = Integer.parseInt(args[2]);
	    			counter.sum(value); // utilizzo del contatore come se fosse locale
	    		} catch (NumberFormatException e) {
	    			System.out.println ("ERRORE: l'argomento deve essere un numero!");
	    		}		
	    	} else if (operation.equals("get")) {
	    		System.out.println("Valore letto: "+counter.get());
	    	} else if (operation.equals("sqr")) {
	    		counter.square();
	    	}
	    	else if (operation.equals("inc")) {
	    		counter.inc();
	    	}
	    }

	}
}
