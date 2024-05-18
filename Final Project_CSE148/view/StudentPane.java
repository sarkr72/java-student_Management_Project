package view;

import java.io.FileNotFoundException;

import alerts.Alerts;
import app.AppDemo;
import borderPane.ButtonPane;
import comboBox.ComboBoxDemo;
import dualgridPane.DualGridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.College;
import model.Major;
import model.MiniStudentCourseBag;
import model.Name;
import model.Person;
import model.Student;

public class StudentPane {

	private College college;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField searchIdField;
	private TextField majorField;

	private Button insertButton;
	private Button searchButton;
	private Button updateButton;
	private Button removeButton;
	
	private Button majorBtn;

	private HBox buttonBox;
	private HBox textFieldBox;
	private VBox root;
	private HBox studentBox;
	private HBox studentCourseInfoBox;

	private Person student;
	private String id;
	private String  searchId;
	private Name name;
	private String firstName;
	private String lastName;
	private Major major;

//	Pane pane = new StudentCourseInfoPane(college).getPane();
	private ButtonPane buttons = new ButtonPane();
	private TextArea textArea;
	
	private Stage primaryStage;
	private AppDemo app;
	
	public StudentPane(College college, Stage primaryStage, AppDemo app) {
		buildTextFields();
		buildButtons();
		buildHboxes();
		buildVBox();
		handleEvent();
		this.college = college;
		this.primaryStage = primaryStage;
		this.app = app;
	}

	private void buildTextFields() {
		firstNameField = new TextField();
		lastNameField = new TextField();
		idField = new TextField();
		searchIdField = new TextField();
		majorField = new TextField();
		majorField.setDisable(true);
		idField.setDisable(true);
		firstNameField.setPromptText("FIRST NAME");
		lastNameField.setPromptText("LAST NAME");
		majorField.setPromptText("MAJOR");
		idField.setPromptText("ID");
		searchIdField.setPromptText("ONLY FOR SEARCH");
	}

	private void buildButtons() {
		insertButton = buttons.getInerstBtn();
		searchButton = buttons.getSearchBtn();
		updateButton = buttons.getUpdateBtn();
		removeButton = buttons.getRemoveBtn();
		majorBtn = new Button("Courses for major");
	}

	private void buildVBox() {
		root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		textArea = new TextArea();
		textArea.setMaxWidth(300);
		textArea.setMaxHeight(200);
		textArea.setPromptText("Student info Bag");
		Label label = new Label("Search student to see major courses");
		root.getChildren().addAll(studentBox, textFieldBox,  ComboBoxDemo.getMajorBox(), buttonBox, textArea, majorBtn, label);
	}

	private void buildHboxes() {
		textFieldBox = new HBox(10);
		buttonBox = new HBox(50);
		studentBox = new HBox(10);
		studentCourseInfoBox = new HBox(10);

		buttonBox.setAlignment(Pos.CENTER);
		studentCourseInfoBox.setAlignment(Pos.CENTER);
		studentBox.setAlignment(Pos.CENTER);
		textFieldBox.setAlignment(Pos.CENTER);
//		buttons.getButtonBox().setAlignment(Pos.CENTER);
		
//		studentCourseInfoBox.getChildren().add(new Label("STUDENT COURSE INFO"));
		studentBox.getChildren().add(new Label("STUDENT"));
		textFieldBox.getChildren().addAll(firstNameField, lastNameField, idField, searchIdField);
		buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
	}

	public void handleEvent() {

		insertButton.setOnAction(e -> {
			try {

				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				idField.getText();
				major = ComboBoxDemo.getMajor();
				name = new Name(firstName, lastName);

				student = new Student(name, major, college.getStudentCourseInfoBag());
				college.getPersonBag().insert(student);

				id = student.getId();
				String info = name + "    id: " + id +"     major: "+ major + "\n";
				textArea.appendText(college.getPersonBag().getPersonBagArray());
				
				firstNameField.clear();
				lastNameField.clear();
				idField.clear();
				majorField.clear();
			} catch (Exception s) {
//				System.out.println(s);
				s.printStackTrace();
			}
		});

		searchButton.setOnAction(s -> {

			searchId = searchIdField.getText();
			student = college.getPersonBag().searchById(searchId);
			
			if (student == null) {
				searchId = Alerts.getDialog("Enter correct ID");
				student = college.getPersonBag().searchById(searchId);
				idField.setText(id);
				searchIdField.setText(searchId);
			} else {
				firstNameField.setText(((Student) student).getName().getFirstName());
				lastNameField.setText(((Student) student).getName().getLastName());
				majorField.setText(((Student) student).getMajor() + "");
				idField.setText(((Student) student).getId());
				
				majorBtn.setOnAction(e1 ->{
					DualGridPane courses = new DualGridPane(primaryStage);
					try {
						courses.getMajorPane(app, college, student);
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
				});
			}
		});

		updateButton.setOnAction(s -> {
			searchId = searchIdField.getText();
			student = college.getPersonBag().searchById(searchId);
			if (student == null) {
				searchId = Alerts.getDialog("Enter correct ID");
				student = college.getPersonBag().searchById(searchId);
				idField.setText(id);
				searchIdField.setText(searchId);
			} else {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				name = new Name(firstName, lastName);
				major = ComboBoxDemo.getMajor();
				majorField.setText(major + "");
				((Student) student).setName(name);
				((Student) student).setMajor(major);
			}
			id = ((Student) student).getId();
			String info ="New: "+ name + "    " + id +"     "+ major + "\n";
			textArea.appendText(info);
			
			firstNameField.clear();
			lastNameField.clear();
			idField.clear();
			searchIdField.clear();
			majorField.clear();
		});

		buttons.getRemoveBtn().setOnAction(s -> {
			try {
				searchId = searchIdField.getText();
				student = college.getPersonBag().removeStudentById(searchId);
				if (student == null) {
					searchId = Alerts.getDialog("Enter correct ID");
					student = college.getPersonBag().removeStudentById(searchId);
					idField.setText(id);
				} else {
					firstNameField.clear();
					lastNameField.clear();
					idField.clear();
					searchIdField.clear();
					majorField.clear();
				}
			} catch (Exception e) {
				System.out.println(e + "\n file not found");
				}});
	}

	public Pane getVbox() {
		return root;
	}
}
