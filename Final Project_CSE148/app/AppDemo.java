package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menuAndItems.MenuAndItems;
import model.College;

public class AppDemo extends Application{
// RINKU SARKAR
//	MenuAndItems borderPane = new MenuAndItems();
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		College college = new College();
		AppDemo app = new AppDemo();
		mainPage(app,primaryStage, college);
//		MenuAndItems.getMenu(primaryStage, college);
	}
	
	public void mainPage(AppDemo app, Stage primaryStage, College college) throws IOException {
		MenuAndItems.getMenu(app,primaryStage, college);
//		MenuAndItems borderPane = new MenuAndItems();
//		Pane root = borderPane.getMenu(primaryStage,app, college);
//		Scene scene = new Scene(root, 600, 500);
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}

}
