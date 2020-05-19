package it.unina.p2.rmi.negoziormi.autenticazione;

import java.io.Serializable;

public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1775405461339509888L;

	private String nomeutente;
	private long randomvalue;
	private long scadenza;
	
	
	public Token(String nomeutente, long randomvalue, long scadenza) {
		super();
		this.nomeutente = nomeutente;
		this.randomvalue = randomvalue;
		this.scadenza = scadenza;
	}


	public String getNomeutente() {
		return nomeutente;
	}


	public void setNomeutente(String nomeutente) {
		this.nomeutente = nomeutente;
	}


	public long getRandomvalue() {
		return randomvalue;
	}


	public void setRandomvalue(long randomvalue) {
		this.randomvalue = randomvalue;
	}


	public long getScadenza() {
		return scadenza;
	}


	public void setScadenza(long scadenza) {
		this.scadenza = scadenza;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeutente == null) ? 0 : nomeutente.hashCode());
		result = prime * result + (int) (randomvalue ^ (randomvalue >>> 32));
		result = prime * result + (int) (scadenza ^ (scadenza >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
		if (nomeutente == null) {
			if (other.nomeutente != null) {
				return false;
			}
		} else if (!nomeutente.equals(other.nomeutente)) {
			return false;
		}
		if (randomvalue != other.randomvalue) {
			return false;
		}
		if (scadenza != other.scadenza) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Token [nomeutente=" + nomeutente + ", randomvalue=" + randomvalue + ", scadenza=" + scadenza + "]";
	}



}
