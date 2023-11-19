package view.user_management_view_admin;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import model.User;
import view.LoginView;

public class UserManagementRemoveView extends BorderPane {
	GridPane contentPane;
	Label titleLbl, idLbl, idContentLbl, nameLbl, nameContentLbl, emailLbl, emailContentLbl, passwordLbl, passwordContentLbl, roleLbl, roleContentLbl, confirmLbl;
	
	Button confirmBtn, declineBtn;
	HBox confirmGroup;
	
	public UserManagementRemoveView(User user) {
		if(!UserController.getCurrentUser().getUserRole().equals("Admin")) {
			Main.getMainPane().setCenter(new LoginView());
			return;
		}
		contentPane = new GridPane();
		
		titleLbl = new Label("Delete User");
		idLbl = new Label("User Id: ");
		idContentLbl = new Label(user.getUserId().toString());
		nameLbl = new Label("User Name: ");
		nameContentLbl = new Label(user.getUserName());
		emailLbl = new Label("User Email: ");
		emailContentLbl = new Label(user.getUserEmail());
		passwordLbl = new Label("User Password: ");
		passwordContentLbl = new Label(user.getUserPassword());
		roleLbl = new Label("User Role: ");
		roleContentLbl = new Label(user.getUserRole());
		
		confirmLbl = new Label("Are you sure that you want to delete this user?");
		
		confirmBtn = new Button("Yes");
		declineBtn = new Button("No");
		
		confirmGroup = new HBox();
		
		confirmGroup.getChildren().addAll(confirmLbl, confirmBtn, declineBtn);
		
		contentPane.add(idLbl, 0, 0);
		contentPane.add(idContentLbl, 1, 0);
		contentPane.add(nameLbl, 0, 1);
		contentPane.add(nameContentLbl, 1, 1);
		contentPane.add(emailLbl, 0, 2);
		contentPane.add(emailContentLbl, 1, 2);
		contentPane.add(passwordLbl, 0, 3);
		contentPane.add(passwordContentLbl, 1, 3);
		contentPane.add(roleLbl, 0, 4);
		contentPane.add(roleContentLbl, 1, 4);
		
		idLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		emailLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		passwordLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		roleLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));		
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		HBox.setMargin(confirmBtn, new Insets(0, 10, 0, 10));
		HBox.setMargin(declineBtn, new Insets(0, 10, 0, 10));
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		BorderPane.setMargin(contentPane, new Insets(20, 0, 0, 50));
		contentPane.setVgap(10);
		contentPane.setHgap(30);
		
		BorderPane.setAlignment(confirmGroup, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(confirmGroup, new Insets(20, 0, 20, 50));
		
		this.setTop(titleLbl);
		this.setCenter(contentPane);
		this.setBottom(confirmGroup);
		
		confirmBtn.setOnAction(e -> {
			UserController.deleteUser(user.getUserId());
			Main.getMainPane().setCenter(new UserManagementView());
		});
		
		declineBtn.setOnAction(e -> {
			Main.getMainPane().setCenter(new UserManagementView());
		});
	}
}
