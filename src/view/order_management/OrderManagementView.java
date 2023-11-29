package view.order_management;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import controller.OrderController;
import controller.ReceiptController;
import controller.UserController;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;
import view.LoginView;
import view.order_management.cashier.CashierOrderDetailView;
import view.order_management.chef.ChefOrderDetailView;
import view.order_management.waiters.WaiterOrderDetailView;

public class OrderManagementView extends BorderPane {
	
	private Label titleLbl;
	private TableView<Order> table;
	private ObservableList<Order> receiptData;
	private Vector<Order> receiptList;
	
	public OrderManagementView() {
		if(!UserController.getCurrentUser().getUserRole().equals("Cashier") && !UserController.getCurrentUser().getUserRole().equals("Chef") && !UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			Main.getMainPane().setCenter(new LoginView());
		}else {
			titleLbl = new Label("Order Management");
			
			titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
			BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
			BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
			
			initTable();
			
			this.setTop(titleLbl);
			this.setCenter(table);
		}
	}

	@SuppressWarnings("unchecked")
	private void initTable() {
		table = new TableView<Order>();
		TableColumn<Order, Integer> orderIdColumn = new TableColumn<Order, Integer>("Order ID");
		orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		TableColumn<Order, String> customerNameColumn = new TableColumn<Order, String>("Customer Name");
		customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderUser().getUserName().toString()));
		TableColumn<Order, String> statusColumn = new TableColumn<Order, String>("Order Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		TableColumn<Order, Date> dateColumn = new TableColumn<Order, Date>("Order Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		TableColumn<Order, Double> totalColumn = new TableColumn<Order, Double>("Total price");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));
		
		if (UserController.getCurrentUser().getUserRole().equals("Cashier")) {
			TableColumn<Order, String> payAction = new TableColumn<Order, String>("View Detail");
			payAction.setCellFactory(new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {

				@Override
				public TableCell<Order, String> call(TableColumn<Order, String> arg0) {
					TableCell<Order, String> cell = new TableCell<>() {
						Button payBtn = new Button("Pay Order");

						@Override
						protected void updateItem(String item, boolean empty) {
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								payBtn.setOnAction(e -> {
									Order order = getTableView().getItems().get(getIndex());
									Main.getMainPane().setCenter(new CashierOrderDetailView(order));
								});
								setGraphic(payBtn);
								setText(null);
							}
						};
					};
					return cell;
				}
			});
			table.getColumns().addAll(orderIdColumn, dateColumn, customerNameColumn, statusColumn, totalColumn, payAction);
			
			//TODO: Get 'Pending' order
			receiptList = OrderController.getAllOrders();
		}else if(UserController.getCurrentUser().getUserRole().equals("Chef")) {
			TableColumn<Order, String> prepareAction = new TableColumn<Order, String>("View Detail");
			prepareAction.setCellFactory(new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {

				@Override
				public TableCell<Order, String> call(TableColumn<Order, String> arg0) {
					TableCell<Order, String> cell = new TableCell<>() {
						Button prepareBtn = new Button("Prepare Order");

						@Override
						protected void updateItem(String item, boolean empty) {
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								prepareBtn.setOnAction(e -> {
									Order order = getTableView().getItems().get(getIndex());
									Main.getMainPane().setCenter(new ChefOrderDetailView(order));
								});
								setGraphic(prepareBtn);
								setText(null);
							}
						};
					};
					return cell;
				}
			});
			table.getColumns().addAll(orderIdColumn, dateColumn, customerNameColumn, statusColumn, totalColumn, prepareAction);
			
			//TODO: Get 'Paid' order
			receiptList = OrderController.getAllOrders();
			
		}else if(UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			TableColumn<Order, String> serveAction = new TableColumn<Order, String>("View Detail");
			serveAction.setCellFactory(new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {

				@Override
				public TableCell<Order, String> call(TableColumn<Order, String> arg0) {
					TableCell<Order, String> cell = new TableCell<>() {
						Button serveBtn = new Button("Serve Order");

						@Override
						protected void updateItem(String item, boolean empty) {
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								serveBtn.setOnAction(e -> {
									Order order = getTableView().getItems().get(getIndex());
									Main.getMainPane().setCenter(new WaiterOrderDetailView(order));
								});
								setGraphic(serveBtn);
								setText(null);
							}
						};
					};
					return cell;
				}
			});
			table.getColumns().addAll(orderIdColumn, dateColumn, customerNameColumn, statusColumn, totalColumn, serveAction);
			
			//TODO: Get 'Prepared' order
			receiptList = OrderController.getAllOrders();
		}
		
		receiptData = FXCollections.observableArrayList(receiptList);
		table.setItems(receiptData);
	}
}
