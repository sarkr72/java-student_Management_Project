package model;

import java.io.Serializable;

public abstract class Person implements Serializable{

	private Name name;
	private String id;
	private static int idCounter = 1;
	
	public Person(Name name) {
		this.name = name;
//		id = String.valueOf(idCounter++);
		id = String.format("%08d", idCounter++);
	}
	
	public String getId() {
		return id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "Id: "+ id + "]";
	}
	
	
}
