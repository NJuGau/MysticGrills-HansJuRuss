package view.order_management;

import java.text.DecimalFormat;
import java.util.Vector;

import controller.OrderController;
import controller.OrderItemController;
import controller.UserController;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.Order;
import model.OrderItem;
import view.LoginView;
import view.menu_item_view_customer.MenuCustomerView;
import view.order_view.OrderDetailsView;
import view.order_view.OrderItemsDeleteView;
import view.order_view.OrderItemsUpdateView;

public class OrderChangeDetailsView extends BorderPane {
	private Order order;
	
	private Label titleLbl, orderIdLbl, orderIdTxt, userLbl, userTxt, statusLbl, statusTxt,
					dateLbl, dateTxt, totalLbl, totalTxt, submitStatusLbl;
	private GridPane dataPane;
	private VBox middlePane;
	private HBox actionBtnContainer;
	private Button addButton, deleteOrderBtn;

	public OrderChangeDetailsView(Order order) {
		if(!UserController.getCurrentUser().getUserRole().equals("Chef") && !UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			Main.getMainPane().setCenter(new LoginView());
		}
		else {
			this.order = order;
			showTitle();
			
			middlePane = new VBox();
			this.setCenter(middlePane);
			
			showOrderData();
			
			showActionButton();
			
			initTable();
		}
		
	}
	
	private void showTitle() {
		titleLbl = new Label("My Order Details");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
	}
	
	private void showActionButton() {
		actionBtnContainer = new HBox();
		submitStatusLbl = new Label();
		
		addButton = new Button("Add new order");
		addButton.setOnAction(event -> {
			OrderController.setOrderID(order.getOrderId());
			Main.getMainPane().setCenter(new MenuCustomerView());
		});
		
		deleteOrderBtn = new Button("Delete this Order");
		deleteOrderBtn.setOnAction(event -> {
			order.setOrderStatus("Pending");
			OrderController.deleteOrder(order.getOrderId());
			
			submitStatusLbl.setText("Success");
			submitStatusLbl.setTextFill(Color.GREEN);
			addButton.setDisable(true);
			deleteOrderBtn.setDisable(true);
			
			Main.getMainPane().setCenter(new OrderChangeView());
		});
		
		actionBtnContainer.getChildren().addAll(addButton, deleteOrderBtn);
		HBox.setMargin(addButton,new Insets(0, 10, 0, 0));
		VBox.setMargin(actionBtnContainer, new Insets(20, 0, 0, 0));
		middlePane.getChildren().addAll(actionBtnContainer, submitStatusLbl);
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
	
	private TableView<OrderItem> table;
	private ObservableList<OrderItem> orderItemData;
	private Vector<OrderItem> orderItemList;
	@SuppressWarnings("unchecked")
	private void initTable() {
		table = new TableView<OrderItem>();
		TableColumn<OrderItem, String> nameColumn = new TableColumn<>("Menu Item Name"); // Header
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemName()));
		
		TableColumn<OrderItem, String> descriptionColumn = new TableColumn<>("Menu Item Description"); // Header
		descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemDescription()));
		descriptionColumn.setPrefWidth(400);
		
		TableColumn<OrderItem, String> priceColumn = new TableColumn<>("Menu Item Price"); // Header
		priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMenuItem().getMenuItemPrice().toString()));
		
		TableColumn<OrderItem, String> quantityColumn = new TableColumn<>("Quantity"); // Header
		quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity().toString()));
		
		TableColumn<OrderItem, String> selectActionColumn = new TableColumn<>("Select"); // Header
		selectActionColumn.setCellFactory(new Callback<TableColumn<OrderItem,String>, TableCell<OrderItem,String>>() {
			
			@Override
			public TableCell<OrderItem, String> call(TableColumn<OrderItem, String> arg0) {
				TableCell<OrderItem, String> cell = new TableCell<OrderItem, String>(){
					Button actionBtn = new Button("Update");
					
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
		
		TableColumn<OrderItem, String> deleteActionColumn = new TableColumn<>("Delete"); // Header
		deleteActionColumn.setCellFactory(new Callback<TableColumn<OrderItem,String>, TableCell<OrderItem,String>>() {
			
			@Override
			public TableCell<OrderItem, String> call(TableColumn<OrderItem, String> arg0) {
				TableCell<OrderItem, String> cell = new TableCell<OrderItem, String>(){
					Button actionBtn = new Button("Remove");
					
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
                                Main.getMainPane().setCenter(new OrderItemsDeleteView(orderItem));
                            });
                            setGraphic(actionBtn);
                            setText(null);
                        }
					}
				};
				
				return cell;
			}
		});
		
		table.getColumns().addAll(nameColumn , descriptionColumn, priceColumn, quantityColumn, selectActionColumn, deleteActionColumn);
	
		// set data source untuk table
		orderItemList = OrderItemController.getAllOrderItemsByOrderId(order.getOrderId().toString());
		putOrderTotal();
		
		// observable
		orderItemData = FXCollections.observableArrayList(orderItemList);
		
		// masukin data ke table
		table.setItems(orderItemData);
		
		middlePane.getChildren().add(table);
		VBox.setMargin(table, new Insets(20, 50, 0, 0));
	}
	
	private void putOrderTotal() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
		String priceDecimalFormat = decimalFormat.format(OrderController.calculateCurrentOrderTotal(orderItemList));
		totalTxt.setText(priceDecimalFormat);
	}

}
