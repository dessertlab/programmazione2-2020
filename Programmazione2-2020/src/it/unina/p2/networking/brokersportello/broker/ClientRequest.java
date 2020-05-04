package it.unina.p2.networking.brokersportello.broker;

public class ClientRequest {
	
	public ClientRequest(int clientID, int requestID) {
		super();
		this.clientID = clientID;
		this.requestID = requestID;
	}
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	
	private int clientID;
	private int requestID;
	
	
	public String toString() {
		return this.clientID + "," +this.requestID;
	}
}
