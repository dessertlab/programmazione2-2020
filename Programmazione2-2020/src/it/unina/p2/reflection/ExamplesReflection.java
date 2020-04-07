package it.unina.p2.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;


@Documented   
@interface ClassPreamble {    
String author();    
String date();    
int currentRevision() default 1;    
String lastModified() default "N/A";    
String lastModifiedBy() default "N/A"; 
String[] reviewers() default "ss";
} 

public class ExamplesReflection {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//Object Creation
			Object p1 = Class.forName("it.unina.p2.reflection.Person").newInstance();
			Object p2 = Person.class.newInstance();
			Object p3 = Person.class.getDeclaredConstructor().newInstance();

			//Method invocation
			Method m = Person.class.getDeclaredMethod("printName", String.class); 
			System.out.println(m.toString());
			System.out.println(m.getName());
			String s = "test";
			m.invoke(p3, s);
			
			//Description of the method
			System.out.println("\n get Paramters: "+Person.getParameters(m));
			Method[] mTry = new Method[1]; 
			mTry[0] = m;
			System.out.println("\n get Methods description: "+Person.getMethodsDescription(mTry));

			//Description of the fields
			System.out.println(Person.getFieldsDescription(Person.class.getFields()));
			System.out.println(Person.class.getField("name").getModifiers());
			System.out.println(Person.class.getField("id").getModifiers());
			System.out.println("All modifiers: "+Modifier.toString(Modifier.methodModifiers()));
			
			// Arrays set and get
			 Object arrayObject =null;
				try {
					arrayObject = Array.newInstance(int.class, 2);
					int n1 = Array.getInt(arrayObject, 0);
					int n2 = Array.getInt(arrayObject, 1);
					System.out.println("n1 = " + n1 + ", n2=" + n2);
					Array.set(arrayObject, 0, 101);
					Array.set(arrayObject, 1, 102);
					n1 = Array.getInt(arrayObject, 0);
					n2 = Array.getInt(arrayObject, 1);
					System.out.println("n1 = " + n1 + ", n2=" + n2);
				} catch (NegativeArraySizeException | 
			IllegalArgumentException |ArrayIndexOutOfBoundsException e) {
					System.out.println(e.getMessage());}
				// Arrays: get number of dimensions
				int dimension = 0;
				Class<?> c = arrayObject.getClass();
				if (!c.isArray()) {
					throw new IllegalArgumentException("Object is not an  array");
				}
				while (c.isArray()) {
					dimension++;
					c = c.getComponentType();
				}
			System.out.println("dimension "+dimension);

			// Arrays: extend the length of teh array
			int[] ids2 = {101, 102};
			System.out.println("Old array length: " + ids2.length);
			System.out.println("Old array elements:" + Arrays.toString(ids2));
			ids2 = (int[]) expandBy(ids2, 1);
			ids2[2] = 103; 
			System.out.println("New array length: " + ids2.length);
			System.out.println("New array elements:" + Arrays.toString(ids2));
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//support method to expand the length of the array
	public static Object expandBy(Object oldArray, int increment) {
		Object newArray = null;
		int oldLength = Array.getLength(oldArray);
		int newLength = oldLength + increment;
		Class<?> c = oldArray.getClass();
		newArray = Array.newInstance(c.getComponentType(), newLength);
		System.arraycopy(oldArray, 0, newArray, 0, oldLength);
		return newArray;
}

}

//example of annotation
@ClassPreamble(author = "P2”)", date = "06/04/2020", reviewers = { "P2" })
//Support class for the above operations
class Person {
	public int id = 1; 
	public String name = "unknonw"; 
	
	public void printName(String param1) throws Exception, IllegalArgumentException {
		System.out.println("My name is: "+name);
		System.out.println("My Id is: "+id);
	}
	
	public static ArrayList<String> getFieldsDescription(Field[] fields){
		ArrayList<String> fieldList = new ArrayList<String>(); 
		
		for (Field f: fields) {
			int mod = f.getModifiers() & Modifier.fieldModifiers();
			String modifiers =Modifier.toString(mod);
			Class<?> type =f.getType();
			String typeName = type.toString();
			String fieldName = f.getName();
			fieldList.add(modifiers + " " + typeName + " " + fieldName); 
		}
		
		return fieldList; 
	}
	
	public static ArrayList<String> getParameters(Executable exec){
		ArrayList<String> paramList = new ArrayList<String>(); 
		Parameter[] params= exec.getParameters();
		for (int i=0; i<params.length;i++) {
			
			int mod = params[i].getModifiers() & Modifier.fieldModifiers();
			String modifiers = Modifier.toString(mod);
			String paramType = params[i].getType().getSimpleName();
			String paramName = params[i].getName();
			String temp = modifiers + " " + paramType + " " + paramName; 
			paramList.add(temp.trim()); 
		}
 		return paramList;  
		
	}
	
	public static ArrayList<String> getMethodsDescription(Method[] methods){
		ArrayList<String> methodsList = new ArrayList<String>(); 
		for (Method m : methods) {
			
			int mod = m.getModifiers() & Modifier.fieldModifiers();
			String modifiers = Modifier.toString(mod);
			Class<?> returnType = m.getReturnType();
			String returnTypeName = returnType.getSimpleName();

			String methodName = m.getName();
			Parameter[] params = m.getParameters();
			String parameters = "";
			for (int i=0; i<params.length; i++) 
				 parameters += params[i].toString()+" "; 
			
			String execptions = "";
			Class<?>[] throwsClause = m.getExceptionTypes();
			for (int i=0; i<throwsClause.length; i++) 
				 execptions += throwsClause[i].toString()+" "; 
			
				methodsList.add(modifiers + " " + returnTypeName + " " + methodName + " "+parameters + " "+execptions);
		}
 		return methodsList;  
		
	}
}
	

