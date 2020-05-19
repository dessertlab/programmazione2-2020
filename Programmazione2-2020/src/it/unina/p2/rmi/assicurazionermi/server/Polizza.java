package it.unina.p2.rmi.assicurazionermi.server;

import java.io.Serializable;

public class Polizza implements Serializable {

	@Override
	public String toString() {
		return "Polizza [conducente=" + conducente + ", auto=" + auto + ", targa=" + targa + ", scadenza=" + scadenza
				+ "]";
	}

	public Polizza(String conducente, String auto, String targa, long scadenza) {
		super();
		this.conducente = conducente;
		this.auto = auto;
		this.targa = targa;
		this.scadenza = scadenza;
	}
	
	public String getConducente() {
		return conducente;
	}
	public void setConducente(String conducente) {
		this.conducente = conducente;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public long getScadenza() {
		return scadenza;
	}
	public void setScadenza(long scadenza) {
		this.scadenza = scadenza;
	}
	private String conducente;
	private String auto;
	private String targa;
	private long scadenza;
	
}
