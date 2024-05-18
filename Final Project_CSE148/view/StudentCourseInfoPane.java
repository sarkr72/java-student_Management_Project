package view;

import java.io.FileNotFoundException;

import alerts.Alerts;
import comboBox.ComboBoxDemo;
import dualgridPane.DualGridPane;
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
import model.EnumGrades;
import model.MiniStudentCourseInfo;
import model.Name;
import model.Person;
import model.Student;
public class StudentCourseInfoPane {

		private College college;
		private TextField courseNumberField;
		private TextField gradeField;
		private TextField courseStatusField;
		private TextField courseCreditsField;
//		private TextField idField;
		
		private Button insertButton;
		private Button searchButton;
		private Button updateButton;
		private Button removeButton;
		
		private HBox buttonBox;
		private HBox textFieldBox;
		private VBox root;
		
		private MiniStudentCourseInfo studentCourseInfo;
		private String courseNumber;
		private EnumGrades grade;
		private CourseStatus courseStatus;
		private double courseCredits;
		
		private TextArea textArea;
		
		DualGridPane pane2;
		public StudentCourseInfoPane(College college) {
			buildTextFields();
			buildButtons();
			buildHboxes();
			buildVBox();
			handleEvent();
			this.college = college;
			
		}
		
		private void buildTextFields() {
			courseNumberField = new TextField();
			gradeField = new TextField();
			courseCreditsField = new TextField();
			courseStatusField = new TextField();
			gradeField.setDisable(true);
			
			courseNumberField.setPromptText("COURSE NUMBER");
//			gradeField.setPromptText("GRADE WILL BE TAKEN AUTO");
			courseCreditsField.setPromptText("COURSE CREDITS");
			courseStatusField.setPromptText("COURSE STATUS");
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
			textArea.setPromptText("Student Course Info Bag");
			root.getChildren().addAll(textFieldBox, buttonBox, textArea);
		}
		private void buildHboxes() {
			textFieldBox =  new HBox(10);
			buttonBox =  new HBox(10);
			textFieldBox.setAlignment(Pos.CENTER);
			buttonBox.setAlignment(Pos.CENTER);
			
			textFieldBox.getChildren().addAll(courseNumberField, courseCreditsField, ComboBoxDemo.getGradeBox(), courseStatusField);
			buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
		}
		
		public void handleEvent() {
			
			insertButton.setOnAction(e -> {
				try {
					Helper.courses(college.getMasterCourseBag());
				} catch (FileNotFoundException e1) {
				System.out.println(e1);
				}

				 courseNumber = courseNumberField.getText();
				 grade = ComboBoxDemo.getGrade();
				 gradeField.setText(grade+"");
//				 gradeField.getText(grade+"");
				 courseStatus = CourseStatus.valueOf(courseStatusField.getText());
				 courseCredits = Double.parseDouble(courseCreditsField.getText());
			
				studentCourseInfo = new MiniStudentCourseInfo(courseNumber, courseCredits, grade, courseStatus);
				college.getStudentCourseInfoBag().insertMiniStudentCourseInfo(studentCourseInfo);
			
				String title2 = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber).getCourseTitle();
				
				String info = "CourseNumber: " + courseNumber + "    Title: " +title2+"     Credits: " +courseCredits + "     Grade: "+ studentCourseInfo.convertGrade() +"     Status"+ courseStatus + "\n";
				textArea.appendText(info);
				
				courseNumberField.clear();
				gradeField.clear();
				courseCreditsField.clear();
				courseStatusField.clear();
			});
			
			searchButton.setOnAction(s -> {
				try {
				courseNumber = courseNumberField.getText();
				studentCourseInfo = college.getStudentCourseInfoBag().findMiniStuentInfoByCourseNumber(courseNumber);
				if(studentCourseInfo == null) {
					courseNumber = Alerts.getDialog("Enter ther correct course number");
					studentCourseInfo = college.getStudentCourseInfoBag().findMiniStuentInfoByCourseNumber(courseNumber);
					courseNumberField.setText(courseNumber);
				}else {
				courseNumberField.setText(studentCourseInfo.getCourseNumber());
				courseCreditsField.setText(studentCourseInfo.getNumberOfCredits()+"");
				gradeField.setText(studentCourseInfo.getLetterGrade()+"");
				String status = studentCourseInfo.getCourseStatus()+"";
				courseStatusField.setText(status);
				college.getPersonBag().display();
				}}catch(Exception e) {
				System.out.println(e + "\n file not found");
			}
			});
			
			updateButton.setOnAction(s -> {
				try {
					courseNumber = courseNumberField.getText();
					studentCourseInfo = college.getStudentCourseInfoBag().findMiniStuentInfoByCourseNumber(courseNumber);
					
					if(studentCourseInfo == null) {
						courseNumber = Alerts.getDialog("Enter ther correct course number");
						studentCourseInfo = college.getStudentCourseInfoBag().findMiniStuentInfoByCourseNumber(courseNumber);
						courseNumberField.setText(courseNumber);
					}else {
					
					courseNumber = courseNumberField.getText();
					courseCredits = Double.parseDouble(courseCreditsField.getText());
					String grade2 = ComboBoxDemo.getGrade()+"";
					gradeField.setText(grade2);
					courseStatus = CourseStatus.valueOf(courseStatusField.getText());
					
					studentCourseInfo.setCourseStatus(courseStatus);
					studentCourseInfo.setLetterGrade(EnumGrades.valueOf(grade2));
					studentCourseInfo.setNumberOfCredits(courseCredits);
					
					String title2 = college.getMasterCourseBag().findCourseByCourseNumber(courseNumber).getCourseTitle();
					
					String info = "New: " +courseNumber + "    " +title2+"     " +courseCredits + "     "+ grade +"     "+ courseStatus + "\n";
					textArea.appendText(info);
					
					courseNumberField.clear();
					courseCreditsField.clear();
					gradeField.clear();
					courseStatusField.clear();
					
				}}catch(Exception e) {
					System.out.println(e + "\n file not found");
				}
			});
			
			removeButton.setOnAction(s -> {
				try {
				courseNumber = courseNumberField.getText();
				studentCourseInfo = college.getStudentCourseInfoBag().removeMiniStudentInfoByCourseNumber(courseNumber);
				if(studentCourseInfo == null) {
					courseNumber = Alerts.getDialog("Enter ther correct course number");
					studentCourseInfo = college.getStudentCourseInfoBag().removeMiniStudentInfoByCourseNumber(courseNumber);
				}else {
				courseNumberField.clear();
				courseCreditsField.clear();
				gradeField.clear();
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
