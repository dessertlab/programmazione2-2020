package it.unina.p2.io;

import java.io.Serializable;

public class Person implements Serializable {	

	private static final long serialVersionUID = 3214640289299418432L;
	
	private String name   = "Unknown";
	private String gender = "Unknown" ;
	private double height = Double.NaN;
	
	public Person(String name, String gender, double height) {
		this.name = name;
		this.gender = gender;
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + ", Gender: " + this.gender + 
		       ", Height: " + this.height;   
	}
}