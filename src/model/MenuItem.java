package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.MenuItemController;
import controller.OrderController;
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
		String query = "INSERT INTO `menuItem` (menuItemName, menuItemDescription, menuItemPrice) VALUES (?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, menuItemName);
			ps.setString(2, menuItemDescription);
			ps.setDouble(3, menuItemPrice);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		return null;
	}
	
	public static String updateMenuItem(Integer menuItemId, String menuItemName, String menuItemDescription, Double menuItemPrice) {
		Vector<Order> orders = OrderController.getAllOrders();
		
		for(Order o: orders) {
			Boolean menuFound = false;
			Integer orderQuantity = null;
			Double oldMenuItemPrice = null;
			Vector<OrderItem> orderItemList =  o.getOrderItems();
			
			for(OrderItem orderItem : orderItemList) {
				if(orderItem.getMenuItem().getMenuItemId() == menuItemId){
					orderQuantity = orderItem.getQuantity();
					oldMenuItemPrice = orderItem.getMenuItem().getMenuItemPrice();
					menuFound = true;
					break;
				}
			}
			
			if(menuFound) {
				Double newOrderTotal = o.getOrderTotal() - (oldMenuItemPrice * orderQuantity) + (menuItemPrice * orderQuantity);
				String updateOrderTotalQuery = "UPDATE `order` SET orderTotal = ? WHERE orderId = ?";
				
				PreparedStatement ps = Connect.getConnection().prepareStatement(updateOrderTotalQuery);
				try {
					ps.setDouble(1, newOrderTotal);
					ps.setInt(2, o.getOrderId());
					Connect.getConnection().executeUpdate(ps);
				} catch (SQLException e) {
					return "Query failed";
				}
			}
		}
		
		String query = "UPDATE `menuItem` SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, menuItemName);
			ps.setString(2, menuItemDescription);
			ps.setDouble(3, menuItemPrice);
			ps.setInt(4, menuItemId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		return null;
	}
	
	public static String deleteMenuItem(Integer menuItemId) {
Vector<Order> orders = OrderController.getAllOrders();
		
		for(Order o: orders) {
			Boolean menuFound = false;
			Integer orderQuantity = null;
			Double oldMenuItemPrice = null;
			Vector<OrderItem> orderItemList =  o.getOrderItems();
			
			for(OrderItem orderItem : orderItemList) {
				if(orderItem.getMenuItem().getMenuItemId() == menuItemId){
					orderQuantity = orderItem.getQuantity();
					oldMenuItemPrice = orderItem.getMenuItem().getMenuItemPrice();
					menuFound = true;
					break;
				}
			}
			
			if(menuFound) {
				Double newOrderTotal = o.getOrderTotal() - (oldMenuItemPrice * orderQuantity);
				String updateOrderTotalQuery = "UPDATE `order` SET orderTotal = ? WHERE orderId = ?";
				
				PreparedStatement ps = Connect.getConnection().prepareStatement(updateOrderTotalQuery);
				try {
					ps.setDouble(1, newOrderTotal);
					ps.setInt(2, o.getOrderId());
					Connect.getConnection().executeUpdate(ps);
				} catch (SQLException e) {
					return "Query failed";
				}
			}
		}
		
		String deleteMenuItemQuery = "DELETE FROM `menuItem` WHERE menuItemId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(deleteMenuItemQuery);
		try {
			ps.setInt(1, menuItemId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		
		String deleteOrderItemWithMenuItemIdQuery = "DELETE FROM `orderItem` WHERE menuItemId = ?";
		PreparedStatement ps2 = Connect.getConnection().prepareStatement(deleteOrderItemWithMenuItemIdQuery);
		try {
			ps2.setInt(1, menuItemId);
			Connect.getConnection().executeUpdate(ps2);
		} catch (SQLException e) {
			return "Query failed";
		}
		
		return null;
	}
	
	public static model.MenuItem getMenuItemByID(Integer menuItemId){		
		String query = "SELECT * FROM `menuItem` WHERE menuItemId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		
		try {
			ps.setInt(1, menuItemId);
			ResultSet res = Connect.getConnection().executeQuery(ps);
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
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		Vector<model.MenuItem> menuItems = new Vector<model.MenuItem>();
		
		try {
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
				Integer menuItemId = res.getInt(1);
				String menuItemName = res.getString(2);
				String menuItemDescription = res.getString(3);
				Double menuItemPrice = res.getDouble(4);
				
				menuItems.add(new MenuItem(menuItemId, menuItemName, menuItemDescription, menuItemPrice));
			}
		} catch (SQLException e) {
			return null;
		}
		
		return menuItems;
	}
	
	

}
