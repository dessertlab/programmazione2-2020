package it.unina.p2.collections.deepCopy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DeepCopyExample 
{   
	
	public static void main(String[] args) {
		ArrayList<Employee> employeeList = new ArrayList<>(); 
		
		Date d = new Date(2323223232L); 
		Employee e = new Employee(1l, "adam", d);
		
        employeeList.add(e);
	    
        System.out.println("Employee list " +employeeList);
 
        //Uncomment one out of the following 3 alternatives to see the effect
        
        // 1) This will call the clone method of ArrayList (shallow copy - only the references to Employee are copied)
        
        /*  @SuppressWarnings("unchecked")
         ArrayList<Employee> employeeListClone = (ArrayList<Employee>)employeeList.clone(); 
         */
      
        // 2) This way of creating the copy also causes a shallow copy
        /*     
         ArrayList<Employee> employeeListClone = new ArrayList<>(employeeList); 
        */
    
        // 3) This will cause a deep copy because "clone" of Employee is called, which is redefined 
        ArrayList<Employee> employeeListClone = new ArrayList<>(); 
        Iterator<Employee> iterator = employeeList.iterator();
        while(iterator.hasNext()){
            try {
            	//This will call the clone method of Employee. 
            	employeeListClone.add((Employee) iterator.next().clone());
            	//Alternatively, each employee must be deeply copied
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
        }
        	         
	        System.out.println("Cloned employee list" +employeeListClone);
	        
	        //Modify the element from the first list
	        Date d1 = new Date(System.currentTimeMillis()-1000000000000L); 
			e.setDate(d1);
			employeeList.set(0, e); 
	        
	        //Check if both the lists are changed (shallow copy) or not (deep copy)
	        System.out.println("\nEmployee list: " +employeeList);
	        System.out.println("Cloned employee list: " +employeeListClone);
	        
		
	}
		
}

