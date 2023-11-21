package view;

import controller.UserController;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import main.Main;
import view.menu_item_view_admin.MenuItemManagementView;
import view.menu_item_view_customer.MenuCustomerView;
import view.order_management.OrderManagementView;
import view.order_view.OrderListView;
import view.user_management_view_admin.UserManagementView;

public class Navbar extends MenuBar{
	private Label homeLbl, receiptManagementLbl, myOrderLbl;
	private Menu homeMenu, receiptManagementMenu, myOrderMenu, adminMenu;
	private MenuItem menuItemManagementMenu, userManagementMenu;

	public Navbar() {
		
		homeLbl = new Label("Menu List");
		homeMenu = new Menu("", homeLbl);
		homeLbl.setOnMouseClicked(event -> {
			Main.getMainPane().setCenter(new MenuCustomerView());
		});
		
		myOrderLbl = new Label("My Order");
		myOrderMenu = new Menu("", myOrderLbl);
		myOrderLbl.setOnMouseClicked(event -> {
			Main.getMainPane().setCenter(new OrderListView());
		});
				
		this.getMenus().addAll(homeMenu, myOrderMenu);
		
		if(UserController.getCurrentUser().getUserRole().equals("Admin")) {
			menuItemManagementMenu = new MenuItem("Menu Item Management");
			menuItemManagementMenu.setOnAction(event -> {
				Main.getMainPane().setCenter(new MenuItemManagementView());
			});
			
			userManagementMenu = new MenuItem("User Management");
			userManagementMenu.setOnAction(event -> {
				Main.getMainPane().setCenter(new UserManagementView());
			});
			
			adminMenu = new Menu("Admin");
			adminMenu.getItems().addAll(menuItemManagementMenu, userManagementMenu);
			
			
			this.getMenus().addAll(adminMenu);
		}
		else if(UserController.getCurrentUser().getUserRole().equals("Cashier")) {
			receiptManagementLbl = new Label("Receipt Management");
			receiptManagementMenu = new Menu("", receiptManagementLbl);
			receiptManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new OrderManagementView());
			});
			this.getMenus().addAll(receiptManagementMenu);
		}
		
		
	}

}
