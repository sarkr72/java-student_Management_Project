package model;

import java.io.Serializable;

public class Course implements Serializable{

	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private double courseCredits;
	
	public Course(String courseNumber, String courseTitle, String courseDescription, double courseCredits) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.courseCredits = courseCredits;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public double getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(double courseCredits) {
		this.courseCredits = courseCredits;
	}

	@Override
	public String toString() {
		return "CourseBag [courseNumber=" + courseNumber + "\n courseTitle=" + courseTitle + "\n courseDescription="
				+ courseDescription + "\n courseCredits=" + courseCredits + "]";
	}
	
}
