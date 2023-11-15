package controller;

import java.util.Vector;

public class MenuItemController {
	
	// CRUD Method
	public static String createMenuItem(String menuItemName, String menuItemDescription, Double menuItemPrice) {
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
		
		return model.MenuItem.createMenuItem(menuItemName, menuItemDescription, menuItemPrice);
	}
	
	public static String updateMenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
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
		return model.MenuItem.updateMenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice);
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
			return "Must be more than 10 character(s)";
		}
		
		return null;
	}
	
	private static String validateMenuItemPrice(Double i) {
		if(i < 2.5) {
			return "Must be a number that is greater than or equal to (>=) 2.5";
		}
		
		return null;
	}

}
