package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import controller.OrderController;
import controller.ReceiptController;
import database.Connect;

public class User {
	private Integer userId;
	private String userRole;
	private String userName;
	private String userEmail;
	private String userPassword;
	public User(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	//DELETE method
	public static String deleteUser(Integer userId) {
		String query = "DELETE FROM `user` WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1, userId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		
		//menghapus order yang memiliki user bersangkutan
		Vector<Order> orders = OrderController.getOrdersByCustomerId(userId);
		for(Order o: orders) {
			String deleteOrderItemWithMenuItemIdQuery = "DELETE FROM `orderItem` WHERE orderId = ?";
			PreparedStatement ps2 = Connect.getConnection().prepareStatement(deleteOrderItemWithMenuItemIdQuery);
			try {
				ps2.setInt(1, o.getOrderId());
				Connect.getConnection().executeUpdate(ps2);
			} catch (SQLException e) {
				return "Query failed";
			}
			OrderController.deleteOrder(o.getOrderId());
			ReceiptController.deleteReceipt(o.getOrderId());
		}
		return null;
	}
	
	//SELECT by user Id method
	public static User getUserById(Integer userId) {
		String query = "SELECT * FROM `user` WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		User u = null;
		try {
			ps.setInt(1, userId);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			res.next();
			int id = res.getInt("userId");
			String role = res.getString("userRole");
			String name = res.getString("userName");
			String email = res.getString("userEmail");
			String password = res.getString("userPassword");
			u = new User(id, role, name, email, password);
		} catch (SQLException e) {
			return null;
		}
		return u;
	}
	
	//CREATE method
	public static String createUser(String userRole, String userName, String userEmail, String userPassword) {
		String query = "INSERT INTO `user` (userRole, userName, userEmail, userPassword) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, userRole);
			ps.setString(2, userName);
			ps.setString(3, userEmail);
			ps.setString(4, userPassword);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		return null;
	}
	
	//UPDATE method
	public static String updateUser(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		String query = "UPDATE `user` SET userRole = ?, userName = ?, userEmail = ?, userPassword = ? WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		
		try {
			ps.setString(1, userRole);
			ps.setString(2, userName);
			ps.setString(3, userEmail);
			ps.setString(4, userPassword);
			ps.setInt(5, userId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e) {
			return "Query failed";
		}
		return null;
	}
	
	//SELECT all method
	public static Vector<User> getAllUsers(){
		Vector<User> userList = new Vector<User>();
		String query = "SELECT * FROM `user`";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
				int id = res.getInt("userId");
				String role = res.getString("userRole");
				String name = res.getString("userName");
				String email = res.getString("userEmail");
				String password = res.getString("userPassword");
				User u = new User(id, role, name, email, password);
				userList.add(u);
			}
		} catch (SQLException e) {
			return null;
		}
		return userList;
	}
	
	//SELECT by email and password method
	public static User authenticateUser(String userEmail, String userPassword) {
		String query = "SELECT * FROM `user` WHERE userEmail = ? AND userPassword = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		User u = null;
		try {
			ps.setString(1, userEmail);
			ps.setString(2, userPassword);
			ResultSet res = Connect.getConnection().executeQuery(ps);
			while(res.next()) {
			int id = res.getInt("userId");
			String role = res.getString("userRole");
			String name = res.getString("userName");
			String email = res.getString("userEmail");
			String password = res.getString("userPassword");
			u = new User(id, role, name, email, password);
			}
		} catch (SQLException e) {
			return null;
		}
		return u;
	}
}
