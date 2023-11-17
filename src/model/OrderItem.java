package model;

import java.util.Vector;

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
		// TODO add SQL Query to add orderItem to Database
		
		return null;
	}
	
	public static String updateOrderItem(Integer orderId, model.MenuItem menuItem, Integer quantity) {
		// TODO add SQL Query to update orderItem to database
		
		return null;
	}
	
	public static String deleteOrderItem(Integer orderId) {
		// TODO add SQL Query to delete orderItem from database
		
		return null;
	}
	
	public static Vector<OrderItem> getAllOrderItemsByOrderId(Integer orderId) {
		// TODO add SQL Query to get all order item by orderId from database
		
		return null;
	}
	

}
