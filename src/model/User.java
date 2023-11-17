package model;

import java.util.Vector;

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
	
	public static Boolean deleteUser(Integer userId) {
		//TODO: Connect to database
		return true;
	}
	
	public static User getUserById(Integer userId) {
		//TODO: Connect to database
		return null;
	}
	
	public static Boolean createUser(String userRole, String userName, String userEmail, String userPassword) {
		//TODO: Connect to database
		return true;
	}
	
	public static Boolean updateUser(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		//TODO: Connect to database
		return true;
	}
	
	public static Vector<User> getAllUsers(){
		//TODO: Connect to database
		return new Vector<User>();
	}
	
	public static User authenticateUser(String userEmail, String userPassword) {
		//TODO: Connect to database
		return null;
	}
}
