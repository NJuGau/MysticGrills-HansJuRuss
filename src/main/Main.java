package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {
	private Scene scene;
	private static BorderPane mainPane;
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		mainPane = new BorderPane();
		GridPane loginPane = new LoginView();
		mainPane.setCenter(loginPane);
		
		BorderPane.setMargin(loginPane, new Insets(20, 0, 0, 0));
		
		scene = new Scene(mainPane, 500, 500);
		arg0.setScene(scene);
		arg0.show();
	}
}
