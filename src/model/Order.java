package model;

import java.sql.Date;
import java.util.Vector;

public class Order {
	private Integer orderId;
	private User orderUser;
	private Vector<OrderItem> orderItems;
	private String orderStatus;
	private Date orderDate;
	// TODO Missing Order Total but i don't know what is that for
	
	public Order(Integer orderId, User orderUser, Vector<OrderItem> orderItems, String orderStatus, Date orderDate) {
		super();
		this.orderId = orderId;
		this.orderUser = orderUser;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}
	
	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public User getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(User orderUser) {
		this.orderUser = orderUser;
	}

	public Vector<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Vector<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	// CRUD Method
	public static String createOrder(User orderUser, Vector<OrderItem> orderItems, Date orderDate) {
		// TODO Add SQL Query to create order
		
		return null;
	}
	
	public static String updateOrder(Integer orderId, Vector<OrderItem> orderItems, Date orderDate) {
		// TODO Add SQL Query to update order
		
		return null;
	}
	
	public static String deleteOrder(Integer orderId) {
		// TODO Add SQL Query to delete order
		
		return null;
	}
	
	public static Order getOrdersByCustomerId(Integer customerId) {
		// TODO Add SQL Query to get order by customer id
		
		return null;
	}
	
	public static Vector<Order> getAllOrders() {
		// TODO Add SQL Query to get all order
		
		return null;
	}
	
	public static Order getOrderByOrderId(Integer orderId) {
		// TODO Add SQL Query to get order by order id
		
		return null;
	}
	
	

	

}
