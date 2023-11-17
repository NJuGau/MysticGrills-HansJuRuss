package view.user_management_view_admin;

import java.util.Vector;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.User;
import view.LoginView;

public class UserManagementView extends BorderPane {
	
	private Label titleLbl;
	private TableView<User> table;
	private ObservableList<User> userData;
	private Vector<User> userList;
	
	public UserManagementView() {
		if(Main.getCurrentUser().getUserRole() != "admin") {
			Main.getMainPane().setCenter(new LoginView());
			return;
		}
		
		titleLbl = new Label("User Management");
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		initTable();
		
		this.setTop(titleLbl);
		this.setCenter(table);
	}
	
	@SuppressWarnings("unchecked")
	private void initTable() {
		table = new TableView<User>();
		TableColumn<User, Integer> idColumn = new TableColumn<User, Integer>("User ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("userId")); 
		TableColumn<User, String> nameColumn = new TableColumn<User, String>("User Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<User, String> emailColumn = new TableColumn<User, String>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
		TableColumn<User, String> passwordColumn = new TableColumn<User, String>("Password");
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
		TableColumn<User, String> roleColumn = new TableColumn<User, String>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		
		TableColumn<User, String> removeAction = new TableColumn<User, String>("Remove");
		removeAction.setCellFactory(new Callback<TableColumn<User,String>, TableCell<User,String>>() {
			
			@Override
			public TableCell<User, String> call(TableColumn<User, String> arg0) {
				TableCell<User, String> cell = new TableCell<>() {
					Button removeBtn = new Button("Remove");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						if(empty) {
							setGraphic(null);
							setText(null);
						}else {
							removeBtn.setOnAction(e -> {
								User user = getTableView().getItems().get(getIndex());
								Main.getMainPane().setCenter(new UserManagementRemoveView(user));
							});
							setGraphic(removeBtn);
							setText(null);
						}
					};
				};
				return cell;
			}
		});
		
		TableColumn<User, String> changeRoleAction = new TableColumn<User, String>("Change Role");
		changeRoleAction.setCellFactory(new Callback<TableColumn<User,String>, TableCell<User,String>>() {
			
			@Override
			public TableCell<User, String> call(TableColumn<User, String> arg0) {
				TableCell<User, String> cell = new TableCell<>() {
					Button changeRoleBtn = new Button("Change Role");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						if(empty) {
							setGraphic(null);
							setText(null);
						}else {
							changeRoleBtn.setOnAction(e -> {
								User user = getTableView().getItems().get(getIndex());
								Main.getMainPane().setCenter(new UserManagementChangeRoleView(user));
							});
							setGraphic(changeRoleBtn);
							setText(null);
						}
					};
				};
				return cell;
			}
		});
		
		table.getColumns().addAll(idColumn, nameColumn, emailColumn, passwordColumn, roleColumn, removeAction, changeRoleAction);
		
		userList = UserController.getAllUsers();
		//Temporary only a.k.a. dummy data
		userList.add(new User(1, "Admin", "Budi", "Budi@sinub.ac.id", "bUd1"));
		userList.add(new User(2, "Chef", "Jono", "Jono@sinub.ac.id", "joN0"));
		userList.add(new User(3, "Customer", "Rita", "Rita@sinub.ac.id", "r1tA"));
		
		userData = FXCollections.observableArrayList(userList);
		table.setItems(userData);		
	}
}
