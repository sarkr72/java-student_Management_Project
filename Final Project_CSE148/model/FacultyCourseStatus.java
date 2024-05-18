package model;

public enum FacultyCourseStatus {

	 TEACHING, TAUGHT, TO_TEACH;
	
		public static FacultyCourseStatus getRandomStatus() {
			int rand = (int)(Math.random() * FacultyCourseStatus.values().length);
			return FacultyCourseStatus.values()[rand];
		}
}
