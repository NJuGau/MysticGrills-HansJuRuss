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
		return User.createUser(userRole, userName, userEmail, userPassword);
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
		return User.updateUser(userId, userRole, userName, userEmail, userPassword); 
	}
	
	public static String deleteUser(Integer userId) {
		return User.deleteUser(userId);
	}
	
	public static User authenticateUser(String userEmail, String userPassword) {
		User user = User.authenticateUser(userEmail, userPassword);
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
		Vector<User> userList = UserController.getAllUsers();
		for(User u: userList) {
			if(u.getUserEmail() == userEmail) {
				return "User email must be unique";
			}
		}
		return null;
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
		if(!userRole.equals("Admin") && !userRole.equals("Chef") && !userRole.equals("Waiter") && !userRole.equals("Cashier") && !userRole.equals("Customer")) {
			return "User role must be Admin, Chef, Waiter, Cashier, or Customer";
		}
		return null;
	}
	
}
