package alerts;



import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Demo extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Error Alert");
		alert1.setHeaderText("This is an  error alert");
		alert1.setContentText("Correct this error alert");
		alert1.showAndWait();
		
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("Warning Alert");
		alert2.setHeaderText("This is an  warning alert");
		alert2.setContentText("Correct this warning alert");
		alert2.showAndWait();
		
		Alert alert3 = new Alert(AlertType.INFORMATION);
		alert3.setTitle("Information Alert");
		alert3.setHeaderText("This is an  infomation alert");
		alert3.setContentText("Correct this infomation alert");
		alert3.showAndWait();
		
		TextInputDialog dialog =  new TextInputDialog();
		dialog.setTitle("Text Needed");
		dialog.setHeaderText("Some is wrong");
		dialog.setContentText("Please re-enter your phone: ");
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			System.out.println("your name is " + result.get());
		}
		
		primaryStage.show();
	}

}
