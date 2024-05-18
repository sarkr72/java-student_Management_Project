package listView;

import java.io.IOException;

import app.AppDemo;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.College;

public class ListViewDemo{

//	public static void main(String[] args) {
//		launch(args);
//	}
//	@Override
//	public void start(Stage primaryStage) throws Exception {
	public static Pane getCollges(Stage primaryStage, AppDemo app, College college) {
		Label response  = new Label("select a college: ");
		
		ListView<String> lvColleges;
		Text title =  new Text("NY Colleges");
		FlowPane root = new FlowPane(10, 10);
		root.setAlignment(Pos.CENTER);
		ObservableList<String> colleges = FXCollections.observableArrayList(
				"SCCC", "NCCC", "SBU", "NYU", "COLUMBIA", "BRINGHAMTON", "BUFFALO"
				);
		
		lvColleges = new ListView<String>(colleges);
		lvColleges.setPrefSize(300, 150);
		
		MultipleSelectionModel<String> lvSelModel = lvColleges.getSelectionModel();
		lvSelModel.setSelectionMode(SelectionMode.MULTIPLE);
		lvSelModel.selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> changed, String oldVal, String newVal) {
			response.setText("you selected " + newVal);
			}
			
		});
		
		Button bt = new Button("SUBMIT");
		root.getChildren().add(title);
		root.getChildren().add(lvColleges);
		root.getChildren().add(response);
		root.getChildren().add(bt);
//		Scene scene = new Scene(root, 350, 300);
//		primaryStage.setTitle("ListView");
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
		bt.setOnAction(e2 ->{
			try {
				app.mainPage(app, primaryStage, college);
			} catch (IOException e1) {
				System.out.println(e1);
			}
		});
		return root;
		}

}
