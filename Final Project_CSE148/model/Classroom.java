package model;

import java.io.Serializable;

public class Classroom implements Serializable{

	private String roomNumber;
	private EnumBuildings roomBuilding;

	public Classroom(String roomNumber, EnumBuildings roomBuilding) {
		super();
		this.roomNumber = roomNumber;
		this.roomBuilding = roomBuilding;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public EnumBuildings getRoomBuilding() {
		return roomBuilding;
	}

	public void setRoomBuilding(EnumBuildings roomBuilding) {
		this.roomBuilding = roomBuilding;
	}

//	@Override
//	public String toString() {
//		return "Classroom [roomNumber=" + roomNumber + ", roomBuilding=" + roomBuilding + "]";
//	}
	@Override
	public String toString() {
		return "room number: " + roomNumber + "\n"+ "Building: "+roomBuilding +"\n" ;
	}

}
