package it.unina.p2.collections.comparison;

import java.util.ArrayList;
import java.util.Collections;

public class ComparatorExampleMain {

	public static void main(String[] args) 
	{ 
		ArrayList<Movie2> list = new ArrayList<Movie2>(); 
		list.add(new Movie2("Force Awakens", 8.3, 2015)); 
		list.add(new Movie2("Star Wars", 8.7, 1977)); 
		list.add(new Movie2("Empire Strikes Back", 8.8, 1980)); 
		list.add(new Movie2("Return of the Jedi", 8.4, 1983)); 

		// Sort by rating : (1) Create an object of ratingCompare 
		//				 (2) Call Collections.sort 
		//				 (3) Print Sorted list 
		System.out.println("Sorted by rating"); 
		RatingCompare ratingCompare = new RatingCompare(); 
		Collections.sort(list, ratingCompare); 
		for (Movie2 movie: list) 
			System.out.println(movie.getRating() + " " + 
							movie.getName() + " " + 
							movie.getYear()); 


		// Call overloaded sort method with RatingCompare 
		// (Same three steps as above) 
		System.out.println("\nSorted by name"); 
		NameCompare nameCompare = new NameCompare(); 
		Collections.sort(list, nameCompare); 
		for (Movie2 movie: list) 
			System.out.println(movie.getName() + " " + 
							movie.getRating() + " " + 
							movie.getYear()); 

		// Uses Comparable to sort by year 
		System.out.println("\nSorted by year"); 
		Collections.sort(list); 
		for (Movie2 movie: list) 
			System.out.println(movie.getYear() + " " + 
							movie.getRating() + " " + 
							movie.getName()+" "); 
	} 
} 