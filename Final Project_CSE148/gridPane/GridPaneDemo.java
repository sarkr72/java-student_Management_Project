package gridPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GridPaneDemo extends Application {
	private Button[] buttons = new Button[9];

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		for (int i = 0; i < 9; i++) {
			buttons[i] = new Button("Button " + i);
//			gridPane.add(buttons[i], i, i);
		}
		
		int row = 0;
		int col= 0;
		int i= 0;
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					gridPane.add(buttons[i++], j, k);
				}
			}

		BorderPane root = new BorderPane();
		VBox box = new VBox(5);
		root.setMargin(box, new Insets(50));
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(gridPane);
//		root.setAlignment(Pos.CENTER);
		
		root.setCenter(gridPane);
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}

}
