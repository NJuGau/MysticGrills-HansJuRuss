package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.Main;

public class LoginView extends GridPane {
	
	Label emailLbl, passwordLbl, titleLbl, linkLbl;
	TextField emailTxt, passwordTxt;
	Hyperlink gotoRegisterLink;
	
	Button submitBtn;
	
	public void initialize() {
		titleLbl = new Label("Login");
		emailLbl = new Label("Email");
		passwordLbl = new Label("Password");
		linkLbl = new Label("Haven't got an account? ");
		
		emailTxt = new TextField();
		passwordTxt = new TextField();
		
		submitBtn = new Button("Login");
		
		gotoRegisterLink = new Hyperlink("Register here");
		
		this.add(titleLbl, 0, 0, 3, 1);
		this.add(emailLbl, 0, 1);
		this.add(passwordLbl, 0, 2);
		
		this.add(emailTxt, 1, 1);
		this.add(passwordTxt, 1, 2);
		
		this.add(submitBtn, 0, 3, 3, 1);
		
		this.add(linkLbl, 0, 4);
		this.add(gotoRegisterLink, 1, 4);
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(submitBtn, HPos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		
		this.setAlignment(Pos.TOP_CENTER);
	}
	
	public LoginView() {
		initialize();
		gotoRegisterLink.setOnMouseClicked(e -> {
			Main.getMainPane().setCenter(new RegisterView());
		});
	}
}
