package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.MenuItemController;
import database.Connect;

public class OrderItem {
	private Integer orderId;
	private model.MenuItem menuItem;
	private Integer quantity;
	
	public OrderItem(Integer orderId, model.MenuItem menuItem, Integer quantity) {
		super();
		this.orderId = orderId;
		this.menuItem = menuItem;
		this.quantity = quantity;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public model.MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(model.MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	// CRUD
	public static String createOrderItem(Integer orderId, model.MenuItem menuItem, Integer quantity) {
		String query = "INSERT INTO `orderItem` (orderId, menuItemId, quantity) VALUES (?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, orderId);
			ps.setInt(2, menuItem.getMenuItemId());
			ps.setInt(3, quantity);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		
//		// Don't forget to update orderTotal in order in database
//		String updateOrderTotalQuery = String.format("UPDATE `order` SET orderTotal = orderTotal + 1"
//				+ "WHERE orderId = '%d'", orderId);
//		Connect.getConnection().executeUpdate(updateOrderTotalQuery);
		
		return null;
	}
	
	public static String updateOrderItem(Integer orderId, model.MenuItem menuItem, Integer quantity) {
		String query = "UPDATE `orderItem` SET quantity = ? WHERE orderId = ? AND menuItemId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, quantity);
			ps.setInt(2, orderId);
			ps.setInt(3, menuItem.getMenuItemId());
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		//TODO: Don't forget to update orderTotal in order in database
		return null;
	}
	
	public static String deleteOrderItem(Integer orderId, Integer menuItemId) {
		String query = "DELETE FROM `orderItem` WHERE orderId  = ? AND menuItemId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, orderId);
			ps.setInt(2, menuItemId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		
//		// Don't forget to update orderTotal in order in database
//		String updateOrderTotalQuery = String.format("UPDATE `order` SET orderTotal = orderTotal - 1"
//				+ "WHERE orderId = '%d'", orderId);
//		Connect.getConnection().executeUpdate(updateOrderTotalQuery);
		
		return null;
	}
	
	public static Vector<OrderItem> getAllOrderItemsByOrderId(Integer orderId) {
		String query = "SELECT * FROM `orderItem` WHERE orderId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		Vector<OrderItem> orderList = new Vector<OrderItem>();
		
		try {
			ps.setInt(1, orderId);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
				model.MenuItem menuItem = MenuItemController.getMenuItemByID(res.getInt(2));
				Integer quantity = res.getInt(3);
				
				orderList.add(new OrderItem(orderId, menuItem, quantity));
			}
			return orderList;
			
		} catch (SQLException e) {
			return null;
		}
	}
	

}
