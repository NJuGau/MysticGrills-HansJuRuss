package view.menu_item_view_customer;

import java.util.Vector;

import controller.MenuItemController;
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
import model.MenuItem;
import view.order_management.OrderManagementView;

public class MenuCustomerView extends BorderPane {
	//Halaman menu item yang dapat diakses hanya oleh customer. Disini, customer bisa memilih menu item untuk ditambahkan ke order. 
	private Label titleLbl;

	public MenuCustomerView() {
		if(!UserController.getCurrentUser().getUserRole().equals("Customer") && !UserController.getCurrentUser().getUserRole().equals("Chef") && !UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			// We don't want to chef or Waiters or Cashier make a new order so we disable Menu
			Main.getMainPane().setCenter(new OrderManagementView());
		}
		else { 
			init();
			initTable();
		}
	}
	
	private void init() {
		titleLbl = new Label("Menu");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
	}
	
	TableView<model.MenuItem> table;
	ObservableList<model.MenuItem> menuItemData;
	Vector<model.MenuItem> menuItemList;
	@SuppressWarnings("unchecked")
	public void initTable() {
		table = new TableView<model.MenuItem>();
		TableColumn<model.MenuItem, Integer> idColumn = new TableColumn<>("ID"); // Header
		idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> nameColumn = new TableColumn<>("Name"); 
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName")); 
		
		TableColumn<model.MenuItem, String> descColumn = new TableColumn<>("Description"); 
		descColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription")); 
		
		TableColumn<model.MenuItem, String> priceColumn = new TableColumn<>("Price"); 
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice")); 
		
		TableColumn<model.MenuItem, String> actionColumn = new TableColumn<>("Action"); 
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));
		actionColumn.setCellFactory(new Callback<TableColumn<MenuItem,String>, TableCell<MenuItem,String>>() {
			
			@Override
			public TableCell<MenuItem, String> call(TableColumn<MenuItem, String> arg0) {
				TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>(){
					Button actionBtn = new Button("Select");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	actionBtn.setOnAction(event -> {
                                MenuItem menuItem = getTableView().getItems().get(getIndex());
                                Main.getMainPane().setCenter(new MenuDetailsCustomerView(menuItem));
                            });
                            setGraphic(actionBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(idColumn ,nameColumn , descColumn, priceColumn, actionColumn);
		
		// set data source untuk table
		menuItemList = MenuItemController.getAllMenuItems();
		
		// observable
		menuItemData = FXCollections.observableArrayList(menuItemList);
		
		// masukin data ke table
		table.setItems(menuItemData);
		
		this.setCenter(table);
	}
}
