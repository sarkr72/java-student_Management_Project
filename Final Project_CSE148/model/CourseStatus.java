package model;

public enum CourseStatus {

	TAKEN, TAKING, TO_TAKE;
	
	public static CourseStatus getRandomStatus() {
		int rand = (int)(Math.random() * CourseStatus.values().length);
		return CourseStatus.values()[rand];
	}
}
