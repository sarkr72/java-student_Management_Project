package model;

import java.io.Serializable;

public class MiniFacultyCourseInfo implements Serializable{

	private String courseNumber;
	private String courseTitle;
	private double numberOfCredits;
	private FacultyCourseStatus courseStatus; // taught or teaching

	public MiniFacultyCourseInfo(String courseNumber, String courseTitle, double numberOfCredits, FacultyCourseStatus courseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.numberOfCredits = numberOfCredits;
		this.courseStatus = courseStatus;
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

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public FacultyCourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(FacultyCourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	@Override
	public String toString() {
		return "MiniFacultyCourseInfo [courseNumber= " + courseNumber + ", courseTitle= " + courseTitle
				+ ", numberOfCredits= " + numberOfCredits + ", courseStatus= " + courseStatus + "]";
	}

}
