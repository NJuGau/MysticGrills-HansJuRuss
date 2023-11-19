package view;

import controller.UserController;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import main.Main;
import view.menu_item_view_admin.MenuItemManagementView;
import view.menu_item_view_customer.MenuCustomerView;

import view.receipt_cashier.ReceiptManagementView;
import view.user_management_view_admin.UserManagementView;

public class Navbar extends MenuBar{
	Label menuItemManagementLbl, homeLbl, userManagementLbl, receiptManagementLbl;
	Menu menuItemManagementMenu, homeMenu, userManagementMenu, receiptManagementMenu;

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
		
		if(UserController.getCurrentUser().getUserRole().equals("Admin")) {
			menuItemManagementLbl = new Label("Menu Item Management");
			menuItemManagementMenu = new Menu("", menuItemManagementLbl);
			menuItemManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new MenuItemManagementView());
			});
			
			userManagementMenu = new MenuItem("User Management");
			userManagementMenu.setOnAction(event -> {
				Main.getMainPane().setCenter(new UserManagementView());
			});
			this.getMenus().addAll(menuItemManagementMenu, userManagementMenu);
		}else if(UserController.getCurrentUser().getUserRole().equals("Cashier")) {
			receiptManagementLbl = new Label("Receipt Management");
			receiptManagementMenu = new Menu("", receiptManagementLbl);
			receiptManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new ReceiptManagementView());
			});
			this.getMenus().addAll(receiptManagementMenu);
		}
		
		
	}

}
