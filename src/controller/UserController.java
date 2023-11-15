package controller;

import java.util.Vector;

import model.User;

public class UserController {
	private static User currentUser;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		UserController.currentUser = currentUser;
	}
	
	public static String createUser(String userRole, String userName, String userEmail, String userPassword, String confirmPassword) {
		//Assumption: confirm password divalidasi di controller, jadi confirmPassword dimasukkan sebagai param di createUser
		String nameValidation = validateUserName(userName);
		String emailValidation = validateUserEmail(userEmail);
		String passwordValidation = validateUserPassword(userPassword);
		String confirmPasswordValidation = validateConfirmPassword(userPassword, confirmPassword);
		String roleValidation = validateUserRole(userRole);
		
		if(nameValidation != null) {
			return nameValidation;
		}else if(emailValidation != null) {
			return emailValidation;
		}else if(passwordValidation != null) {
			return passwordValidation;
		}else if(confirmPasswordValidation != null) {
			return confirmPasswordValidation;
		}
		Boolean isCreated = User.createUser(userRole, userName, userEmail, userPassword);
		if(isCreated) {
			return null;
		}else {
			return "Error in Database: User failed to be created"; 
		}
	}
	
	public static String updateUser(Integer userId, String userRole, String userName, String userEmail, String userPassword) {
		String nameValidation = validateUserName(userName);
		String emailValidation = validateUserEmail(userEmail);
		String passwordValidation = validateUserPassword(userPassword);	
		String roleValidation = validateUserRole(userRole); 
		
		if(nameValidation != null) {
			return nameValidation;
		}else if(emailValidation != null) {
			return emailValidation;
		}else if(passwordValidation != null) {
			return passwordValidation;
		}else if(roleValidation != null) {
			return roleValidation;
		}
		Boolean isUpdated = User.updateUser(userId, userRole, userName, userEmail, userPassword); 
		if(isUpdated) {
			return null;
		}else {
			return "Error in Database: User failed to be updated";
		}
	}
	
	public static String deleteUser(Integer userId) {
		Boolean isDeleted = User.deleteUser(userId);
		if(isDeleted) {
			return null;
		}else {
			return "User failed to be deleted";
		}
	}
	
	public static User authenticateUser(String userEmail, String userPassword) {
		String loginValidation = validateLogin(userEmail, userPassword);
		if(loginValidation != null) {
			return null; //TODO: Somehow return loginValidation to view
		}
		User user = authenticateUser(userEmail, userPassword);
		return user;
	}
	
	public static Vector<User> getAllUsers(){
		return User.getAllUsers();
	}
	
	public static User getUserById(Integer userId) {
		return User.getUserById(userId);
	}
	
	//validation
	private static String validateUserName(String userName) {
		if(userName.isEmpty()) {
			return "User name cannot be empty";
		}
		return null;
	}
	
	private static String validateUserEmail(String userEmail) {
		if(userEmail.isEmpty()) {
			return "User email cannot be empty";
		}
		//TODO: insert unique algo
		return "";
	}
	
	private static String validateUserPassword(String userPassword) {
		if(userPassword.isEmpty()) {
			return "User password cannot be empty";
		}else if(userPassword.length() < 6) {
			return "User password must be at least 6 characters long";
		}
		return null;
	}
	
	private static String validateConfirmPassword(String userPassword, String confirmPassword) {
		if(confirmPassword.isEmpty()) {
			return "Password confirmation cannot be empty";
		}else if(!confirmPassword.equals(userPassword)) {
			return "password confirmation must be the same with password";
		}
		return null;
	}
	
	private static String validateUserRole(String userRole) {
		if(!(userRole.equals("Admin") && userRole.equals("Chef") && userRole.equals("Waiter") && userRole.equals("Cashier") && userRole.equals("Customer"))) {
			return "User role must be Admin, Chef, Waiter, Cashier, or Customer";
		}
		return null;
	}
	
	private static String validateLogin(String userEmail, String userPassword) {
		if(userEmail.isEmpty()) {
			return "User email must be filled";
		}
		else if(userPassword.isEmpty()) {
			return "User password must be filled";
		}else if(authenticateUser(userEmail, userPassword) == null) {
			return "Credential invalid";
		}
		return null;
	}
}
