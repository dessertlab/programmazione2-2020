package it.unina.p2.esercitazione.io;

import java.util.StringTokenizer;

public class ContattoCSV implements Comparable<ContattoCSV> {	

	protected String nome = "Unknown";
	protected String cognome = "Unknown";
	protected long numero = 0;
	protected String email = "";
	
	
	public ContattoCSV() {
		this.nome = "Unknown";
		this.cognome = "Unknown";
		this.numero = 0;
		this.email = "";
	}

	
	public ContattoCSV(String nome, String cognome, long numero, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
		this.email = email;
	}
	
	
	public ContattoCSV(String nome, String cognome, long numero) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = numero;
		this.email = "";
	}
	
	
	public ContattoCSV(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.numero = 0;
		this.email = email;
	}
	
	

	public ContattoCSV(String csv_line) {
		

		StringTokenizer tokenizer = new StringTokenizer(csv_line, "#:");
		
		this.nome = tokenizer.nextToken();
		this.cognome = tokenizer.nextToken();
		

		
		while(tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();

			if(token.equals("tel") && tokenizer.hasMoreTokens()) {
				
				this.numero = Long.parseLong( tokenizer.nextToken() );
			}

			else if(token.equals("email") && tokenizer.hasMoreTokens()) {

				this.email = tokenizer.nextToken();
			}
		}


	}
	
	
	@Override
	public int compareTo(ContattoCSV o) {

		if( this.cognome.compareTo(o.cognome) != 0 ) {
			return this.cognome.compareTo(o.cognome);
		}
		else {
			return this.nome.compareTo(o.nome);
		}
	}

	
	@Override
	public String toString() {
		
		return this.nome + "#" + this.cognome + 
				(this.numero != 0 ? "#tel:"+this.numero : "") + 
				(!this.email.equals("") ? "#email:"+this.email : "");
	}



}