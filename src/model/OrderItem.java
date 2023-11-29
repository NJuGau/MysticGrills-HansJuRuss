package model;

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
		String query = String.format("INSERT INTO `orderItem` (orderId, menuItemId, quantity) VALUES ('%d', '%d', '%s')", 
				orderId, menuItem.getMenuItemId(), quantity);
		Connect.getConnection().executeUpdate(query);
		
//		// Don't forget to update orderTotal in order in database
//		String updateOrderTotalQuery = String.format("UPDATE `order` SET orderTotal = orderTotal + 1"
//				+ "WHERE orderId = '%d'", orderId);
//		Connect.getConnection().executeUpdate(updateOrderTotalQuery);
		
		return null;
	}
	
	public static String updateOrderItem(Integer orderId, model.MenuItem menuItem, Integer quantity) {
		String query = String.format("UPDATE `orderItem` SET menuItemId = '%d', quantity = '%d' WHERE orderId = '%d'", menuItem.getMenuItemId(), quantity, orderId);
		Connect.getConnection().executeUpdate(query);
		
		return null;
	}
	
	public static String deleteOrderItem(Integer orderId) {
		String query = String.format("DELETE FROM `orderItem` WHERE orderId  = '%d'", orderId);
		Connect.getConnection().executeUpdate(query);
		
//		// Don't forget to update orderTotal in order in database
//		String updateOrderTotalQuery = String.format("UPDATE `order` SET orderTotal = orderTotal - 1"
//				+ "WHERE orderId = '%d'", orderId);
//		Connect.getConnection().executeUpdate(updateOrderTotalQuery);
		
		return null;
	}
	
	public static Vector<OrderItem> getAllOrderItemsByOrderId(Integer orderId) {
		String query = String.format("SELECT * FROM `orderItem` WHERE orderId = '%d'", orderId);
		ResultSet res = Connect.getConnection().executeQuery(query);
		Vector<OrderItem> orderList = new Vector<OrderItem>();
		
		try {
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
