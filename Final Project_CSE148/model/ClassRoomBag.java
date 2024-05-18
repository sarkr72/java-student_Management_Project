package model;

import java.io.Serializable;

public class ClassRoomBag implements Serializable{

	private Classroom[] classrooms;
	private int nElems;
	
	public ClassRoomBag(int maxClassrooms) {
		classrooms = new Classroom[maxClassrooms];
		nElems = 0;
	}
	
	public void insertClassroom(Classroom classroom) {
		classrooms[nElems++] = classroom;
	}
	
	public String getClassroomBag() {
		String str = "";
		for(int i = 0; i < nElems; i++) {
			str += classrooms[i] + "\n";
		}
		return str;
	}
	public Classroom findClassRoomByRoomNumber(String roomNumber) {
		for(int i = 0; i < nElems; i++) {
			if(roomNumber.contentEquals(classrooms[i].getRoomNumber()))
			return classrooms[i];
		}
		return null;
	}
	
	public Classroom removeClassroomByRoomNumber(String roomNumber) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (roomNumber.contentEquals(classrooms[i].getRoomNumber())) {
				break;
			}
		}
		Classroom temp = classrooms[i];
		if (i == nElems) {
			return null;
		} else {
			for (int j = i; j < nElems - 1; j++) {
				classrooms[j] = classrooms[j + 1];
			}
		}
		nElems--;
		return temp;
	}
	
	public void displayClassrooms() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(classrooms[i]);
			System.out.println();
		}
	}
}
