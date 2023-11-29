package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Connect;

public class MenuItem {
	private Integer menuItemId;
	private String menuItemName;
	private String menuItemDescription;
	private Double menuItemPrice;
	
	public MenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
		super();
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		this.menuItemDescription = menuItemDescription;
		this.menuItemPrice = menuItemPrice;
	}

	public Integer getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Integer menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getMenuItemDescription() {
		return menuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}

	public Double getMenuItemPrice() {
		return menuItemPrice;
	}

	public void setMenuItemPrice(Double menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}
	
	// CRUD
	public static String createMenuItem(String menuItemName, String menuItemDescription, Double menuItemPrice) {		
		String query = String.format("INSERT INTO `menuItem` (menuItemName, menuItemDescription, menuItemPrice) VALUES ('%s', '%s', '%f')", menuItemName, menuItemDescription, menuItemPrice);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static String updateMenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
		String query = String.format("UPDATE `menuItem` SET menuItemName = '%s', menuItemDescription = '%s', menuItemPrice = '%f' "
				+ "WHERE menuItemId = '%d'", menuItemName, menuItemDescription, menuItemPrice, menuItemId);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static String deleteMenuItem(Integer menuItemId) {
		String query = String.format("DELETE FROM `menuItem` WHERE menuItemId = '%d'", menuItemId);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static model.MenuItem getMenuItemByID(Integer menuItemId){		
		String query = String.format("SELECT * FROM `menuItem` WHERE menuItemId = '%d'", menuItemId);
		ResultSet res = Connect.getConnection().executeQuery(query);
		
		try {
			res.next();
			String menuItemName = res.getString(2);
			String menuItemDescription = res.getString(3);
			Double menuItemPrice = res.getDouble(4);
			return new MenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice);
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Vector<model.MenuItem> getAllMenuItems(){
		String query = "SELECT * FROM `menuItem`";
		ResultSet res = Connect.getConnection().executeQuery(query);
		Vector<model.MenuItem> menuItems = new Vector<model.MenuItem>();
		
		try {
			while(res.next()) {
				Integer menuItemId = res.getInt(1);
				String menuItemName = res.getString(2);
				String menuItemDescription = res.getString(3);
				Double menuItemPrice = res.getDouble(4);
				
				menuItems.add(new MenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menuItems;
	}
	
	

}
