package model;

import java.io.Serializable;

public class Faculty extends Person implements Serializable{

	private Title title;
	private double salary;
	private String officePhone;
	private MiniFacultyCourseBag miniFacultyCourseBag;

	public Faculty(Name name, Title title, double salary, String officePhone,
			MiniFacultyCourseBag miniFacultyCourseBag) {
		super(name);
		this.salary = salary;
		this.title = title;
		this.officePhone = officePhone;
		this.miniFacultyCourseBag = miniFacultyCourseBag;
	}

	
	public Title getTitle() {
		return title;
	}
	
	public void settitle(Title title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}

	public void setMiniFacultyCourseBag(MiniFacultyCourseBag miniFacultyCourseBag) {
		this.miniFacultyCourseBag = miniFacultyCourseBag;
	}

	@Override
	public String toString() {
		return "\n Faculty: " + super.toString() +  " [" + title + ", salary=" + salary + ", officePhone=" + officePhone + "]";
	}

	public void setTitle(Title title) {
		this.title = title;
	}


}
