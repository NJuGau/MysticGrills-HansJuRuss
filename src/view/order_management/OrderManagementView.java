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
		
		//Temporary only a.k.a. dummy data
		User u1 = new User(3, "Customer", "Rita", "Rita@sinub.ac.id", "r1tA");
		User u2 = new User(4, "Customer", "Tina", "Tina@sinub.ac.id", "r1tA");
		
		MenuItem m1 = new MenuItem(1, "Cola", "Deez Nuts", (double) 50);
		MenuItem m2 = new MenuItem(2, "Fanta", "Deez Nutss", (double) 100);
		MenuItem m3 = new MenuItem(3, "Sprite", "Deez Nutsss", (double) 120);
		
		Vector<OrderItem> orderList = new Vector<>();
		orderList.add(new OrderItem(1, m1, 2));
		orderList.add(new OrderItem(1, m2, 3));
		
		Vector<OrderItem> orderList2 = new Vector<>();
		orderList2.add(new OrderItem(2, m3, 1));
		
		Order o1 = new Order(1, u1, orderList, "Prepared", new Date(Calendar.getInstance().getTimeInMillis()));
		Order o2 = new Order(2, u2, orderList2, "Served", new Date(Calendar.getInstance().getTimeInMillis()));
		
		receiptList.add(o1);
		receiptList.add(o2);
		
		receiptData = FXCollections.observableArrayList(receiptList);
		table.setItems(receiptData);
	}
}
