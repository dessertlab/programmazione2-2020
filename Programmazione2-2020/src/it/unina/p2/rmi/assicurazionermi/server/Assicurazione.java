package it.unina.p2.rmi.assicurazionermi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Assicurazione extends UnicastRemoteObject implements IAssicuratore, IVerifica {


	protected Assicurazione() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 5201930762314053294L;
	private ArrayList<Polizza> polizze = new ArrayList<>();
	

	@Override
	public void registra(Polizza p) throws RemoteException {
		
		System.out.println("[ServerAssicuratore] Registrazione polizza: "+p);
		
		polizze.add(p);

	}

	@Override
	public void rinnova(String targa, long scadenza) throws RemoteException, TargaNotFoundException {
		
		System.out.println("[ServerAssicuratore] Rinnovo polizza: "+targa+ ", " + scadenza);

		
		for(Polizza p : polizze) {
			
			if(p.getTarga().equals(targa)) {
				
				p.setScadenza(scadenza);
				
				return;
			}
		}
		
		throw new TargaNotFoundException();
	}
	
	@Override
	public ArrayList<String> getTarghe() throws RemoteException {
		
		ArrayList<String> lista_targhe = new ArrayList<>();
		
		for(Polizza p : polizze) {
			
			lista_targhe.add( p.getTarga() );
		}
		
		return lista_targhe;
	}
	

	@Override
	public boolean verifica(String targa) throws RemoteException, TargaNotFoundException {

		for(Polizza p : polizze) {
			
			if(p.getTarga().equals(targa)) {
				
				long scadenza = p.getScadenza();
				
				if(scadenza < System.currentTimeMillis()) {
					
					return false;
				}
				else {
					
					return true;
				}
			}
		}
		
		
		throw new TargaNotFoundException();
	}


}
