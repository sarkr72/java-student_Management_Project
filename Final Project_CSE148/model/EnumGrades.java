package model;

public enum EnumGrades {

	A, B_PLUS, B, C_PLUS, C, D_PLUS, D, F, NO_GRADE;
	
	public static EnumGrades getRandomGrade() {
		int rand = (int)(Math.random() * EnumGrades.values().length);
		return EnumGrades.values()[rand];
	}
}
