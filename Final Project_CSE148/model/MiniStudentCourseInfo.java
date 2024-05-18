package model;

import java.io.Serializable;

public class MiniStudentCourseInfo implements Serializable{
	
	private String courseNumber;
	private double numberOfCredits;
	private EnumGrades letterGrade;
	private CourseStatus courseStatus;
	
	public MiniStudentCourseInfo(String courseNumber, double numberOfCredits, EnumGrades letterGrade, CourseStatus courseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = letterGrade;
		this.courseStatus = courseStatus;
	}

	public MiniStudentCourseInfo(String courseNumber) {
		super();
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = EnumGrades.NO_GRADE;
		this.courseStatus = CourseStatus.TAKEN;
	}
	
	public double convertGrade() {
		double point;
		switch (letterGrade) {
		case A:
			point = 4.0;
			break;
		case B_PLUS:
			point = 3.5;
			break;
		case B:
			point = 3.0;
			break;
		case C_PLUS:
			point = 2.5;
			break;
		case C:
			point = 2.0;
			break;
		case D_PLUS:
			point = 1.5;
			break;
		case D:
			point = 1.0;
			break;
		case F:
			point = 0.0;
			break;
		default:
			point = 0;
		}
		return point;
	}
	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public EnumGrades getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(EnumGrades letterGrade) {
		this.letterGrade = letterGrade;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus( CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	@Override
	public String toString() {
		return "MiniStudentInfo [courseNumber=" + courseNumber + ", numberOfCredits=" + numberOfCredits
				+ ", Grade=" + convertGrade() + ", courseStatus=" + courseStatus + "]";
	}
	
//	@Override
//	public String toString() {
//		return courseNumber;
//	}
}
