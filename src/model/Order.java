package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.OrderController;
import database.Connect;

public class Order {
	private Integer orderId;
	private User orderUser;
	private Vector<OrderItem> orderItems;
	private String orderStatus;
	private Date orderDate;
	private Double orderTotal;
	
	public Order(Integer orderId, User orderUser, Vector<OrderItem> orderItems, String orderStatus, Date orderDate, Double orderTotal) {
		super();
		this.orderId = orderId;
		this.orderUser = orderUser;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
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

	public Double getOrderTotal() {
		return orderTotal;
	}
	
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	// CRUD Method
	public static Integer createOrder(User orderUser, Vector<OrderItem> orderItems, Date orderDate) {
		String query = "INSERT INTO `order` (userId, orderStatus, orderDate, orderTotal) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, orderUser.getUserId());
			ps.setString(2, "null");
			ps.setDate(3, orderDate);
			ps.setDouble(4, 0.0);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e1) {
			return null;
		}
		
		String lastInsertedIdQuery = "SELECT LAST_INSERT_ID()";
		ps = Connect.getConnection().prepareStatement(lastInsertedIdQuery);
		
		try {
			ResultSet res = Connect.getConnection().executeQuery(ps);
			res.next();
			return res.getInt(1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static String updateOrder(Integer orderId, Vector<OrderItem> orderItems, String orderStatus) {
		String query = "UPDATE `order` SET orderStatus = ?, orderTotal = ? WHERE orderId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, orderStatus);
			ps.setDouble(2, OrderController.calculateCurrentOrderTotal(orderItems));
			ps.setInt(3, orderId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		
		//TODO: update order detail (?)
		return null;
	}
	
	public static String deleteOrder(Integer orderId) {
		String query = "DELETE FROM `order` WHERE orderId  = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, orderId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		return null;
	}
	
	public static Vector<Order> getOrdersByCustomerId(Integer customerId) {
		String query = "SELECT * FROM `order` WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		Vector<Order> orderList = new Vector<Order>();
		
		try {
			ps.setInt(1, customerId);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
				Integer orderId = res.getInt(1);
				User orderUser = User.getUserById(res.getInt(2));
				
				Vector<OrderItem> orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
				String orderStatus = res.getString(3);
				Date orderDate = res.getDate(4);
				Double orderTotal = (double) orderItems.size();
				orderList.add(new Order(orderId, orderUser, orderItems, orderStatus, orderDate, orderTotal));
			}
			return orderList;
			
		} catch (SQLException e) {
			return null;
		}

	}
	
	public static Vector<Order> getAllOrders() {
		String query = "SELECT * FROM `order`";
		Vector<Order> orderList = new Vector<Order>();
		
		try {
			PreparedStatement ps = Connect.getConnection().prepareStatement(query);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
				Integer orderId = res.getInt(1);
				User orderUser = User.getUserById(res.getInt(2));
				
				Vector<OrderItem> orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
				String orderStatus = res.getString(3);
				Date orderDate = res.getDate(4);
				Double orderTotal = res.getDouble(5);
				orderList.add(new Order(orderId, orderUser, orderItems, orderStatus, orderDate, orderTotal));
			}
			return orderList;
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Order getOrderByOrderId(Integer orderId) {
		String query = "SELECT * FROM `order` WHERE orderId  = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		
		try {
			ps.setInt(1, orderId);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			res.next();
			User orderUser = User.getUserById(res.getInt(2));
			Vector<OrderItem> orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
			String orderStatus = res.getString(3);
			Date orderDate = res.getDate(4);
			Double orderTotal = res.getDouble(5);
			
			return new Order(orderId, orderUser, orderItems, orderStatus, orderDate, orderTotal);
		} catch (SQLException e) {
			return null;
		}
	}
	
	

	

}
