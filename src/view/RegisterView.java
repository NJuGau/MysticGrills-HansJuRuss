package view;

import controller.UserController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;

public class RegisterView extends BorderPane {
	
	GridPane contentPane;
	Label titleLbl, userNameLbl, emailLbl, passwordLbl, confirmPasswordLbl, linkLbl, errorLbl; 
	TextField userNameTxt, emailTxt, passwordTxt, confirmPasswordTxt;
	Button submitBtn;
	Hyperlink gotoLoginLink;
	
	public void initialize() {
		contentPane = new GridPane();
		
		titleLbl = new Label("Register");
		userNameLbl = new Label("Username: ");
		emailLbl = new Label("Email: ");
		passwordLbl = new Label("Password: ");
		confirmPasswordLbl = new Label("Confirm password: ");
		linkLbl = new Label("Got an Account? ");
		errorLbl = new Label();
		
		userNameTxt = new TextField();
		emailTxt = new TextField();
		passwordTxt = new TextField();
		confirmPasswordTxt = new TextField();
		
		submitBtn = new Button("Register");
		
		gotoLoginLink = new Hyperlink("Login Now");
		
		contentPane.add(userNameLbl, 0, 1);
		contentPane.add(emailLbl, 0, 2);
		contentPane.add(passwordLbl, 0, 3);
		contentPane.add(confirmPasswordLbl, 0, 4);
		
		contentPane.add(userNameTxt, 1, 1);
		contentPane.add(emailTxt, 1, 2);
		contentPane.add(passwordTxt, 1, 3);
		contentPane.add(confirmPasswordTxt, 1, 4);
		contentPane.add(errorLbl, 0, 5, 2, 1);
		
		contentPane.add(submitBtn, 0, 6, 2, 1);
		
		contentPane.add(linkLbl, 0, 7);
		contentPane.add(gotoLoginLink, 1, 7);
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(submitBtn, HPos.CENTER);
		contentPane.setVgap(10);
		contentPane.setHgap(10);
		
		contentPane.setAlignment(Pos.TOP_CENTER);
		
		gotoLoginLink.setOnAction(e ->{
			Main.getMainPane().setCenter(new LoginView());
		});
		
		submitBtn.setOnAction(e ->{
			String validation = UserController.createUser("Customer", userNameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), confirmPasswordTxt.getText());
			if(validation != null) {
				errorLbl.setText(validation);
			}else {
				Main.getMainPane().setCenter(new LoginView());
			}
		});
		
		this.setTop(titleLbl);
		this.setCenter(contentPane);
	}
	
	
	public RegisterView() {
		initialize();
	}
}
