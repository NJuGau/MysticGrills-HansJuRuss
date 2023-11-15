package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.MenuItemView.MenuItemManagementView;

public class Main extends Application {
	private Scene scene;
	private static BorderPane mainPane;
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    public static BorderPane getMainPane() {
        return mainPane;
    }

	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane pane = new MenuItemManagementView();
		scene = new Scene(pane, 700, 500);
		arg0.setTitle("Mystic Grills");
		arg0.setResizable(true);
		arg0.getIcons().clear();
		arg0.getIcons().add(new Image("./assets/MysticGrills.jpg"));
		arg0.setScene(scene);
		arg0.show();
	}
}
