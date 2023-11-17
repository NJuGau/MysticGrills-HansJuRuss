package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

public class LoginView extends BorderPane {
	
	GridPane contentPane;
	Label emailLbl, passwordLbl, titleLbl, linkLbl;
	TextField emailTxt, passwordTxt;
	Hyperlink gotoRegisterLink;
	
	Button submitBtn;
	
	public void initialize() {
		contentPane = new GridPane();
		
		titleLbl = new Label("Login");
		emailLbl = new Label("Email");
		passwordLbl = new Label("Password");
		linkLbl = new Label("Haven't got an account? ");
		
		emailTxt = new TextField();
		passwordTxt = new TextField();
		
		submitBtn = new Button("Login");
		
		gotoRegisterLink = new Hyperlink("Register here");
		
		contentPane.add(titleLbl, 0, 0, 3, 1);
		contentPane.add(emailLbl, 0, 1);
		contentPane.add(passwordLbl, 0, 2);
		
		contentPane.add(emailTxt, 1, 1);
		contentPane.add(passwordTxt, 1, 2);
		
		contentPane.add(submitBtn, 0, 3, 3, 1);
		
		contentPane.add(linkLbl, 0, 4);
		contentPane.add(gotoRegisterLink, 1, 4);
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(submitBtn, HPos.CENTER);
		contentPane.setVgap(10);
		contentPane.setHgap(10);
		
		contentPane.setAlignment(Pos.TOP_CENTER);
		
		this.setTop(titleLbl);
		this.setCenter(contentPane);
	}
	
	public LoginView() {
		initialize();
		gotoRegisterLink.setOnMouseClicked(e -> {
			Main.getMainPane().setCenter(new RegisterView());
		});
	}
}
