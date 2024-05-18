package alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public static void getAlert(String str) {
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Error Alert");
		alert1.setHeaderText("This is an  error alert");
		alert1.setContentText(str);
		alert1.showAndWait();
	}
	
	public static String getDialog(String str) {
		TextInputDialog dialog =  new TextInputDialog();
		dialog.setTitle("Text Needed");
		dialog.setHeaderText("Some is wrong");
		dialog.setContentText(str);
		
		Optional<String> result = dialog.showAndWait();
//		if(result.isPresent()) {
//			System.out.println("your " + str + " is " + result.get());
//		}
		return result.get();
	}
}
