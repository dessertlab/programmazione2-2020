package it.unina.p2.collections;

import java.util.*;
import org.apache.commons.lang.*;
import org.apache.commons.lang.mutable.MutableInt;

public class Examples {

	static Integer staticInt =3; 
	static MutableInt staticIntMutable = new MutableInt(3); 
	public static void main(String[] args) {

		
		/* Arrays
		int[] array = new int[4]; 
		array[0] = 3;
		array[1] = 9;
		array[2] = 1;
		String[] stringArray = new String[]{"A", "B", "C"};
		
		//The class Arrays has methods to work with arrays
		Arrays.sort(array);
		List<String> al = Arrays.asList(stringArray);
		*/
		
		 
		
		/*String and Wrappers*/
		//construction
		String string1 = new String("bye");
		String string2 = "bye";
		
		//String objects are immutable
		string1 = "hello"; 
		//causes the creation of a new String, and the previous object will garbage-collected
		
		//StringBuffer is Mutable
		StringBuffer strbuf = new StringBuffer("string buf"); 
		strbuf.append(" hello"); 
		System.out.println(strbuf); 
		
		/*Wrapper classes are Immutable */
		Integer i = 3; //new Integer(3); //Autoboxing
		
		System.out.println("\nPrint Integer obj before passing it to a method "+i);
		testMethod(i); 
		System.out.println("Print Integer obj after having passed it to the test method "+i);
		
		// MutableInt is a mutable object, from the apache.commons library (see the import)
		MutableInt i2 = new MutableInt(3); //No autoboxing (it is an external library
		System.out.println("\nPrint MutableInt obj before passing it to a method "+i2);
		testMethodMutable(i2); 
		System.out.println("Print MutableInt obj after having passed it to the test method "+i2);
		
		
		System.out.println("\nPrint static Integer obj before passing it to a method "+staticInt);
		testMethod(staticInt); 
		System.out.println("Print static Integer obj after having passed it to the test method "+staticInt);
		
		System.out.println("\nPrint static MutableInt obj before passing it to a method "+staticIntMutable);
		testMethodMutable(staticIntMutable); 
		System.out.println("Print static MutableIntobj after having passed it to the test method "+staticIntMutable);
		
		
		/* Use of and access to List, Set, Map: SEE THE CODE IN THE SLIDES */ 
	}
	
	public static void testMethod(Integer i) {
		i=7; 
		System.out.println("Print the Integer obj within the test method, after modification: "+i);
		
		
	}
	
	public static void testMethodMutable(MutableInt i) {
		i.setValue(7); 
		System.out.println("Print the Integer obj within the test method, after modification: "+i);
 
		
	}

}
