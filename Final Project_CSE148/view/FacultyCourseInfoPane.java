package view;

import java.io.FileNotFoundException;

import alerts.Alerts;
import comboBox.ComboBoxDemo;
import helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.College;
import model.Course;
import model.CourseStatus;
import model.FacultyCourseStatus;
import model.MiniFacultyCourseInfo;

public class FacultyCourseInfoPane {

	private College college;
	private TextField courseNumberField;
	private TextField courseTitleField;
	private TextField courseStatusField;
	private TextField courseCreditsField;
	
	private Button insertButton;
	private Button searchButton;
	private Button updateButton;
	private Button removeButton;
	
	private HBox buttonBox;
	private HBox textFieldBox;
	private VBox root;
	
	private MiniFacultyCourseInfo facultyCourseInfo;
	private String courseNumber;
	private String courseTitle;
	private double numberOfCredits;
	private FacultyCourseStatus courseStatus;
	
	private TextArea textArea;
	
	public FacultyCourseInfoPane(College college) {
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
		courseCreditsField = new TextField();
		courseStatusField = new TextField();
		
		courseNumberField.setPromptText("COURSE NUMBER");
		courseTitleField.setPromptText("COURSE TITLE");
		courseCreditsField.setPromptText("COURSE CREDITS");
		courseStatusField.setPromptText("COURSE STATUS");
	}

	private void buildButtons() {
		insertButton = new Button("INSERT");
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
		textArea.setPromptText("Faculty Course Info Bag");
		root.getChildren().addAll(textFieldBox, buttonBox, textArea);
	}
	private void buildHboxes() {
		textFieldBox =  new HBox(10);
		buttonBox =  new HBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);

		textFieldBox.getChildren().addAll(courseNumberField, courseTitleField, courseCreditsField, ComboBoxDemo.getCourseStatusBox());
		buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
	}
	
	public void handleEvent() {
		insertButton.setOnAction(e -> {
			
			 courseNumber = courseNumberField.getText();
			 courseTitle = courseTitleField.getText();
			 numberOfCredits = Double.parseDouble(courseCreditsField.getText());
			 courseStatus = ComboBoxDemo.getCourseStatus();
			 courseStatusField.setText(ComboBoxDemo.getCourseStatus()+"");
			 
			facultyCourseInfo = new MiniFacultyCourseInfo(courseNumber, courseTitle, numberOfCredits, courseStatus);
			college.getFacultyCourseBag().insertMiniFacultyCourseInfo(facultyCourseInfo);
			
			String info = courseNumber + "    " +courseTitle+"     " + numberOfCredits + "     "+ courseStatus + "\n";
			textArea.appendText(info);
		
			courseNumberField.clear();
			courseTitleField.clear();
			courseStatusField.clear();
			courseCreditsField.clear();
		});
		
		searchButton.setOnAction(s -> {
			try {
			 courseNumber = courseNumberField.getText();
			 facultyCourseInfo = college.getFacultyCourseBag().findMiniFacultyInfoByCourseNumber(courseNumber);
			if(facultyCourseInfo == null) {
				courseNumber = Alerts.getDialog("Enter correct courseNumber");
				 facultyCourseInfo = college.getFacultyCourseBag().findMiniFacultyInfoByCourseNumber(courseNumber);
				 courseNumberField.setText(courseNumber);
			}else {
			courseTitle = facultyCourseInfo.getCourseTitle();
			numberOfCredits = facultyCourseInfo.getNumberOfCredits();
			courseStatus = facultyCourseInfo.getCourseStatus();
			
			courseNumberField.setText(courseNumber);
			courseTitleField.setText(courseTitle);
			courseCreditsField.setText(numberOfCredits+"");
			courseStatusField.setText(courseStatus+"");
			}}catch(Exception e) {
				System.out.println(e + "\n file not found");
			}
		});
		
		updateButton.setOnAction(s -> {
			try {
				 courseNumber = courseNumberField.getText();
				 facultyCourseInfo = college.getFacultyCourseBag().findMiniFacultyInfoByCourseNumber(courseNumber);
				 if(facultyCourseInfo == null) {
						courseNumber = Alerts.getDialog("Enter correct courseNumber");
						 facultyCourseInfo = college.getFacultyCourseBag().findMiniFacultyInfoByCourseNumber(courseNumber);
						 courseNumberField.setText(courseNumber);
					}else {
				courseTitle = courseTitleField.getText();
				numberOfCredits = Double.parseDouble(courseCreditsField.getText());
				courseStatus = ComboBoxDemo.getCourseStatus();
				 courseStatusField.setText(ComboBoxDemo.getCourseStatus()+"");
					
			facultyCourseInfo.setCourseTitle(courseTitle);
			facultyCourseInfo.setNumberOfCredits(numberOfCredits);
			facultyCourseInfo.setCourseStatus(courseStatus);
			
			String info = "New: "+ courseNumber + "    " +courseTitle+"     " + numberOfCredits + "     "+ courseStatus + "\n";
			textArea.appendText(info);
			
			courseNumberField.clear();
			courseTitleField.clear();
			courseStatusField.clear();
			courseCreditsField.clear();
		}}catch(Exception e) {
			System.out.println(e + "\n file not found");
		}
		});
		
		removeButton.setOnAction(s -> {
			try {
			 courseNumber = courseNumberField.getText();
			 facultyCourseInfo = college.getFacultyCourseBag().removeMiniFacultyInfoByCourseNumber(courseNumber);
			 if(facultyCourseInfo == null) {
					courseNumber = Alerts.getDialog("Enter correct courseNumber");
					 college.getFacultyCourseBag().findMiniFacultyInfoByCourseNumber(courseNumber);
			}else {
			courseNumberField.clear();
			courseTitleField.clear();
			courseCreditsField.clear();
			courseStatusField.clear();
			}}catch(Exception e) {
			System.out.println(e + "\n file not found");
		}
		});
	}
	public Pane getPane() {
		return root;
	}
}
