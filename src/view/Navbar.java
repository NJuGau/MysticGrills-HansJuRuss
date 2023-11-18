package view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import main.Main;
import view.menu_item_view_admin.MenuItemManagementView;
import view.menu_item_view_customer.MenuCustomerView;
import view.order_view.OrderListView;
import view.user_management_view_admin.UserManagementView;

public class Navbar extends MenuBar{
	private Label homeLbl, myOrderLbl;
	private Menu homeMenu, myOrderMenu, adminMenu;
	private MenuItem userManagementMenu, menuItemManagementMenu;

	public Navbar() {
		
		homeLbl = new Label("Menu");
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
		
		if(Main.getCurrentUser().getUserRole() == "admin") {
			adminMenu = new Menu("Admin");
			
			menuItemManagementMenu = new MenuItem("Menu Item Management");
			menuItemManagementMenu.setOnAction(event -> {
				Main.getMainPane().setCenter(new MenuItemManagementView());
			});
			
			userManagementMenu = new MenuItem("User Management");
			userManagementMenu.setOnAction(event -> {
				Main.getMainPane().setCenter(new UserManagementView());
			});
			
			adminMenu.getItems().addAll(menuItemManagementMenu, userManagementMenu);
			this.getMenus().add(adminMenu);
		}
	}

}
