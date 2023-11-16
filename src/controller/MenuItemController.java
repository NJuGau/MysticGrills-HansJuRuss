package controller;

import java.util.Vector;

public class MenuItemController {
	
	// CRUD Method
	public static String createMenuItem(String menuItemName, String menuItemDescription, String menuItemPrice) {
		String nameValidation = validateMenuItemName(menuItemName);
		if(nameValidation != null) {
			return nameValidation;
		}
		
		String descValidation = validateMenuItemDescription(menuItemDescription);
		if(descValidation != null) {
			return descValidation;
		}
		
		String priceValidation = validateMenuItemPrice(menuItemPrice);
		if(priceValidation != null) {
			return priceValidation;
		}
		
		return model.MenuItem.createMenuItem(menuItemName, menuItemDescription, Double.parseDouble(menuItemPrice));
	}
	
	public static String updateMenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, String menuItemPrice) {
		String nameValidation = validateMenuItemName(menuItemName);
		if(nameValidation != null) {
			return nameValidation;
		}
		
		String descValidation = validateMenuItemDescription(menuItemDescription);
		if(descValidation != null) {
			return descValidation;
		}
		
		String priceValidation = validateMenuItemPrice(menuItemPrice);
		if(priceValidation != null) {
			return priceValidation;
		}	
		return model.MenuItem.updateMenuItem(menuItemId, menuItemName, menuItemDescription, Double.parseDouble(menuItemPrice));
	}
	
	public static String deleteMenuItem(Integer menuItemId) {
		return model.MenuItem.deleteMenuItem(menuItemId);
	}
	
	public static model.MenuItem getMenuItemByID(Integer menuItemId){
		return model.MenuItem.getMenuItemByID(menuItemId);
	}
	
	public static Vector<model.MenuItem> getAllMenuItems(){
		return model.MenuItem.getAllMenuItems();
	}
	
	// validation
	private static String validateMenuItemName(String input) {
		// TODO: validate input if it's unique
		if(input.isEmpty()) {
			return "Name must not empty";
		}
		
		return null;
	}
	
	private static String validateMenuItemDescription(String input) {
		if(input.length() <= 10) {
			return "Description must be more than 10 character(s)";
		}
		
		return null;
	}
	
	private static String validateMenuItemPrice(String i) {
		if(i.isEmpty()) {
			return "Price must not be empty";
		}
		
		if(Double.parseDouble(i) < 2.5) {
			return "Price must be a number that is greater than or equal to (>=) 2.5";
		}
		
		return null;
	}

}
