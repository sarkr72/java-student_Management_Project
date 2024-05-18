package view;
import alerts.Alerts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.College;
import model.Faculty;
import model.MiniFacultyCourseBag;
import model.MiniFacultyCourseInfo;
import model.Name;
import model.Person;
import model.Student;
import model.Title;

public class FacultyPane {

		private College college;
		private TextField titleField;
		private TextField salaryField;
		private TextField officePhoneField;
		private TextField firstNameField;
		private TextField lastNameField;
		private TextField idField;
		
		private Button insertButton;
		private Button searchButton;
		private Button updateButton;
		private Button removeButton;
		
		
		private HBox ID;
		private HBox buttonBox;
		private HBox textFieldBox;
		private VBox root;
		private HBox labelBox;
		private HBox facultyBox;
		
		Label label;
		
		private Person faculty;
		private Title title;
		private double salary;
		private String officePhone;
		private Name name;
		private String firstName;
		private String lastName;
		private  String id;
		
		private TextArea textArea;
		
		public FacultyPane(College college) {
			buildTextFields();
			buildButtons();
			buildHboxes();
			buildVBox();
			handleEvent();
			this.college = college;
		}
		
		private void buildTextFields() {
			titleField = new TextField();
			salaryField = new TextField();
			officePhoneField = new TextField();
			firstNameField = new TextField();
			lastNameField = new TextField();
			idField = new TextField();
			
			titleField.setPromptText("TITLE");
			salaryField.setPromptText("SALARY");
			officePhoneField.setPromptText("OFFICE PHONE");
			firstNameField.setPromptText("FIRST NAME");
			lastNameField.setPromptText("LAST NAME");
			idField.setPromptText("SEARCH BY ID");
//			idField.setDisable(true);
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
//			Pane pane2 = new FacultyCourseInfoPane(college).getPane();
			textArea = new TextArea();
			textArea.setMaxWidth(300);
			textArea.setMaxHeight(200);
			textArea.setPromptText("Faculty Info Bag");
			root.getChildren().addAll(facultyBox, textFieldBox, buttonBox, textArea);
		}
		private void buildHboxes() {
			textFieldBox =  new HBox(10);
			buttonBox =  new HBox(10);
			labelBox = new HBox(10);
			facultyBox = new HBox(10);
//			label = new Label("FACULTLY COURSE INFO");
			Label faculty = new Label("FACULTY");
			
			textFieldBox.setAlignment(Pos.CENTER);
			buttonBox.setAlignment(Pos.CENTER);
			labelBox.setAlignment(Pos.CENTER);
			facultyBox.setAlignment(Pos.CENTER);
			
			facultyBox.getChildren().add(faculty);
			textFieldBox.getChildren().addAll(firstNameField, lastNameField, titleField, salaryField, officePhoneField, idField);
			buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
//			labelBox.getChildren().add(label);
		}
		
		public void handleEvent() {
			insertButton.setOnAction(e -> {
				firstName = firstNameField.getText();
				lastName =  lastNameField.getText();
				name = new Name(firstName, lastName);
				 title = Title.valueOf(titleField.getText());
				 salary = Double.parseDouble(salaryField.getText());
				 officePhone = officePhoneField.getText();
				 if(officePhone.length() != 10) {
					 officePhone = Alerts.getDialog("Enter A 10 Digits number");
					 officePhoneField.setText(officePhone);
				 }else {
				 MiniFacultyCourseBag mfc = new MiniFacultyCourseBag(1000);
				 faculty = new Faculty(name, title, salary, officePhone, mfc);
				college.getPersonBag().insert(faculty);
				id = faculty.getId();
				
				String info = name + "    Id: " + id + "     " +"title :"+title+"     salary :" + salary + "      officePhone: "+ officePhone + "\n";
				textArea.appendText(info);
				
				firstNameField.clear();
				lastNameField.clear();
				titleField.clear();
				salaryField.clear();
				officePhoneField.clear();
			}});
			
			searchButton.setOnAction(s -> {
				try {
				 String id = idField.getText();
				 faculty = college.getPersonBag().searchById(id);
				 if(faculty == null) {
					 id = Alerts.getDialog("Enter the correct id");
					 faculty = college.getPersonBag().searchById(id);
					 idField.setText(id);
				 }else {
				firstNameField.setText(((Faculty) faculty).getName().getFirstName());
				lastNameField.setText(((Faculty) faculty).getName().getLastName());
				String title2 = ((Faculty) faculty).getTitle()+"";
				
				titleField.setText(title2);
				salaryField.setText((((Faculty) faculty).getSalary())+"");
				officePhoneField.setText(((Faculty) faculty).getOfficePhone());
				 }}catch(Exception e) {
					System.out.println(e + "\n file not found");
				}
			});
			
			updateButton.setOnAction(e -> {
				 id = idField.getText();
				faculty = college.getPersonBag().searchById(id);
				 if(faculty == null) {
					 id = Alerts.getDialog("Enter the correct id");
					 faculty = college.getPersonBag().searchById(id);
					 idField.setText(id);
				 }else {
				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				name = new Name(firstName, lastName);
				title = Title.valueOf(titleField.getText());
				salary = Double.parseDouble(salaryField.getText());
				officePhone = officePhoneField.getText();
				 if(officePhone.length() != 10) {
					 officePhone = Alerts.getDialog("Enter A 10 Digits number");
					 officePhoneField.setText(officePhone);
				 }else {
				((Faculty) faculty).setName(name);
				((Faculty) faculty).settitle(title);
				((Faculty) faculty).setSalary(salary);
				((Faculty) faculty).setOfficePhone(officePhone);
				
				String info = "New: " +name + "Id: " + id+"title :"+title+"     salary :" + salary + "     "+ officePhone + "\n";
				textArea.appendText(info);
				
				idField.clear();
				firstNameField.clear();
				lastNameField.clear();
				titleField.clear();
				salaryField.clear();
				officePhoneField.clear();
				 }}});
			
			removeButton.setOnAction(e -> {
				 id = idField.getText();
				faculty = college.getPersonBag().removeStudentById(id);
				 if(faculty == null) {
					 id = Alerts.getDialog("Enter the correct id");
					 faculty = college.getPersonBag().removeStudentById(id);
				 }else {
				idField.clear();
				firstNameField.clear();
				lastNameField.clear();
				titleField.clear();
				salaryField.clear();
				officePhoneField.clear();
				 }});
			
//			facultyCourseInfoBtn.setOnAction(e ->{
//				new FacultyCourseInfoPane(college).getPane();
//			});
		}
		public Pane getPane() {
			return root;
		}

}
