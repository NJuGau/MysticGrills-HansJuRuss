package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegisterView extends GridPane {
	
	Label titleLbl, userNameLbl, emailLbl, passwordLbl, confirmPasswordLbl; 
	TextField userNameTxt, emailTxt, passwordTxt, confirmPasswordTxt;
	Button submitBtn;
	
	public void initialize() {
		titleLbl = new Label("Register");
		userNameLbl = new Label("Username: ");
		emailLbl = new Label("Email: ");
		passwordLbl = new Label("Password: ");
		confirmPasswordLbl = new Label("Confirm password: ");
		
		userNameTxt = new TextField();
		emailTxt = new TextField();
		passwordTxt = new TextField();
		confirmPasswordTxt = new TextField();
		
		submitBtn = new Button("Register");
		
		this.add(titleLbl, 0, 0, 2, 1);
		this.add(userNameLbl, 0, 1);
		this.add(emailLbl, 0, 2);
		this.add(passwordLbl, 0, 3);
		this.add(confirmPasswordLbl, 0, 4);
		
		this.add(userNameTxt, 1, 1);
		this.add(emailTxt, 1, 2);
		this.add(passwordTxt, 1, 3);
		this.add(confirmPasswordTxt, 1, 4);
		
		this.add(submitBtn, 0, 5, 2, 1);
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(submitBtn, HPos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		
		this.setAlignment(Pos.TOP_CENTER);
	}
	
	
	public RegisterView() {
		initialize();
	}
}
