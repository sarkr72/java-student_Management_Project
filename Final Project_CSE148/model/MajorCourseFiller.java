package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import helpers.Helper;

public class MajorCourseFiller implements Serializable{

	public static MiniStudentCourseBag fillWithMajorCourses(Major major) throws FileNotFoundException {
		
		MiniStudentCourseBag bag = new MiniStudentCourseBag(60);
		
		College college = new College();
		MasterCourseBag mcb = Helper.courses(college.getMasterCourseBag());
		
		File file = new File("MajorCourses/" + major.toString() + ".txt");
		Scanner scan = new Scanner(file);
		
		int i = 0;
		while (scan.hasNextLine()) {
			String courseNumber = scan.nextLine();
			if(i < 5) {
			Course c1 = mcb.findCourseByCourseNumber(courseNumber);
			double credits = c1.getCourseCredits();
			EnumGrades grade = EnumGrades.getRandomGrade();
			CourseStatus status = CourseStatus.getRandomStatus();
			
			if(((String.valueOf(status)).contentEquals("TAKING") || ((String.valueOf(status)).contentEquals("TO_TAKE")))) {
				grade = EnumGrades.NO_GRADE;
			}
			bag.insertMiniStudentCourseInfo(new MiniStudentCourseInfo(courseNumber, credits, grade, status));
			}
			bag.insertMiniStudentMajorInfo(new MiniStudentCourseInfo(courseNumber));
		}
		scan.close();
		
		return bag;
	}
}