package alerts;

import javafx.application.Application;
import javafx.stage.Stage;

public class DemoAlerts extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String s = Alerts.getDialog("Phone number is incorrect");
//		System.out.println(s);
//		primatyStage
	}

}
