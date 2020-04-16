package it.unina.p2.esercitazione.threads;

import java.io.Serializable;



public class Contatto implements Serializable, Comparable<Contatto> {
	
	private static final long serialVersionUID = -4059846979942113468L;

	protected String nome = "Unknown";
	protected String cognome = "Unknown";
	protected long numero = 0;
	protected String email = "";
	
	
	public Contatto() {
		this.nome = "Unknown";
		this.cognome = "Unknown";
		this.numero = 0;
		this.email = "";
	}

	
	public Contatto(String nome, String cognome, long numero, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
		this.email = email;
	}
	
	
	public Contatto(String nome, String cognome, long numero) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
		this.email = "";
	}
	
	
	public Contatto(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = 0;
		this.email = email;
	}
	

	
	@Override
	public int compareTo(Contatto o) {

		if( this.cognome.compareTo(o.cognome) != 0 ) {
			return this.cognome.compareTo(o.cognome);
		}
		else {
			return this.nome.compareTo(o.nome);
		}
	}
	
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + " " + this.cognome + ", Telefono: " + this.numero + 
		       ", E-mail: " + this.email;
	}

}