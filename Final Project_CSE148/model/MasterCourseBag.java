package model;

import java.io.Serializable;

public class MasterCourseBag implements Serializable{

	private Course[] courseArray;
	private int nElems;
	
	public MasterCourseBag(int maxCourseNumber) {
		courseArray = new Course[maxCourseNumber];
		nElems = 0;
	}
	
	public void insertCourse(Course course) {
		courseArray[nElems++] = course;
	}
	public String getMasterCourseBag() {
		String str = "";
		for(int i = 0; i < nElems; i++) {
			str += courseArray[i] + "\n";
		}
		return str;
	}
	
	public Course[] getCourseArray() {
		return courseArray;
	}

	public Course findCourseByCourseNumber(String courseNumber) {
		for(int i = 0; i < nElems; i++) {
			if(courseNumber.contentEquals(courseArray[i].getCourseNumber())) {
			return courseArray[i];
			}
		}
		return null;
	}
	
	public Course removeCourseByCourseNumber(String courseNumber) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (courseNumber.contentEquals(courseArray[i].getCourseNumber())) {
				break;
			}
		}
		Course temp = courseArray[i];
		if (i == nElems) {
			return null;
		} else {
			for (int j = i; j < nElems - 1; j++) {
				courseArray[j] = courseArray[j + 1];
			}
		}
		nElems--;
		return temp;
	}
	
	public void displayCourses() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(courseArray[i]);
		}
	}
}
