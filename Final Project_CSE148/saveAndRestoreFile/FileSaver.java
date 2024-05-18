package saveAndRestoreFile;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import helpers.Helper;
import model.ClassRoomBag;
import model.College;
import model.MasterCourseBag;
import model.MiniFacultyCourseBag;
import model.MiniStudentCourseBag;
import model.PersonBag;
import model.TextBookBag;

public class FileSaver {

	public static void saveBinaryFile(File file, College college) throws IOException {
		Helper.courses(college.getMasterCourseBag());
		Helper.classRooms(college.getClassRoomBag());
		Helper.faculty(college.getFacultyCourseBag(), college);
		Helper.students(college.getPersonBag(), college);
		Helper.textBooks(college.getTextBookBag());
		String bags = "";
		
		PersonBag personBag = college.getPersonBag();
		PersonBag facultyBag = college.getPersonBag();
//		MiniStudentCourseBag studentInfoBag =  college.getStudentCourseInfoBag();
		MiniFacultyCourseBag facultyInfoBag = college.getFacultyCourseBag();
		TextBookBag textBookBag = college.getTextBookBag();
		MasterCourseBag courseBag = college.getMasterCourseBag(); 
		ClassRoomBag classRoomBag = college.getClassRoomBag();
		
		int i = 0;
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		
		while(i < 6) {
		if(i == 0) {
		dos.writeObject(personBag);
		}else if(i == 1) {
			dos.writeObject(facultyBag);
		}else if(i == 2) {
			dos.writeObject(facultyInfoBag);
		}
		else if(i == 3) {
			dos.writeObject(textBookBag);
		}
		else if(i == 4) {
			dos.writeObject(courseBag);
		}else if(i == 5) {
			dos.writeObject(classRoomBag);
		}
		i++;
	}
		dos.close();
		}
}
