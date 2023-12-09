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
import view.order_management.cashier.CashierOrderDetailView;
import view.order_management.cashier.CashierReceiptManagementView;
import view.order_view.OrderListView;
import view.user_management_view_admin.UserManagementView;

public class Navbar extends MenuBar{
	private Label homeLbl, orderManagementLbl, myOrderLbl, logoutLbl, menuItemManagementLbl, userManagementLbl, receiptManagementLbl;
	private Menu homeMenu, orderManagementMenu, myOrderMenu, logoutMenu, menuItemManagementMenu, userManagementMenu, receiptManagementMenu;

	public Navbar() {
		
		logoutLbl = new Label("Logout");
		logoutMenu = new Menu("", logoutLbl);
		logoutLbl.setOnMouseClicked(event -> {
			UserController.setCurrentUser(null);
			Main.getMainPane().setTop(null);
			Main.getMainPane().setCenter(new LoginView());
		});
		
		if(UserController.getCurrentUser().getUserRole().equals("Customer")) {
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
					
			this.getMenus().addAll(homeMenu, myOrderMenu, logoutMenu);
		}
		
		if(UserController.getCurrentUser().getUserRole().equals("Admin")) {
			menuItemManagementLbl = new Label("Menu Item Management");
			menuItemManagementMenu = new Menu("", menuItemManagementLbl);
			menuItemManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new MenuItemManagementView());
			});
			
			userManagementLbl = new Label("User Management");
			userManagementMenu = new Menu("", userManagementLbl);
			userManagementLbl.setOnMouseClicked((event -> {
				Main.getMainPane().setCenter(new UserManagementView());
			}));;
			
			this.getMenus().addAll(menuItemManagementMenu, userManagementMenu, logoutMenu);
		}

		else if(UserController.getCurrentUser().getUserRole().equals("Cashier") || UserController.getCurrentUser().getUserRole().equals("Chef") || UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			orderManagementLbl = new Label("Order Management");
			orderManagementMenu = new Menu("", orderManagementLbl);
			orderManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new OrderManagementView());
			});
			
			if(UserController.getCurrentUser().getUserRole().equals("Cashier")) {
				receiptManagementLbl = new Label("Receipt Management");
				receiptManagementMenu = new Menu("", receiptManagementLbl);
				receiptManagementLbl.setOnMouseClicked(e ->{
					Main.getMainPane().setCenter(new CashierReceiptManagementView());
				});
				this.getMenus().addAll(orderManagementMenu, receiptManagementMenu, logoutMenu);
			}else {
				this.getMenus().addAll(orderManagementMenu, logoutMenu);
			}
		}
		
		
	}

}
