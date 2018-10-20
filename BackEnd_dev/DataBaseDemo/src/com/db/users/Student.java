package com.db.users;

public class Student {
	protected String Id;
	protected String Name;
	protected String Sex;
	protected String Age;

	    public Student(String Name, String Sex, String Age) {
	        this.Id = null; //default
	        this.Name = Name;
	        this.Sex = Sex;
	        this.Age = Age;
	    }

	    public String getId() {
	        return Id;
	    }

	    public void setId(String Id) {
	        this.Id = Id;
	    }

	    public String getName() {
	        return Name;
	    }

	    public void setName(String Name) {
	        this.Name = Name;
	    }

	    public String getSex() {
	        return Sex;
	    }

	    public void setSex(String Sex) {
	        this.Sex = Sex;
	    }

	    public String getAge() {
	        return Age;
	    }

	    public void setage(String Age) {
	        this.Age = Age;
	    }
}
