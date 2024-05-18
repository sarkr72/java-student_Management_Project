package comboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CourseStatus;
import model.EnumGrades;
import model.FacultyCourseStatus;
import model.Major;

public class ComboBoxDemo{

	private static EnumGrades Egrade;
	private static Major major;
	private static FacultyCourseStatus cStatus;
	
	public static Pane getGradeBox() {
		ObservableList<String> list = FXCollections.observableArrayList(
				"A", "B+", "B", "C+", "C", "D+", "D", "F", "NO_GRADE");
		ComboBox<String> box = new ComboBox();
		box.setItems(list);
		box.setOnAction(e ->{
			String  item = (String) box.getValue();
			 switch(item) {
			 case "A": Egrade = EnumGrades.valueOf("A"); break;
			 case "B+": Egrade = EnumGrades.valueOf("B_PLUS"); break;
			 case "B": Egrade = EnumGrades.valueOf("B"); break;
			 case "C+": Egrade = EnumGrades.valueOf("C_PLUS"); break;
			 case "C": Egrade = EnumGrades.valueOf("C"); break;
			 case "D+": Egrade = EnumGrades.valueOf("D_PLUS"); break;
			 case "D": Egrade = EnumGrades.valueOf("D"); break;
			 case "F": Egrade = EnumGrades.valueOf("F"); break;
			 case "NO_GRADE": Egrade = EnumGrades.valueOf("NO_GRADE"); break;
			 default: System.out.println("null");
			 }
//			System.out.println("your grade is " + item);
		});
		
		HBox root = new HBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(box);
		return root;
	}
	
	public static EnumGrades getGrade() {
		return Egrade;
	}
	
	public static Pane getMajorBox() {
		ObservableList<String> list = FXCollections.observableArrayList(
				"ACC", "AUT", "BUS", "COT", "CSE", "CST", "CYB", "DRF", "ELT", "MKT");
		ComboBox<String> box = new ComboBox();
		box.setItems(list);
		box.setOnAction(e ->{
			String  item = (String) box.getValue();
			major = Major.valueOf(item);
//			System.out.println("your grade is " + item);
		});
		
		HBox root = new HBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(box);
		return root;
	}
	
	public static Pane getCourseStatusBox() {
		ObservableList<String> list = FXCollections.observableArrayList(
				"TEACHING", "TAUGHT", "TO_TEACH");
		ComboBox<String> box = new ComboBox();
		box.setItems(list);
		box.setOnAction(e ->{
			String  item = box.getValue();
			cStatus = FacultyCourseStatus.valueOf(item);
		});
		
		HBox root = new HBox(50);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(box);
		return root;
	}
	
	public static FacultyCourseStatus getCourseStatus() {
		return cStatus;
	}
	public static Major getMajor() {
		return major;
	}

}
