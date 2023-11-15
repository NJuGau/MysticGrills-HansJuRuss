package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	Scene scene;
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane pane = new BorderPane();
		scene = new Scene(pane, 500, 500);
		arg0.setScene(scene);
		arg0.show();
	}
}
