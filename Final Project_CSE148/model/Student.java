package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import helpers.Helper;

public class Student extends Person implements Serializable{

	private Major major;
	private double gpa;
	private MiniStudentCourseBag miniStudentCourseBags;
	
	public Student(Name name, Major major, MiniStudentCourseBag miniStudentCourseBag) throws FileNotFoundException {
		super(name);
		this.major = major;
		miniStudentCourseBags = fillMiniStudentCourseBagByMajor(major);
		this.gpa = Helper.calculateGpa(miniStudentCourseBag);
	}

	public MiniStudentCourseBag fillMiniStudentCourseBagByMajor(Major major2) throws FileNotFoundException {
		MiniStudentCourseBag bag = null;
		bag = MajorCourseFiller.fillWithMajorCourses(major2);
		return bag;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBags;
	}

	public void setMiniStudentCourseBag(MiniStudentCourseBag miniStudentCourseBag) {
		this.miniStudentCourseBags = miniStudentCourseBag;
	}

	public double getGpa() {
		return gpa;
	}

	@Override
	public String toString() {
		return "Student [ " + super.toString()+ "major=" + major + ", gpa=" + String.format("%4.2f", gpa) + "]";
	}
//	@Override
//	public String toString() {
//		return "Student [ " + super.toString() + "major=" + major + "]\n";
//	}
	
}
