package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import model.ClassRoomBag;
import model.Classroom;
import model.College;
import model.Course;
import model.CourseStatus;
import model.EnumBuildings;
import model.EnumGrades;
import model.Faculty;
import model.FacultyCourseStatus;
import model.Major;
import model.MasterCourseBag;
import model.MiniFacultyCourseBag;
import model.MiniFacultyCourseInfo;
import model.MiniStudentCourseBag;
import model.MiniStudentCourseInfo;
import model.Name;
import model.PersonBag;
import model.Student;
import model.TextBook;
import model.TextBookBag;
import model.Title;

public class Helper implements Serializable{
	public static void allCourses() {

	}

	public static MasterCourseBag courses(MasterCourseBag cb) throws FileNotFoundException {
		File file = new File("RawData/Courses/Course_Inventory.txt");
		Scanner scanner = new Scanner(file);					

		while (true) {
			String courseNumber = scanner.nextLine();
			String courseTitle = scanner.nextLine();
			String courseDesc = scanner.nextLine();
			double credits = Double.parseDouble(scanner.nextLine());
			scanner.nextLine();
			Course course = new Course(courseNumber, courseTitle, courseDesc, credits);
				cb.insertCourse(course);
				if(courseNumber.contentEquals("WST130")) {
					break;
				}
		}
		scanner.close();
		return cb;
	}

	public static void textBooks(TextBookBag tb) throws FileNotFoundException  {
		File file1 = new File("RawData/TextBooks/textbook_isbns.txt");
		File file2 = new File("RawData/TextBooks/textbook_titles.txt");
		Scanner isbns = new Scanner(file1);
		Scanner titles = new Scanner(file2);

		while (isbns.hasNextLine() && titles.hasNextLine()) {
			String isbn = isbns.nextLine();
			String title = titles.nextLine();
			Name[] authors = getAuthorArray("RawData/Names/First Names.txt", "RawData/Names/Last Names.txt");
//			Name authors = new Name()
			double price = (double) (0.0 + (Math.random() * 200.00));
			tb.insertTextBook(new TextBook(title, isbn, authors, price));
		}
	}

	private static Name[] getAuthorArray(String fNames, String lNames) throws FileNotFoundException {
		Name[] nameArray = new Name[4];
		int randomNumber = (int) (Math.random() * 4) + 1;

		for (int i = 0; i < randomNumber; i++) {
			nameArray[i] = popName();
		}
		Name[] compactedArray = Arrays.copyOf(nameArray, randomNumber);

		return compactedArray;
	}

	private static String popRandomName(String fileName) throws FileNotFoundException {
		String[] names = FileReaderHelper.readFile(fileName);
		int randomNumber = (int) (Math.random() * names.length);
		String randomName = names[randomNumber];
		return randomName;
	}

	public static Name popName() throws FileNotFoundException {
		String firstName = popRandomName("RawData/Names/First Names.txt");
		String lastName = popRandomName("RawData/Names/Last Names.txt");
		Name name = new Name(firstName, lastName);
		return name;
	}

	public static double calculateGpa(MiniStudentCourseBag miniStudentCourseBag) {
		double gradePoint = 0;
		int totalCredits = 0;
		for (int i = 0; i < 5; i++) {

			gradePoint += MiniStudentCourseBag.getMiniStudentCourseInfoArray()[i].getNumberOfCredits()

					* MiniStudentCourseBag.getMiniStudentCourseInfoArray()[i].convertGrade();

			totalCredits += MiniStudentCourseBag.getMiniStudentCourseInfoArray()[i].getNumberOfCredits();

//			System.out.println("credits: " + msb.getMiniStudentCourseInfoArray()[i].getNumberOfCredits() + " grade: "
//					+ msb.getMiniStudentCourseInfoArray()[i].convertGrade() + " total credits :"
//					+ msb.getMiniStudentCourseInfoArray()[i].getNumberOfCredits());
		}
//		System.out.println("grade :" + gradePoint + "credits: " + totalCredits);

		return gradePoint / totalCredits;
	}

	public static void students(PersonBag personBag, College college) throws FileNotFoundException {
		Helper.courses(college.getMasterCourseBag());
		MasterCourseBag courseBag = college.getMasterCourseBag();
		MiniStudentCourseBag mcb = college.getStudentCourseInfoBag();
		File file1 =  new File("RawData/Students/students");
		Scanner scan = new Scanner(file1);
		
		while(scan.hasNextLine()) {
			String firstName = scan.nextLine();
			String lastName = scan.nextLine();
			Major major = Major.valueOf(scan.nextLine());
//			System.out.println(firstName + " " + lastName + " " + major);
			personBag.insert(new Student((new Name(firstName, lastName)), major, mcb));
			
			int rand = (int)(Math.random() * 5);
			
			Course course = courseBag.getCourseArray()[rand];
			
			String courseNumber = course.getCourseNumber();
			double credits = course.getCourseCredits();
			EnumGrades grade = EnumGrades.getRandomGrade();
			CourseStatus status = CourseStatus.getRandomStatus();
			if(((String.valueOf(status)).contentEquals("TAKING") || ((String.valueOf(status)).contentEquals("TO_TAKE")))) {
				grade = EnumGrades.NO_GRADE;
			}
			mcb.insertMiniStudentCourseInfo(new MiniStudentCourseInfo(courseNumber, credits, grade, status));
		}
	}
//
	public static void faculty(MiniFacultyCourseBag miniFacultyCourseBag, College college) throws FileNotFoundException {
		MasterCourseBag courseBag = college.getMasterCourseBag();
		PersonBag personBag = college.getPersonBag();
		
		File faculty = new File("RawData/Faculty/faculty");
		Scanner scan = new Scanner(faculty);
		
		while(scan.hasNextLine()) {
			String fName = scan.nextLine();
			String lName = scan.nextLine();
			Name name = new Name(fName, lName);
			String title = scan.nextLine();
//			Title title2 = Title.valueOf(title);
			double salary = Double.parseDouble(scan.nextLine());
			String phone = scan.nextLine();
			personBag.insertFaculty(new Faculty(name, Title.PROF, salary, phone, miniFacultyCourseBag));
			
			int rand = (int)(Math.random() * 5);
			
			Course course = courseBag.getCourseArray()[rand];
			
			String courseNumber = course.getCourseNumber();
			String title3 = course.getCourseTitle();
			double credits = course.getCourseCredits();
			FacultyCourseStatus status = FacultyCourseStatus.getRandomStatus();
			miniFacultyCourseBag.insertMiniFacultyCourseInfo(new MiniFacultyCourseInfo(courseNumber, title3, credits, status));
		}
//		pb.display();
	}

	public static void classRooms(ClassRoomBag classRoomBag) throws FileNotFoundException {
		for(int i = 0; i < 400; i++) {
			String roomNumber = "R" + String.format("%03d", i);
			classRoomBag.insertClassroom(new Classroom(roomNumber, EnumBuildings.Riverhead));
		}
		for(int i = 0; i < 400; i++) {
			String roomNumber = "I" + String.format("%03d", i);
			classRoomBag.insertClassroom(new Classroom(roomNumber, EnumBuildings.Islip));
		}
		for(int i = 0; i < 400; i++) {
			String roomNumber = "S" + String.format("%03d", i);
			classRoomBag.insertClassroom(new Classroom(roomNumber, EnumBuildings.Smithtown));
		}
		for(int i = 0; i < 400; i++) {
			String roomNumber = "N" + String.format("%03d", i);
			classRoomBag.insertClassroom(new Classroom(roomNumber, EnumBuildings.NFL));
		}
//		classRoomBag.displayClassrooms();
	}
}
