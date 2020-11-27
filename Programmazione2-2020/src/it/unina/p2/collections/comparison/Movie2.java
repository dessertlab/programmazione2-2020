package it.unina.p2.collections.comparison;

//A Java program to demonstrate Comparator interface (modified from GeeksforGeeks)
import java.io.*; 
import java.util.*; 

//A class 'Movie' that implements Comparable 
public class Movie2 implements Comparable<Movie2> 
{ 
	private double rating; 
	private String name; 
	private int year; 

	// Used to sort movies by year 
	public int compareTo(Movie2 m) 
	{ 
		return this.year - m.getYear(); 
	} 

	// Constructor 
	public Movie2(String nm, double rt, int yr) 
	{ 
		this.name = nm; 
		this.rating = rt; 
		this.year = yr; 
	} 

	// Getter methods for accessing private data 
	public double getRating() { return rating; } 
	public String getName() { return name; } 
	public int getYear()	 { return year; } 
} 

//Class to compare Movies by ratings 
class RatingCompare implements Comparator<Movie2> 
{ 
	public int compare(Movie2 m1, Movie2 m2) 
	{ 
		if (m1.getRating() < m2.getRating()) return -1; 
		if (m1.getRating() > m2.getRating()) return 1; 
		else return 0; 
	} 
} 

//Class to compare Movies by name 
class NameCompare implements Comparator<Movie2> 
{ 
	public int compare(Movie2 m1, Movie2 m2) 
	{ 
		return m1.getName().compareTo(m2.getName()); 
	} 
} 

