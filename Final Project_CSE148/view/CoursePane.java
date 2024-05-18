package view;

import alerts.Alerts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.College;
import model.Course;

public class CoursePane {
	
	private College college;
	private TextField courseNumberField;
	private TextField courseTitleField;
	private TextField courseDescField;
	private TextField courseCreditsField;
	
	private Button insertButton;
	private Button searchButton;
	private Button updateButton;
	private Button removeButton;
	
	private HBox buttonBox;
	private HBox textFieldBox;
	private VBox root;
	
	private Course course;
	private String courseNumber;
	private String courseTitle;
	private String courseDesc;
	private double courseCredits;
	
	private TextArea textArea;
	
	public CoursePane(College college) {
		buildTextFields();
		buildButtons();
		buildHboxes();
		buildVBox();
		handleEvent();
		this.college = college;
	}
	
	private void buildTextFields() {
		courseNumberField = new TextField();
		courseTitleField = new TextField();
		courseDescField = new TextField();
		courseCreditsField = new TextField();
		
		courseNumberField.setPromptText("COURSE NUMBER");
		courseTitleField.setPromptText("COURSE TITLE");
		courseDescField.setPromptText("COURSE DESCRIPTION");
		courseCreditsField.setPromptText("COURSE CREDITS");
//		idField.setDisable(false);
	}

	private void buildButtons() {
		insertButton = new Button("insert");
		searchButton = new Button("SEARCH");
		updateButton = new Button("UPDATE");
		removeButton = new Button("REMOVE");
	}
	
	private void buildVBox() {
		root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		textArea = new TextArea();
		textArea.setMaxWidth(500);
		textArea.setMaxHeight(200);
		textArea.setPromptText("Course Info Bag");
		root.getChildren().addAll(textFieldBox, buttonBox, textArea);
	}
	private void buildHboxes() {
		textFieldBox =  new HBox(10);
		buttonBox =  new HBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);

		textFieldBox.getChildren().addAll(courseNumberField, courseTitleField, courseDescField, courseCreditsField);
		buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
	}
	
	public void handleEvent() {
		insertButton.setOnAction(e -> {
			 courseNumber = courseNumberField.getText();
			 courseTitle = courseTitleField.getText();
			 courseTitleField.setText(courseTitle);
			 courseDesc = courseDescField.getText();
			 courseCredits = Double.parseDouble(courseCreditsField.getText());
		
			course = new Course(courseNumber, courseTitle, courseDesc, courseCredits);
			college.getMasterCourseBag().insertCourse(course);
			
			String info = courseNumber + "    " +courseTitle+"     " + courseDesc + "     " + courseCredits + "\n";
			textArea.appendText(info);
			
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescField.clear();
			courseCreditsField.clear();
		});
		
		searchButton.setOnAction(s -> {
			try {
			 courseNumber = courseNumberField.getText();
			course = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber);
			if(course == null) {
			courseNumber = Alerts.getDialog("Enter correct ID");
				course = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber);
				courseNumberField.setText(courseNumber);
			}else {
			courseTitle = course.getCourseTitle();
			courseDesc = course.getCourseDescription();
			courseCredits = course.getCourseCredits();
			String credits = courseCredits+"";
			
			courseNumberField.setText(courseNumber);
			courseTitleField.setText(courseTitle);
			courseDescField.setText(courseDesc);
			courseCreditsField.setText(credits);
			}}catch (Exception e) {
			System.out.println(e + "\n file not found");
		}});
		
		updateButton.setOnAction(s -> {
			try {
				 courseNumber = courseNumberField.getText();
					course = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber);
			if(course == null) {
				courseNumber = Alerts.getDialog("Enter correct ID");
				course = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber);
				courseNumberField.setText(courseNumber);
			}else {
					courseTitle = courseTitleField.getText();
					courseDesc = courseDescField.getText();
					courseCredits = Double.parseDouble(courseCreditsField.getText());
					
			course.setCourseTitle(courseTitle);
			course.setCourseDescription(courseDesc);
			course.setCourseCredits(courseCredits);
			
			String info = "New: " + courseNumber + "    " +courseTitle+"     " + courseDesc + "     " + courseCredits + "\n";
			textArea.appendText(info);
			
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescField.clear();
			courseCreditsField.clear();
			}}catch (Exception e) {
			System.out.println(e + "\n file not found");
		}});
		
		removeButton.setOnAction(s -> {
			try {
			 courseNumber = courseNumberField.getText();
			 course = college.getMasterCourseBag().removeCourseByCourseNumber(courseNumber);
			 if(course == null) {
				 courseNumber = Alerts.getDialog("Enter correct ID");
					course = college.getMasterCourseBag().removeCourseByCourseNumber(courseNumber);
			 }
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescField.clear();
			courseCreditsField.clear();
		}catch(Exception e) {
			System.out.println(e + "\n file not found");
		}
		});
	}
	public Pane getPane() {
		return root;
	}
}
