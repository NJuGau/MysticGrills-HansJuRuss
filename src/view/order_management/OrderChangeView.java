package view.order_management;

import java.sql.Date;
import java.util.Vector;

import controller.OrderController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import main.Main;
import model.Order;
import view.LoginView;
import view.Navbar;
import view.order_management.cashier.CashierOrderDetailView;
import view.order_management.chef.ChefOrderDetailView;
import view.order_management.waiters.WaiterOrderDetailView;


public class OrderChangeView extends BorderPane {
	//Halaman order change management yang dapat diakses chef dan waiter. Akan ditampilkan 'pending' order. Mereka dapat memilih order untuk diubah ataupun dihapus.
	private Label titleLbl;
	private TableView<Order> table;
	private ObservableList<Order> orderData;
	private Vector<Order> orderList;
	private VBox idBox;
	private HBox idTxtGroup, btnGroup;
	private Button updateBtn, cancelBtn;
	private TextField idTxt;
	private Label idLbl, noteLbl, errorLbl;	
	private Order selectedOrder;
	
	public OrderChangeView() {
		if(!UserController.getCurrentUser().getUserRole().equals("Chef") && !UserController.getCurrentUser().getUserRole().equals("Waiter")) {
			Main.getMainPane().setCenter(new LoginView());
		}else {
			titleLbl = new Label("Change Order");
			idLbl = new Label("Order Id: ");
			noteLbl = new Label("Note: Press one of the button in the table to select id");
			noteLbl.setTextFill(Color.GREEN);
			errorLbl = new Label();
			errorLbl.setTextFill(Color.RED);
			idTxt = new TextField();
			cancelBtn = new Button("Cancel | Wipe Id");
			updateBtn = new Button("Update Order");
			
			idBox = new VBox();
			idTxtGroup = new HBox();
			btnGroup = new HBox();
			
			idTxt.setEditable(false);
			
			idTxtGroup.getChildren().addAll(idLbl, idTxt);
			
			cancelBtn.setOnMouseClicked(e -> {
				idTxt.setText("");
			});
			
			updateBtn.setOnMouseClicked(e -> {
				OrderController.setOrderID(selectedOrder.getOrderId());
				// Update Navbar
				Main.getMainPane().setTop(new Navbar());
				
				Main.getMainPane().setCenter(new OrderChangeDetailsView(selectedOrder));
			});
			
			btnGroup.getChildren().addAll(cancelBtn, updateBtn);
			idBox.getChildren().addAll(idTxtGroup, noteLbl, errorLbl, btnGroup);
			
			titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
			BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
			BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
			
			BorderPane.setMargin(idBox, new Insets(20, 0, 20, 20));
			
			HBox.setMargin(cancelBtn, new Insets(0, 20, 0, 0));
			HBox.setMargin(updateBtn, new Insets(0, 20, 0, 0));
			VBox.setMargin(idTxtGroup, new Insets(0, 0, 10, 0));
			VBox.setMargin(errorLbl, new Insets(0, 0, 10, 0));
			VBox.setMargin(btnGroup, new Insets(0, 0, 10, 0));
			
			initTable();
			
			this.setTop(titleLbl);
			this.setCenter(table);
			this.setBottom(idBox);
		}
	}

	@SuppressWarnings("unchecked")
	private void initTable() {
		orderList = new Vector<Order>();
		
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
		
		TableColumn<Order, String> payAction = new TableColumn<Order, String>("View Detail");
		payAction.setCellFactory(new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {

				@Override
				public TableCell<Order, String> call(TableColumn<Order, String> arg0) {
					TableCell<Order, String> cell = new TableCell<>() {
						Button payBtn = new Button("Select");

						@Override
						protected void updateItem(String item, boolean empty) {
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								payBtn.setOnAction(e -> {
									selectedOrder = getTableView().getItems().get(getIndex());
									idTxt.setText(selectedOrder.getOrderId().toString());
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
			
		orderList = OrderController.getOrderByStatus("Pending");
		
		orderData = FXCollections.observableArrayList(orderList);
		table.setItems(orderData);
	}
}
