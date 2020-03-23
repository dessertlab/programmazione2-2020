package it.unina.p2.esercitazione.io;

import java.io.Serializable;

public class ContattoSerializable extends ContattoCSV implements Serializable {
	
	private static final long serialVersionUID = -4059846979942113468L;

	public ContattoSerializable() {
		super();
	}


	public ContattoSerializable(String nome, String cognome, long numero, String email) {
		super(nome, cognome, numero, email);

	}
	
	
	public ContattoSerializable(String nome, String cognome, long numero) {
		super(nome, cognome, numero);

	}
	
	
	public ContattoSerializable(String nome, String cognome, String email) {
		super(nome, cognome, email);
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.nome + " " + this.cognome + ", Telefono: " + this.numero + 
		       ", E-mail: " + this.email;
	}

}