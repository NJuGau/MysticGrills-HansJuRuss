package view.MenuItemView;

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
import model.MenuItem;

public class MenuItemManagementView extends BorderPane {
	
	private Label titleLbl;
	private Button addMenuItemBtn, updateMenuItemBtn, deleteMenuItemBtn;
	private HBox actions;

	public MenuItemManagementView() {
		titleLbl = new Label("Menu Item Management");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		initTable();
		
		actions = new HBox();
		addMenuItemBtn = new Button("Add Menu Items");
		updateMenuItemBtn = new Button("Update Menu Items");
		deleteMenuItemBtn = new Button("Delete Menu Items");
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
	public void initTable() {
		table = new TableView<model.MenuItem>();
		TableColumn<model.MenuItem, Integer> idColumn = new TableColumn<>("ID"); // Header
		idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> nameColumn = new TableColumn<>("Name"); // Header
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> descColumn = new TableColumn<>("Description"); // Header
		descColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> priceColumn = new TableColumn<>("price"); // Header
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice")); // Tipe variable pada model
		
		TableColumn<model.MenuItem, String> updateAction = new TableColumn<>("Action"); // Header
		updateAction.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));
		updateAction.setCellFactory(new Callback<TableColumn<MenuItem,String>, TableCell<MenuItem,String>>() {
			
			@Override
			public TableCell<MenuItem, String> call(TableColumn<MenuItem, String> arg0) {
				TableCell<MenuItem, String> cell = new TableCell<MenuItem, String>(){
					Button updateBtn = updateMenuItemBtn;
					
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
                                System.out.println(menuItem.getMenuItemId() + " " + menuItem.getMenuItemName());
                            });
                            setGraphic(updateBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(idColumn ,nameColumn , descColumn, priceColumn, updateAction);
		
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
	
	public void navigateToUpdateMenuView(Integer id) {
		
	}
	
}
