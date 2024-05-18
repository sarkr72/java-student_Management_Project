package model;

public enum EnumBuildings {

	Riverhead, Islip, Smithtown, NFL;
	
	public static EnumBuildings getRandomBuilding() {
		int rand = (int)(Math.random() * EnumBuildings.values().length);
		return EnumBuildings.values()[rand];
	}
}
