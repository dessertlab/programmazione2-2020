package it.unina.p2.networking.brokersportello.broker;

public interface IBroker {

	int inoltraRichiesta(ClientRequest cReq) throws IDClientInvalidoException;
	void sottoscrivi(int port);
}
