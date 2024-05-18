package fileChoooserSample;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import helpers.FileReaderHelper;
import helpers.Helper;
import javafx.scene.control.TextArea;
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

//import java.io.File;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.HBox;
//import javafx.stage.FileChooser;
//import javafx.stage.FileChooser.ExtensionFilter;
//import javafx.stage.Stage;
//
public class FileChooserFinal {

	public static String readFile(File file, College college) throws FileNotFoundException {

//	PersonBag personBag = college.getPersonBag();
		Scanner scan = new Scanner(file);
		String text;
		try {

//        bufferedReader = new BufferedReader(new FileReader(file));
			while (scan.hasNextLine()) {
				String firstName = scan.nextLine();
				String lastName = scan.nextLine();
				Major major = Major.valueOf(scan.nextLine());
//			System.out.println(firstName + " " + lastName + " " + major);
				college.getPersonBag()
						.insert(new Student((new Name(firstName, lastName)), major, college.getStudentCourseInfoBag()));
			}

		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		;

		return college.getPersonBag().getPersonBagArray();
	}

	public static String readFacultyFile(File file, College college) throws FileNotFoundException {

		PersonBag personBag = college.getPersonBag();
		Scanner scan = new Scanner(file);
		String text;
		// bufferedReader = new BufferedReader(new FileReader(file));
		while (scan.hasNextLine()) {
			String firstName = scan.nextLine();
			String lastName = scan.nextLine();
			Title title = Title.valueOf(scan.nextLine());
			double salary = Double.parseDouble(scan.nextLine());
			String phone = scan.nextLine();
//			System.out.println(firstName + " " + lastName + " " + title + " " + salary + " " + phone);
			personBag.insertFaculty(
					new Faculty((new Name(firstName, lastName)), title, salary, phone, college.getFacultyCourseBag()));
		}
		;

		return personBag.getFacultyBagArray();
	}

	public static void exportFile(String content, File file) {
		try {
			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static String readCourseFile(File file, College college) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		MasterCourseBag mcg = college.getMasterCourseBag();
		while (true) {
			String courseNumber = scanner.nextLine();
			String courseTitle = scanner.nextLine();
			String courseDesc = scanner.nextLine();
			double credits = Double.parseDouble(scanner.nextLine());
			scanner.nextLine();
			Course course = new Course(courseNumber, courseTitle, courseDesc, credits);
			mcg.insertCourse(course);
			if (courseNumber.contentEquals("WST130")) {
				break;
			}
		}
		scanner.close();
		return mcg.getMasterCourseBag();
	}

	public static String readTextbookFile(List<File> list, College college) throws FileNotFoundException {
//		TextArea textArea = new TextArea();
//		textArea.appendText(String.valueOf(list));
		String str = String.valueOf(list);
		String isbnFile = "";
		String title = "";
		String fNames = "";
		String lNames = "";
		for (int i = 1; i < 109; i++) {
			if (i >= 1 && i <= 24) {
				fNames += str.charAt(i);
			} else if (i >= 27 && i <= 49) {
				lNames += str.charAt(i);
			} else if (i >= 52 && i <= 78) {
				isbnFile += str.charAt(i);
			} else if (i >= 81 && i <= 108) {
				title += str.charAt(i);
			}

//			System.out.println(str.charAt(i) + "i=" +i);
		}

//		System.out.println("isbns: " + isbnFile + "\n title: \n" + title + "\n fNames: \n" + fNames + "\n lNames \n" + lNames);

		File file1 = new File(isbnFile);
		File file2 = new File(title);

		Scanner isbns = new Scanner(file1);
		Scanner titles = new Scanner(file2);

		TextBookBag textbookBag = college.getTextBookBag();

		while (isbns.hasNextLine() && titles.hasNextLine()) {
			String isbn = isbns.nextLine();
			String titlea = titles.nextLine();
			System.out.println(isbn + "\n" + titlea);
			Name[] authors = getAuthorArray(fNames, lNames);
			double price = (double) (0.0 + (Math.random() * 200.00));
			textbookBag.insertTextBook(new TextBook(titlea, isbn, authors, price));
			if (isbn.contentEquals("978-0-444-50520-0")) {
				break;
			}
		}
		return textbookBag.getTextBookBag();
	}

	private static Name[] getAuthorArray(String fName, String lName) throws FileNotFoundException {
		Name[] nameArray = new Name[4];
		int randomNumber = (int) (Math.random() * 4) + 1;

		for (int i = 0; i < randomNumber; i++) {
			nameArray[i] = popName(fName, lName);
		}
		Name[] compactedArray = Arrays.copyOf(nameArray, randomNumber);

		return compactedArray;
	}

	private static String popRandomName(String fName) throws FileNotFoundException {
		String[] names = FileReaderHelper.readFile(fName);
		int randomNumber = (int) (Math.random() * names.length);
		String randomName = names[randomNumber];
		return randomName;
	}

	public static Name popName(String fName, String lName) throws FileNotFoundException {
		String firstName = popRandomName(fName);
		String lastName = popRandomName(lName);
		Name name = new Name(firstName, lastName);
		return name;
	}

	public static String readClassRoomFile(File file, College college) throws FileNotFoundException {
		ClassRoomBag classRoomBag = college.getClassRoomBag();
		Scanner scan = new Scanner(file);

		while (scan.hasNextLine()) {
			String roomNumber = scan.nextLine();
			String str = scan.nextLine();
			EnumBuildings building = EnumBuildings.valueOf(str);
			classRoomBag.insertClassroom(new Classroom(roomNumber, building));
			if (roomNumber.contentEquals("N399")) {
				break;
			}
			scan.nextLine();
		}
		return classRoomBag.getClassroomBag();
	}

	public static void exportFacultyFile(String content, File file) {
		try {
			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void exportCourseFile(String content, File file) {
		try {
			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void exportTextBookFile(College college, File file) throws FileNotFoundException {
		Helper.textBooks(college.getTextBookBag());
		String content = college.getTextBookBag().getTextBookBag();

		try {
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void exportClassRoomFile(String content, File file) {
		try {
			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static String readFacultyCourseInfoFile(File file, College college) throws FileNotFoundException {
		MiniFacultyCourseBag miniFacultyCourseBag = college.getFacultyCourseBag();
		Scanner scan = new Scanner(file);
		String text;

		while (scan.hasNextLine()) {
			String courseNumber = scan.nextLine();
			String title3 = scan.nextLine();
			double credits = Double.parseDouble(scan.nextLine());
			FacultyCourseStatus status = FacultyCourseStatus.valueOf(scan.nextLine());
			miniFacultyCourseBag
					.insertMiniFacultyCourseInfo(new MiniFacultyCourseInfo(courseNumber, title3, credits, status));
		}
		return miniFacultyCourseBag.getFacutyCourseInfoBag();
	}

	public static String readStudentCourseInfoFile(File file, College college) throws FileNotFoundException {
		MiniStudentCourseBag miniStudentCourseBag = college.getStudentCourseInfoBag();
		Scanner scan = new Scanner(file);
		String text;

		while (scan.hasNextLine()) {
			String courseNumber = scan.nextLine();
			double credits = Double.valueOf(scan.nextLine());
			EnumGrades grade = EnumGrades.valueOf(scan.nextLine());
			CourseStatus status = CourseStatus.valueOf(scan.nextLine());
			miniStudentCourseBag
					.insertMiniStudentCourseInfo(new MiniStudentCourseInfo(courseNumber, credits, grade, status));
		}
		return miniStudentCourseBag.studntCourseInfoBag();
	}

	public static void exportStudentCourseInfoFile(String content, File file) {
		try {
			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}