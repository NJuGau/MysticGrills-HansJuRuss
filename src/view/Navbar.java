package view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import main.Main;
import view.MenuItemViewAdmin.MenuItemManagementView;
import view.MenuItemViewCustomer.MenuCustomerView;

public class Navbar extends MenuBar{
	Label menuItemManagementLbl, homeLbl;
	Menu menuItemManagementMenu, homeMenu;

	public Navbar() {
		
		homeLbl = new Label("Menu");
		homeMenu = new Menu("", homeLbl);
		homeLbl.setOnMouseClicked(event -> {
			Main.getMainPane().setCenter(new MenuCustomerView());
		});
		
		this.getMenus().addAll(homeMenu);
		
		if(Main.getCurrentUser().getUserRole() == "admin") {
			menuItemManagementLbl = new Label("Menu Item Management");
			menuItemManagementMenu = new Menu("", menuItemManagementLbl);
			menuItemManagementLbl.setOnMouseClicked(event -> {
				Main.getMainPane().setCenter(new MenuItemManagementView());
			});
			
			this.getMenus().addAll(menuItemManagementMenu);
		}
	}

}
