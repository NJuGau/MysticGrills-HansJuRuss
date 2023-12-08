package view.order_management.chef;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import controller.OrderController;
import controller.ReceiptController;
import controller.UserController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import model.Order;
import model.OrderItem;
import view.LoginView;
import view.order_management.OrderManagementView;

public class ChefOrderDetailView extends BorderPane {
	private Label titleLbl, orderIdLbl, orderIdContentLbl, nameLbl, nameContentLbl, statusLbl, statusContentLbl, totalPriceLbl, totalPriceContentLbl, errorLbl;
	private VBox contentBox;
	private HBox btnGroup;
	private Button backBtn, prepareBtn;
	private GridPane headerPane, paymentPane;
	private TableView<OrderItem> detailTable;
	private ObservableList<OrderItem> detailData;
	
	public ChefOrderDetailView(Order order) {
		if(!UserController.getCurrentUser().getUserRole().equals("Chef")) {
			Main.getMainPane().setCenter(new LoginView());
			return;
		}
		
		titleLbl = new Label("Order Detail");
		orderIdLbl = new Label("Order ID: ");
		orderIdContentLbl = new Label(order.getOrderId().toString());
		nameLbl = new Label("Customer Name: ");
		nameContentLbl = new Label(order.getOrderUser().getUserName());
		statusLbl = new Label("Order Status");
		statusContentLbl = new Label(order.getOrderStatus());
		statusContentLbl.setTextFill(Color.GOLD);
		totalPriceLbl = new Label("Total Price: ");
		totalPriceContentLbl = new Label(order.getOrderTotal().toString());
		errorLbl = new Label();
		
		backBtn = new Button("Back");
		prepareBtn = new Button("Prepare Order");
		
		initTable(order.getOrderItems());
		
		headerPane = new GridPane();
		paymentPane = new GridPane();
		contentBox = new VBox();
		btnGroup = new HBox();
		
		headerPane.add(orderIdLbl, 0, 1);
		headerPane.add(orderIdContentLbl, 1, 1);
		headerPane.add(nameLbl, 0, 2);
		headerPane.add(nameContentLbl, 1, 2);
		headerPane.add(statusLbl, 0, 3);
		headerPane.add(statusContentLbl, 1, 3);
		
		paymentPane.add(totalPriceLbl, 0, 0);
		paymentPane.add(totalPriceContentLbl, 1, 0);
		paymentPane.add(errorLbl, 0, 3, 2, 1);
		
		contentBox.getChildren().addAll(headerPane, detailTable, paymentPane);
		btnGroup.getChildren().addAll(backBtn, prepareBtn);
		
		backBtn.setOnAction(e ->{
			Main.getMainPane().setCenter(new OrderManagementView());
		});
		
		prepareBtn.setOnAction(e ->{
			String status = OrderController.updateOrder(order.getOrderId().toString(), order.getOrderItems(), "Prepared");
			statusContentLbl.setText("Prepared");
			statusContentLbl.setTextFill(Color.ORANGE);
			if(status == null) {
				errorLbl.setText("Success");
				errorLbl.setTextFill(Color.GREEN);
				prepareBtn.setDisable(true);
			}
			else {
				errorLbl.setText(status);
				errorLbl.setTextFill(Color.RED);
			}
			
		});
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		orderIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		statusLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		totalPriceLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		headerPane.setAlignment(Pos.TOP_LEFT);
		paymentPane.setAlignment(Pos.TOP_LEFT);
		headerPane.setVgap(10);
		headerPane.setHgap(30);
		paymentPane.setVgap(10);
		paymentPane.setHgap(30);
		
		HBox.setMargin(backBtn, new Insets(0, 10, 0, 0));
		HBox.setMargin(prepareBtn, new Insets(0, 10, 0, 10));
		
		BorderPane.setMargin(contentBox, new Insets(20));
		
		VBox.setMargin(headerPane, new Insets(0, 0, 30, 0));
		VBox.setMargin(paymentPane, new Insets(30, 0, 0, 0));
		
		BorderPane.setMargin(btnGroup, new Insets(0, 0, 20, 20));
		
		this.setTop(titleLbl);
		this.setCenter(contentBox);
		this.setBottom(btnGroup);
	}


	@SuppressWarnings("unchecked")
	private void initTable(Vector<OrderItem> orderItemList) {
		detailTable = new TableView<OrderItem>();
		
		TableColumn<OrderItem, String> itemNameColumn = new TableColumn<OrderItem, String>("Item Name");
		itemNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemName()));
		TableColumn<OrderItem, Double> itemPriceColumn = new TableColumn<OrderItem, Double>("Item Price");
		itemPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMenuItem().getMenuItemPrice()).asObject());
		TableColumn<OrderItem,Integer> itemQuantityColumn = new TableColumn<OrderItem, Integer>("Item Quantity");
		itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
		
		detailTable.getColumns().addAll(itemNameColumn, itemPriceColumn, itemQuantityColumn);
		
		detailData = FXCollections.observableArrayList(orderItemList);
		
		detailTable.setItems(detailData);
	}

}
