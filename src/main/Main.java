package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.LoginView;
import view.MenuItemView.MenuItemAddView;
import view.MenuItemView.MenuItemManagementView;

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
		mainPane.setCenter(new MenuItemManagementView());
		
		scene = new Scene(mainPane, 700, 500);
		arg0.setTitle("Mystic Grills");
		arg0.setResizable(true);
		arg0.getIcons().clear();
		arg0.getIcons().add(new Image("./assets/MysticGrills.jpg"));
		arg0.setScene(scene);
		arg0.show();
	}
}
