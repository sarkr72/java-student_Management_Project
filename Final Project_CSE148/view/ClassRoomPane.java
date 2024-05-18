package view;

import alerts.Alerts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Classroom;
import model.College;
import model.EnumBuildings;

public class ClassRoomPane {

	private College college;
	private TextField roomNumberField;
	private TextField buildingField;

	private Button insertButton;
	private Button searchButton;
	private Button updateButton;
	private Button removeButton;

	private HBox buttonBox;
	private HBox textFieldBox;
	private VBox root;

	private Classroom classRoom;
	private String roomNumber;
	private EnumBuildings building;
	
	private TextArea textArea;
	
	public ClassRoomPane(College college) {
		buildTextFields();
		buildButtons();
		buildHboxes();
		buildVBox();
		handleEvent();
		this.college = college;
	}

	private void buildTextFields() {
		roomNumberField = new TextField();
		buildingField = new TextField();
		roomNumberField.setPromptText("ROOM NUMBER");
		buildingField.setPromptText("BUILDING");
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
		textArea.setMaxWidth(200);
		textArea.setMaxHeight(300);
		textArea.setPromptText("Classroom Info Bag");
		root.getChildren().addAll(textFieldBox, buttonBox, textArea);
	}

	private void buildHboxes() {
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);

		textFieldBox.getChildren().addAll(roomNumberField, buildingField);
		buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
	}

	public void handleEvent() {

		insertButton.setOnAction(e -> {
			roomNumber = roomNumberField.getText();
			building = EnumBuildings.valueOf(buildingField.getText());
			classRoom = new Classroom(roomNumber, building);
			college.getClassRoomBag().insertClassroom(classRoom);
			
			String info = roomNumber + "    " +building +  "\n";
			textArea.appendText(info);
			
			roomNumberField.clear();
			buildingField.clear();
		});

		searchButton.setOnAction(s -> {
			try {
				roomNumber = roomNumberField.getText();
				classRoom = college.getClassRoomBag().findClassRoomByRoomNumber(roomNumber);
				System.out.println("classroom" + classRoom);
				if (classRoom == null) {
					roomNumber = Alerts.getDialog("Enter correct classRoom");
					classRoom = college.getClassRoomBag().findClassRoomByRoomNumber(roomNumber);
					roomNumberField.setText(roomNumber);
				} else {
					building = classRoom.getRoomBuilding();

//				college.getClassRoomBag().displayClassrooms();

					roomNumberField.setText(roomNumber);
					buildingField.setText(String.valueOf(building));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		});

		updateButton.setOnAction(s -> {
			try {
				roomNumber = roomNumberField.getText();
				classRoom = college.getClassRoomBag().findClassRoomByRoomNumber(roomNumber);
				if (classRoom == null) {
					roomNumber = Alerts.getDialog("Enter correct room Number");
					classRoom = college.getClassRoomBag().findClassRoomByRoomNumber(roomNumber);
					roomNumberField.setText(roomNumber);
				} else {
					building = EnumBuildings.valueOf(buildingField.getText());

					classRoom.setRoomBuilding(building);

					String info = "New :" + roomNumber + "    " +building +  "\n";
					textArea.appendText(info);
					
					roomNumberField.clear();
					buildingField.clear();
				}
			} catch (Exception e) {
				System.out.println(e + "\n file not found");
			}
		});

		removeButton.setOnAction(s -> {
			try {
				roomNumber = roomNumberField.getText();
				classRoom = college.getClassRoomBag().removeClassroomByRoomNumber(roomNumber);
				if (classRoom == null) {
					roomNumber = Alerts.getDialog("Enter correct room number");
					classRoom = college.getClassRoomBag().removeClassroomByRoomNumber(roomNumber);
				} else {
					roomNumberField.clear();
					buildingField.clear();
				}
			} catch (Exception e) {
				System.out.println(e + "\n file not found");
			}
		});
	}

	public Pane getPane() {
		return root;
	}
}
