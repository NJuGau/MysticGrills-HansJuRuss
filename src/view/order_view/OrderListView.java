package view.order_view;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import controller.MenuItemController;
import controller.OrderController;
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
import model.Order;

public class OrderListView extends BorderPane{
	private static Integer orderID = null;
	
	private Label titleLbl;
	
	public OrderListView() {
		showTitle();
		
		
		initTable();
		// Get orderID which order status is empty
		for(Order o : orderList) {
			if(o.getOrderStatus().isEmpty()) {
				orderID = o.getOrderId();
				break;
			}
		}
	}
	
	private TableView<Order> table;
	private ObservableList<Order> orderData;
	private Vector<Order> orderList;
	@SuppressWarnings("unchecked")
	public void initTable() {
		table = new TableView<Order>();
		TableColumn<Order, Integer> idColumn = new TableColumn<>("ID"); // Header
		idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId")); // Tipe variable pada model
		
		TableColumn<Order, String> nameColumn = new TableColumn<>("Customer"); // Header
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderUser().getUserName()));
		
		TableColumn<Order, String> statusColumn = new TableColumn<>("Status"); // Header
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus")); // Tipe variable pada model
		
		TableColumn<Order, String> dateColumn = new TableColumn<>("Date"); // Header
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderDate().toString())); // Tipe variable pada model
		
		TableColumn<Order, Integer> totalColumn = new TableColumn<>("Total"); // Header
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal")); // Tipe variable pada model
		
		TableColumn<Order, String> actionColumn = new TableColumn<>("Action"); // Header
		actionColumn.setCellFactory(new Callback<TableColumn<Order,String>, TableCell<Order,String>>() {
			
			@Override
			public TableCell<Order, String> call(TableColumn<Order, String> arg0) {
				TableCell<Order, String> cell = new TableCell<Order, String>(){
					Button actionBtn = new Button("Select");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	actionBtn.setOnAction(event -> {
                                Order order = getTableView().getItems().get(getIndex());
                                Main.getMainPane().setCenter(new OrderDetailsView(order));
                            });
                            setGraphic(actionBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(idColumn ,nameColumn , statusColumn, dateColumn, totalColumn, actionColumn);
		
		// set data source untuk table
		orderList = OrderController.getOrdersByCustomerId(UserController.getCurrentUser().getUserId());
		
		orderList.add(new Order(1, UserController.getCurrentUser(), null, "", new Date(Calendar.getInstance().getTimeInMillis())));
		orderList.add(new Order(2, UserController.getCurrentUser(), null, "Pending", new Date(Calendar.getInstance().getTimeInMillis())));
		orderList.add(new Order(3, UserController.getCurrentUser(), null, "Pending", new Date(Calendar.getInstance().getTimeInMillis())));
		
		// observable
		orderData = FXCollections.observableArrayList(orderList);
		
		// masukin data ke table
		table.setItems(orderData);
		
		this.setCenter(table);
	}
	
	public void showTitle() {
		titleLbl = new Label("My Orders");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
	}
	
	public static Integer getOrderID() {
		return orderID;
	}
	
	public static void setOrderID(Integer id) {
		orderID = id;
	}

}
