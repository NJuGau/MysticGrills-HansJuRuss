package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public static String deleteUser(Integer userId) {
		String query = String.format("DELETE FROM `user` WHERE userId = %d", userId);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static User getUserById(Integer userId) {
		String query = String.format("SELECT * FROM `user` WHERE userId = %d", userId);
		ResultSet res = Connect.getConnection().executeQuery(query);
		User u = null;
		try {
			//TODO: Test first
			res.next();
			int id = res.getInt("userId");
			String role = res.getString("userRole");
			String name = res.getString("userName");
			String email = res.getString("userEmail");
			String password = res.getString("userPassword");
			u = new User(id, role, name, email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public static String createUser(String userRole, String userName, String userEmail, String userPassword) {
		String query = String.format("INSERT INTO `user` (userRole, userName, userEmail, userPassword) VALUES ('%s', '%s', '%s', '%s')", userRole, userName, userEmail, userPassword);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static String updateUser(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		String query = String.format("UPDATE `user` SET userRole = '%s', userName = '%s', userEmail = '%s', userPassword = '%s' WHERE userId = %d", userRole, userName, userEmail, userPassword, userId);
		Connect.getConnection().executeUpdate(query);
		return null;
	}
	
	public static Vector<User> getAllUsers(){
		Vector<User> userList = new Vector<User>();
		String query = "SELECT * FROM `user`";
		ResultSet res = Connect.getConnection().executeQuery(query);
		try {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	public static User authenticateUser(String userEmail, String userPassword) {
		String query = String.format("SELECT * FROM `user` WHERE userEmail = '%s' AND userPassword = '%s'", userEmail, userPassword);
		ResultSet res = Connect.getConnection().executeQuery(query);
		try {
			//TODO: Test first
			while(res.next()) {
			int id = res.getInt("userId");
			String role = res.getString("userRole");
			String name = res.getString("userName");
			String email = res.getString("userEmail");
			String password = res.getString("userPassword");
			User u = new User(id, role, name, email, password);
			return u;
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
}
