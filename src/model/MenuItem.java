package model;

import java.util.Vector;

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
		//TODO: Add SQL Query to insert to database
		
		return null;
	}
	
	public static String updateMenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
		// TODO: Add SQL Query to update to database
		
		return null;
	}
	
	public static String deleteMenuItem(Integer menuItemId) {
		// TODO: Add SQL Query to delete to database
		return null;
	}
	
	public static model.MenuItem getMenuItemByID(Integer menuItemId){		
		// TODO: Add SQL Query to get data from database
		return null;
	}
	
	public static Vector<model.MenuItem> getAllMenuItems(){
		// TODO: Add SQL Query to get all data from database
		
		return new Vector<model.MenuItem>();
	}
	
	

}
