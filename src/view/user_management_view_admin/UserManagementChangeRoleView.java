package view.user_management_view_admin;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class UserManagementChangeRoleView extends BorderPane{
	
	private GridPane contentPane;
	private Label titleLbl, idLbl, idContentLbl, nameLbl, nameContentLbl, emailLbl, emailContentLbl, passwordLbl, passwordContentLbl, roleLbl, errorLbl;
	
	private ComboBox<String> roleComboBox;
	
	private Button updateBtn, cancelBtn;
	
	private HBox btnGroup;
	
	public UserManagementChangeRoleView(User user) {
		if(!UserController.getCurrentUser().getUserRole().equals("Admin")) {
			Main.getMainPane().setCenter(new LoginView());
			return;
		}
		contentPane = new GridPane();
		
		titleLbl = new Label("Change Role");
		idLbl = new Label("User ID: ");
		idContentLbl = new Label(user.getUserId().toString());
		nameLbl = new Label("User Name: ");
		nameContentLbl = new Label(user.getUserName());
		emailLbl = new Label("User Email: ");
		emailContentLbl = new Label(user.getUserEmail());
		passwordLbl = new Label("Password: ");
		passwordContentLbl = new Label(user.getUserPassword());
		roleLbl = new Label("Role: ");
		errorLbl = new Label();
		
		roleComboBox = new ComboBox<String>();
		roleComboBox.getItems().addAll("Admin", "Chef", "Waiter", "Cashier", "Customer");
		roleComboBox.setValue(user.getUserRole());
		
		updateBtn = new Button("Update Role");
		cancelBtn = new Button("Cancel");
		
		btnGroup = new HBox();
		
		btnGroup.getChildren().addAll(updateBtn, cancelBtn);
		
		contentPane.add(idLbl, 0, 0);
		contentPane.add(idContentLbl, 1, 0);
		contentPane.add(nameLbl, 0, 1);
		contentPane.add(nameContentLbl, 1, 1);
		contentPane.add(emailLbl, 0, 2);
		contentPane.add(emailContentLbl, 1, 2);
		contentPane.add(passwordLbl, 0, 3);
		contentPane.add(passwordContentLbl, 1, 3);
		contentPane.add(roleLbl, 0, 4);
		contentPane.add(roleComboBox, 1, 4);
		contentPane.add(errorLbl, 0, 5, 2, 1);
		
		idLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		emailLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		passwordLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		roleLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));		
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		HBox.setMargin(updateBtn, new Insets(0, 10, 0, 10));
		HBox.setMargin(cancelBtn, new Insets(0, 10, 0, 10));
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		BorderPane.setMargin(contentPane, new Insets(20, 0, 0, 50));
		contentPane.setVgap(10);
		contentPane.setHgap(30);
		
		BorderPane.setAlignment(btnGroup, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(btnGroup, new Insets(20, 0, 20, 50));
		
		this.setTop(titleLbl);
		this.setCenter(contentPane);
		this.setBottom(btnGroup);
		
		updateBtn.setOnAction(e -> {
			System.out.println(roleComboBox.getValue());
			String err = UserController.updateUser(user.getUserId(), roleComboBox.getValue(), user.getUserName(), user.getUserEmail(), user.getUserPassword());
			if(err == null) {
				Main.getMainPane().setCenter(new UserManagementView());
			}else {
				errorLbl.setText(err);
			}
		});
		
		cancelBtn.setOnAction(e ->{
			Main.getMainPane().setCenter(new UserManagementView());
		});
	}
}
