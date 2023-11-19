package view.receipt_cashier;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

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
import model.Receipt;
import model.User;
import view.LoginView;

public class ReceiptManagementView extends BorderPane {
	
	private Label titleLbl;
	private TableView<Receipt> table;
	private ObservableList<Receipt> receiptData;
	private Vector<Receipt> receiptList;
	
	public ReceiptManagementView() {
		if(!UserController.getCurrentUser().getUserRole().equals("Cashier")) {
			Main.getMainPane().setCenter(new LoginView());
			return;
		}
		
		titleLbl = new Label("Receipt Management");
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		initTable();
		
		this.setTop(titleLbl);
		this.setCenter(table);
	}

	@SuppressWarnings("unchecked")
	private void initTable() {
		table = new TableView<Receipt>();
		TableColumn<Receipt, Integer> receiptIdColumn = new TableColumn<Receipt, Integer>("Receipt ID");
		receiptIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptId"));
		TableColumn<Receipt, Integer> orderIdColumn = new TableColumn<Receipt, Integer>("Order ID");
		orderIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReceiptOrder().getOrderId()).asObject());
		TableColumn<Receipt, String> customerNameColumn = new TableColumn<Receipt, String>("Customer Name");
		customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReceiptOrder().getOrderUser().getUserName().toString()));
		TableColumn<Receipt, Double> paymentAmountColumn = new TableColumn<Receipt, Double>("Payment Amount");
		paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount"));
		TableColumn<Receipt, String> paymentTypeColumn = new TableColumn<Receipt, String>("Payment Type");
		paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType"));
		TableColumn<Receipt, Date> paymentDateColumn = new TableColumn<Receipt, Date>("Payment Date");
		paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate"));
		
		TableColumn<Receipt, String> selectAction = new TableColumn<Receipt, String>("View Detail");
		selectAction.setCellFactory(new Callback<TableColumn<Receipt,String>, TableCell<Receipt,String>>() {
			
			@Override
			public TableCell<Receipt, String> call(TableColumn<Receipt, String> arg0) {
				TableCell<Receipt, String> cell = new TableCell<>() {
					Button selectBtn = new Button("Select");
					
					@Override
					protected void updateItem(String item, boolean empty) {
						if(empty) {
							setGraphic(null);
							setText(null);
						}else {
							selectBtn.setOnAction(e -> {
								Receipt receipt = getTableView().getItems().get(getIndex());
								Main.getMainPane().setCenter(new ReceiptDetailView(receipt));
							});
							setGraphic(selectBtn);
							setText(null);
						}
					};
				};
				return cell;
			}
		});
		
		table.getColumns().addAll(receiptIdColumn, orderIdColumn, customerNameColumn, paymentAmountColumn, paymentTypeColumn, paymentDateColumn, selectAction);
		
		receiptList = ReceiptController.getAllReceipts();
		
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
		
		receiptList.add(new Receipt(1, o1, (double) 10000, new Date(Calendar.getInstance().getTimeInMillis()), "Debit"));
		receiptList.add(new Receipt(2, o2, (double) 20000, new Date(Calendar.getInstance().getTimeInMillis()), "Cash"));
		
		receiptData = FXCollections.observableArrayList(receiptList);
		table.setItems(receiptData);
	}
}
