package saveAndRestoreFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.ClassRoomBag;
import model.MasterCourseBag;
import model.MiniFacultyCourseBag;
import model.PersonBag;
import model.TextBookBag;

public class FileRestorer {

	public static String restoreFile(File file) throws IOException, ClassNotFoundException {

//		MiniStudentCourseBag studentInfoBag =  college.getStudentCourseInfoBag();
//		MiniFacultyCourseBag facultyInfoBag = college.getFacultyCourseBag();
//		TextBookBag textBookBag = college.getTextBookBag();
//		MasterCourseBag courseBag = college.getMasterCourseBag(); 
//		ClassRoomBag classRoomBag =
		PersonBag personBag;
		PersonBag facultyBag;
		MiniFacultyCourseBag facultyInfoBag;
		MasterCourseBag courseBag;
		ClassRoomBag classroomBag;
		TextBookBag textBookBag;
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream dis = new ObjectInputStream(fis);

		String str = "";
		int i = 0;

		while (i < 6) {
			if (i == 0) {
				personBag = (PersonBag) (dis.readObject());
				str += "Person Bag: \n" + personBag.getPersonBagArray();
			} else if (i == 1) {
				personBag = (PersonBag) (dis.readObject());
				str += "Faculty Bag: \n" + personBag.getFacultyBagArray();
			} else if (i == 2) {
				facultyInfoBag = (MiniFacultyCourseBag) (dis.readObject());
				str += "\n Faculty Course Info Bag: \n" + facultyInfoBag.getFacutyCourseInfoBag();
			} else if (i == 3) {
				textBookBag = (TextBookBag) (dis.readObject());
				str += "\n Textbook Bag: \n" + textBookBag.getTextBookBag();
			} else if (i == 4) {
				courseBag = (MasterCourseBag) (dis.readObject());
				str += "\n Master Course Bag: \n" + courseBag.getMasterCourseBag();
			} else if (i == 5) {
				classroomBag = (ClassRoomBag) (dis.readObject());
				str += "\n Classroom Bag: \n" + classroomBag.getClassroomBag();
			}
			i++;
		}
		dis.close();
		return str;
	}
}
