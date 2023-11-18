package view.order_view;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import controller.OrderController;
import controller.OrderItemController;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.MenuItem;
import model.Order;
import model.OrderItem;

public class OrderDetailsView extends BorderPane{
	private Order order;
	
	private Label titleLbl, orderIdLbl, orderIdTxt, userLbl, userTxt, statusLbl, statusTxt,
					dateLbl, dateTxt, totalLbl, totalTxt;
	private GridPane dataPane;
	private VBox middlePane;

	public OrderDetailsView(Order order) {
		this.order = order;
		
		showTitle();
		
		middlePane = new VBox();
		this.setCenter(middlePane);
		showOrderData();
		
		initTable();
	}
	
	private TableView<OrderItem> table;
	private ObservableList<OrderItem> orderItemData;
	private Vector<OrderItem> orderItemList;
	@SuppressWarnings("unchecked")
	public void initTable() {
		table = new TableView<OrderItem>();
		TableColumn<OrderItem, String> nameColumn = new TableColumn<>("Menu Item Name"); // Header
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemName()));
		
		TableColumn<OrderItem, String> descriptionColumn = new TableColumn<>("Menu Item Description"); // Header
		descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemDescription()));
		
		TableColumn<OrderItem, String> priceColumn = new TableColumn<>("Menu Item Price"); // Header
		priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemPrice().toString()));
		
		TableColumn<OrderItem, String> quantityColumn = new TableColumn<>("Menu Item Quantity"); // Header
		quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity().toString()));
		
		TableColumn<OrderItem, String> actionColumn = new TableColumn<>("Action"); // Header
		actionColumn.setCellFactory(new Callback<TableColumn<OrderItem,String>, TableCell<OrderItem,String>>() {
			
			@Override
			public TableCell<OrderItem, String> call(TableColumn<OrderItem, String> arg0) {
				TableCell<OrderItem, String> cell = new TableCell<OrderItem, String>(){
					Button actionBtn = new Button("Update Order");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	actionBtn.setOnAction(event -> {
                                OrderItem orderItem = getTableView().getItems().get(getIndex());
                                Main.getMainPane().setCenter(new OrderItemsUpdateView(orderItem));
                            });
                            setGraphic(actionBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(nameColumn , descriptionColumn, priceColumn, quantityColumn, actionColumn);
		
		// set data source untuk table
		orderItemList = OrderItemController.getAllOrderItemsByOrderId(order.getOrderId().toString());
		
		// Mockup data
		orderItemList.add(
				new OrderItem(order.getOrderId(), new MenuItem(1, "Cola", "Deez Nuts", (double) 50), 10));
		orderItemList.add(
				new OrderItem(order.getOrderId(), new MenuItem(2, "Fanta", "Deez Nuts", (double) 50), 10));
		orderItemList.add(
				new OrderItem(order.getOrderId(), new MenuItem(3, "Coffee", "Deez Nuts", (double) 50), 10));
		
		// observable
		orderItemData = FXCollections.observableArrayList(orderItemList);
		
		// masukin data ke table
		table.setItems(orderItemData);
		
		middlePane.getChildren().add(table);
		VBox.setMargin(table, new Insets(20, 50, 0, 0));
	}
	
	private void showOrderData() {
		orderIdLbl = new Label("Order ID");
		orderIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		userLbl = new Label("Customer");
		userLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		statusLbl = new Label("Status");
		statusLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		dateLbl = new Label("Date");
		dateLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		totalLbl = new Label("Total Order Items");
		totalLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		orderIdTxt = new Label(order.getOrderId().toString());
		userTxt = new Label(order.getOrderUser().getUserName());
		statusTxt = new Label(order.getOrderStatus());
		if(statusTxt.getText().equals("Pending")) {
			statusTxt.setTextFill(Color.GOLD);
		}
		else if(statusTxt.getText().equals("Paid")) {
			statusTxt.setTextFill(Color.BLUE);
		}
		else if(statusTxt.getText().equals("Prepared")) {
			statusTxt.setTextFill(Color.ORANGE);
		}
		else if(statusTxt.getText().equals("Served")) {
			statusTxt.setTextFill(Color.GREEN);
		}
		
		dateTxt = new Label(order.getOrderDate().toString());
		totalTxt = new Label(order.getOrderTotal().toString());
		
		dataPane = new GridPane();
		dataPane.add(orderIdLbl, 0, 0);
		dataPane.add(userLbl, 0, 1);
		dataPane.add(statusLbl, 0, 2);
		dataPane.add(dateLbl, 0, 3);
		dataPane.add(totalLbl, 0, 4);
		
		dataPane.add(orderIdTxt, 1, 0);
		dataPane.add(userTxt, 1, 1);
		dataPane.add(statusTxt, 1, 2);
		dataPane.add(dateTxt, 1, 3);
		dataPane.add(totalTxt, 1, 4);
		
		dataPane.setHgap(50);
		
		middlePane.getChildren().addAll(dataPane);
		BorderPane.setMargin(middlePane, new Insets(20, 0, 0, 50));
		
	}
	
	private void showTitle() {
		titleLbl = new Label("My Order Details");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
	}

}
