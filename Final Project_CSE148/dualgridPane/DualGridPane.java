package dualgridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import app.AppDemo;
import comboBox.ComboBoxDemo;
import helpers.Helper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.College;
import model.Course;
import model.CourseStatus;
import model.EnumGrades;
import model.Major;
import model.MiniStudentCourseInfo;
import model.Person;
import model.Student;

public class DualGridPane{
	private String potential2;
	private String potential;
	private BorderPane root;
//	public static void main(String[] args) {
//	launch(args);
//
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
	
//		public void listViewsOfCourses() {
	Stage primaryStage;
	public DualGridPane(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void getMajorPane(AppDemo app, College college, Person student) throws FileNotFoundException {
		root = new BorderPane();
		Scene scene = new Scene(root, 400, 250);
		
		GridPane gridPane = new GridPane();
		gridPane.setGridLinesVisible(false);
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		ColumnConstraints colum1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		ColumnConstraints colum2 = new ColumnConstraints(50);
		ColumnConstraints colum3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		
		colum1.setHgrow(Priority.ALWAYS);
		colum3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(colum1, colum2, colum3);
		
		Label coursesToTakeLbl = new Label("Courses To Take");
		gridPane.setHalignment(coursesToTakeLbl, HPos.CENTER);
		gridPane.add(coursesToTakeLbl, 0, 0);
		
		Label coursesTakingLbl = new Label("Courses Taking");
		gridPane.setHalignment(coursesTakingLbl, HPos.CENTER);
		gridPane.add(coursesTakingLbl, 2, 0);
		
		Major major = ((Student) student).getMajor();
		File file = new File("MajorCourses/" + major.toString() + ".txt");
		Scanner scan = new Scanner(file);
		
		String[] courses1 = new String [college.getStudentCourseInfoBag().getMiniStudentMajorsArray().length];
		int i = 0;
		while (scan.hasNextLine()) {
			String courseNumber = scan.nextLine();
		courses1[i] = courseNumber;
		i++;
		}
		scan.close();
		
		String[] courses = Arrays.copyOf(courses1, i);
		
		ObservableList<String> coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(courses));
		ListView<String> coursesToTakeListView = new ListView<>( coursesToTakeList);
		gridPane.add(coursesToTakeListView, 0, 1);
		
		ObservableList<String> coursesTakingList = FXCollections.observableArrayList(Arrays.asList());
		
		ListView<String> coursesTakingListView = new ListView<>( coursesTakingList);
		gridPane.add(coursesTakingListView, 2, 1);
		
		Button sendRightButton = new Button("->");
		Button sendLeftButton = new Button("<-");
		
		sendRightButton.setOnAction(e ->{
			try {
				Helper.courses(college.getMasterCourseBag());
			} catch (FileNotFoundException e1) {
				System.out.println(e1);
			}
			potential = coursesToTakeListView.getSelectionModel().getSelectedItem();
			if(potential != null) {
				coursesToTakeListView.getSelectionModel().clearSelection();
				coursesToTakeList.remove(potential);
				coursesTakingList.add(potential);
				
				String courseNumber = potential;
				Course course = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber);
				double credits = course.getCourseCredits();
				EnumGrades grade = EnumGrades.getRandomGrade();
				CourseStatus status = CourseStatus.getRandomStatus();
				if(((String.valueOf(status)).contentEquals("TAKING") || ((String.valueOf(status)).contentEquals("TO_TAKE")))) {
					grade = EnumGrades.NO_GRADE;
				}
				college.getStudentCourseInfoBag().insertMiniStudentCourseInfo(new MiniStudentCourseInfo(courseNumber, credits, grade, status));
			}
//			System.out.println(college.getStudentCourseInfoBag().studentMajorCourseInfoBag());
		});

		sendLeftButton.setOnAction(e ->{
			 potential2 = coursesTakingListView.getSelectionModel().getSelectedItem();
			if(potential2 != null) {
				coursesTakingListView.getSelectionModel().clearSelection();
				coursesTakingList.remove(potential2);
				coursesToTakeList.add(potential2);
				String courseNumber = potential2;
				college.getStudentCourseInfoBag().removeMiniStudentInfoByCourseNumber(courseNumber);
			}
			
		});
		
		Button home = new Button("OK");
		home.setAlignment(Pos.BOTTOM_CENTER);
		
		home.setOnAction(e ->{
			try {
				app.mainPage(primaryStage);
			} catch (IOException e1) {
			System.out.println(e1);
			}
		});
		
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(sendRightButton, sendLeftButton,  home);
		
		gridPane.add(vBox, 1, 1);
		
		root.setCenter(gridPane);
		
		GridPane.setVgrow(root, Priority.ALWAYS);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
		public Pane getListOfCourses() {
			return root;
		}

}
