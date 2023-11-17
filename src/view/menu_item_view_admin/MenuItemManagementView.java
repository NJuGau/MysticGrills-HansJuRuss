package view.menu_item_view_admin;

import java.util.Vector;

import controller.MenuItemController;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.MenuItem;
import view.menu_item_view_customer.MenuCustomerView;

public class MenuItemManagementView extends BorderPane {
	
	private Label titleLbl;
	private Button addMenuItemBtn;
	private HBox actions;

	public MenuItemManagementView() {
		if(Main.getCurrentUser().getUserRole() != "admin") {
			Main.getMainPane().setCenter(new MenuCustomerView());
			return;
		}
		
		// Show Title
		titleLbl = new Label("Menu Item Management");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		// Show Table
		initTable();
		
		// Add New Menu Items
		actions = new HBox();
		addMenuItemBtn = new Button("Add Menu Items");
		addMenuItemBtn.setOnAction(event -> {
			navigateToAddMenu();
		});
		actions.getChildren().addAll(addMenuItemBtn);
		HBox.setMargin(addMenuItemBtn, new Insets(0, 10, 0, 10));
		this.setBottom(actions);
		BorderPane.setAlignment(actions, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(actions, new Insets(10, 0, 10, 0));
	}
	
	TableView<model.MenuItem> table;
	ObservableList<model.MenuItem> menuItemData;
	Vector<model.MenuItem> menuItemList;
	
	@SuppressWarnings("unchecked")
	private void initTable() {
		table = new TableView<model.MenuItem>();
		TableColumn<model.MenuItem, Integer> idColumn = new TableColumn<>("ID"); // Header
		idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> nameColumn = new TableColumn<>("Name"); // Header
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> descColumn = new TableColumn<>("Description"); // Header
		descColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> priceColumn = new TableColumn<>("Price"); // Header
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> updateAction = new TableColumn<>("Update"); // Header
		updateAction.setCellFactory(new Callback<TableColumn<MenuItem,String>, TableCell<MenuItem,String>>() {
			
			@Override
			public TableCell<MenuItem, String> call(TableColumn<MenuItem, String> arg0) {
				TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>(){
					Button updateBtn = new Button("Update");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	updateBtn.setOnAction(event -> {
                                MenuItem menuItem = getTableView().getItems().get(getIndex());
                                Main.getMainPane().setCenter(new MenuItemUpdateView(menuItem));     
                            });
                            setGraphic(updateBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		TableColumn<model.MenuItem, String> deleteAction = new TableColumn<>("Delete"); // Header
		deleteAction.setCellFactory(new Callback<TableColumn<MenuItem,String>, TableCell<MenuItem,String>>() {
			
			@Override
			public TableCell<MenuItem, String> call(TableColumn<MenuItem, String> arg0) {
				TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>(){
					Button deleteBtn = new Button("Delete");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	deleteBtn.setOnAction(event -> {
                                MenuItem menuItem = getTableView().getItems().get(getIndex());
                                Main.getMainPane().setCenter(new MenuItemDeleteView(menuItem));
                            });
                            setGraphic(deleteBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(idColumn ,nameColumn , descColumn, priceColumn, updateAction, deleteAction);
		
		// set data source untuk table
		menuItemList = MenuItemController.getAllMenuItems();
		menuItemList.add(new MenuItem(1, "Cola", "Deez Nuts", (double) 50));
		menuItemList.add(new MenuItem(2, "Fanta", "Deez Nuts", (double) 50));
		menuItemList.add(new MenuItem(3, "Coffee", "Deez Nuts", (double) 50));
		
		// observable
		menuItemData = FXCollections.observableArrayList(menuItemList);
		
		// masukin data ke table
		table.setItems(menuItemData);
		
		this.setCenter(table);
	}
	
	private void navigateToAddMenu() {
		Main.getMainPane().setCenter(new MenuItemAddView());
	}
	
}
