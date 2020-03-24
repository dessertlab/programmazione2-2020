package it.unina.p2.collections.deepCopy;

import java.util.Date;

public class Employee implements Cloneable{

	private Long id;
    private String name;
    private Date date;       //Mutable field
 
    public Employee(Long id, String name, Date date) {
        super();
        this.id = id;
        this.name = name;
        this.date = date; // Date of Birth
    }
 
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Employee clone = null;
        try
        {
            clone = (Employee) super.clone();
 
            //Copy new date object to cloned method. This overrides the clone method of Object
            clone.setDate((Date) this.getDate().clone());
        } 
        catch (CloneNotSupportedException e) 
        {
            throw new RuntimeException(e);
        }
        return clone;
    }
    
  //Getters and setters
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", date of birth=" + date + "]";
    }
}
