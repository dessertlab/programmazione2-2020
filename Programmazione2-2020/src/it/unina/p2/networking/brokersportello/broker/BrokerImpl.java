package it.unina.p2.networking.brokersportello.broker;

import java.util.ArrayList;

import it.unina.p2.sportello.ISportello;
import it.unina.p2.sportello.SportelloProxy;

public class BrokerImpl extends BrokerSkeleton {

	
	private ArrayList<ISportello> sportelli = new ArrayList<>();
	
	private int nextSportello = 0;
	
	
	@Override
	public synchronized int inoltraRichiesta(ClientRequest cReq) throws IDClientInvalidoException {
		

		System.out.println("[Broker] Invocazione inoltraRichiesta: " + cReq);

		if(cReq.getClientID()<1 || cReq.getClientID()>50) {
				throw new IDClientInvalidoException();
		}
		

		if(sportelli.size() > 0) {
			
			System.out.println("[Broker] Selezionato sportello " + nextSportello);
			
			sportelli.get(nextSportello).serviRichiesta(cReq.getClientID(), cReq.getRequestID());
			nextSportello = (nextSportello + 1) % sportelli.size();
		}
		
		return 0;
	}
	

	@Override
	public synchronized void sottoscrivi(int port) {
		
		System.out.println("[Broker] Aggiunta nuovo sportello, port " + port);
		
		SportelloProxy sportello = new SportelloProxy();
		sportello.setPort(port);

		sportelli.add(sportello);

		System.out.println("[Broker] Totale sportelli: " + sportelli.size());

	}

}
