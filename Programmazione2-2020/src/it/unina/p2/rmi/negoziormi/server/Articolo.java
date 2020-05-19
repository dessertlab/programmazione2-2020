package it.unina.p2.rmi.negoziormi.server;

import java.io.Serializable;

public class Articolo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3271735339232187664L;
	private String nome;
	private Categoria categoria;
	private int costo;
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Articolo(String nome, Categoria categoria, int costo) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.costo = costo;
	}


	@Override
	public String toString() {
		return "Articolo [nome=" + nome + ", categoria=" + categoria + ", costo=" + costo + "]";
	}
	
}
