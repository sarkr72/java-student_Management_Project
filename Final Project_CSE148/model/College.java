package model;

import java.io.Serializable;

public class College implements Serializable{
	private PersonBag personBag;
	private TextBookBag textBookBag;
	private MasterCourseBag masterCourseBag;
	private ClassRoomBag classRoomBag;
	private MiniStudentCourseBag studentCourseInfoBag;
	private MiniFacultyCourseBag facultyCourseBag;
	
	public College() {
		personBag  = new PersonBag(CollegeIf.PERSONBAG_MAXSIZE);
		textBookBag = new TextBookBag(CollegeIf.TEXTBOOKBAG_MAXSIZE);
		masterCourseBag =  new MasterCourseBag(CollegeIf.MASTER_COURSEBAG_MAXSIZE);
		classRoomBag =  new ClassRoomBag(CollegeIf.CLASSROOMBAG_MAXSIZE);
		studentCourseInfoBag = new MiniStudentCourseBag(CollegeIf.MINI_STUDENT_COURSEBAG_MAXSIZE);
		facultyCourseBag = new MiniFacultyCourseBag(CollegeIf.MINI_FACUTY_COURSEBAG_MAXSIZE);
	}
	
	
	public MiniFacultyCourseBag getFacultyCourseBag() {
		return facultyCourseBag;
	}


	public void setFacultyCourseBag(MiniFacultyCourseBag facultyCourseBag) {
		this.facultyCourseBag = facultyCourseBag;
	}


	public MiniStudentCourseBag getStudentCourseInfoBag() {
		return studentCourseInfoBag;
	}


	public void setStudentCourseInfoBag(MiniStudentCourseBag studentCourseInfoBag) {
		this.studentCourseInfoBag = studentCourseInfoBag;
	}


	public PersonBag getPersonBag() {
		return personBag;
	}
	public void setPersonBag(PersonBag personBag) {
		this.personBag = personBag;
	}
	
	public ClassRoomBag getClassRoomBag() {
		return classRoomBag;
	}
	public void setClassRoomBag(ClassRoomBag classRoomBag) {
		this.classRoomBag = classRoomBag;
	}
	public TextBookBag getTextBookBag() {
		return textBookBag;
	}
	public void setTextBookBag(TextBookBag textBookBag) {
		this.textBookBag = textBookBag;
	}
	public MasterCourseBag getMasterCourseBag() {
		return masterCourseBag;
	}
	public void setMasterCourseBag(MasterCourseBag masterCourseBag) {
		this.masterCourseBag = masterCourseBag;
	}
	
	
}
