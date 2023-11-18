package main;

import java.sql.Date;
import java.util.Calendar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import view.LoginView;
import view.Navbar;
import view.menu_item_view_admin.MenuItemAddView;
import view.menu_item_view_admin.MenuItemManagementView;
import view.menu_item_view_customer.MenuCustomerView;

public class Main extends Application {
	private Scene scene;
	private static BorderPane mainPane;
	private static User currentUser;
	
	private User admin = new User(0, "admin", "Admin", "admin@admin.com", "123admin");
	
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		currentUser = admin;
		
		mainPane = new BorderPane();
		mainPane.setTop(new Navbar());
		mainPane.setCenter(new MenuCustomerView()); // Default page
		mainPane.setCenter(new LoginView()); // Default page
		
		scene = new Scene(mainPane, 700, 500);
		arg0.setTitle("Mystic Grills");
		arg0.setResizable(false);
		arg0.getIcons().clear();
		arg0.getIcons().add(new Image("./assets/MysticGrills.jpg"));
		arg0.setScene(scene);
		arg0.show();
	}
}
