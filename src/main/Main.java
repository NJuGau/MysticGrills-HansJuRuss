package main;

import controller.UserController;
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
import model.MenuItem;
import model.Order;
import model.User;
import view.LoginView;
import view.Navbar;
import view.menu_item_view_admin.MenuItemAddView;
import view.menu_item_view_admin.MenuItemManagementView;
import view.menu_item_view_customer.MenuCustomerView;
import view.order_management.OrderManagementView;

public class Main extends Application {
	private Scene scene;
	
	//mainPane akan diakses di dalam semua view untuk melakukan pergantian view
	private static BorderPane mainPane;
	
	public Main() {
		
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
		scene = new Scene(mainPane, 1080, 720);
		
		mainPane.setCenter(new LoginView());
		arg0.setTitle("Mystic Grills");
		arg0.setResizable(false);
		arg0.getIcons().clear();
		arg0.getIcons().add(new Image("./assets/MysticGrills.jpg"));
		arg0.setScene(scene);
		arg0.show();
	}
}
