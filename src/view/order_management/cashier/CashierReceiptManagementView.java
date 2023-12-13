package view.order_management.cashier;

import java.sql.Date;
import java.util.Vector;

import controller.OrderController;
import controller.ReceiptController;
import controller.UserController;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import model.Receipt;
import view.LoginView;
import view.order_management.waiters.WaiterOrderDetailView;

public class CashierReceiptManagementView extends BorderPane {
	//Halaman receipt managament yang hanya dapat diakses cashier. Akan ditampilkan list of receipt yang dapat dipilih untuk dilihat detailnya.
	private Label titleLbl;
	private TableView<Receipt> table;
	private ObservableList<Receipt> receiptData;
	private Vector<Receipt> receiptList;
	
	public CashierReceiptManagementView() {
		if(!UserController.getCurrentUser().getUserRole().equals("Cashier")) {
			Main.getMainPane().setCenter(new LoginView());
		}else {
			titleLbl = new Label("Receipt Management");
			
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
		receiptList = ReceiptController.getAllReceipts();
		Vector<Receipt> allOrder = ReceiptController.getAllReceipts();
		table = new TableView<Receipt>();
		TableColumn<Receipt, Integer> receiptIdColumn = new TableColumn<Receipt, Integer>("Receipt ID");
		receiptIdColumn.setCellValueFactory(new PropertyValueFactory<>("receiptId"));
		TableColumn<Receipt, Integer> orderIdColumn = new TableColumn<Receipt, Integer>("Order ID");
		orderIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getReceiptOrder().getOrderId()).asObject());
		TableColumn<Receipt, String> customerNameColumn = new TableColumn<Receipt, String>("Customer Name");
		customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReceiptOrder().getOrderUser().getUserName().toString()));
		TableColumn<Receipt, Date> dateColumn = new TableColumn<Receipt, Date>("Receipt Payment Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate"));
		TableColumn<Receipt, Double> totalColumn = new TableColumn<Receipt, Double>("Payment Amount");
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount"));
		TableColumn<Receipt, String> typeColumn = new TableColumn<Receipt, String>("Payment Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType"));
		TableColumn<Receipt, String> viewAction = new TableColumn<Receipt, String>("View Receipt Detail");
		viewAction.setCellFactory(new Callback<TableColumn<Receipt, String>, TableCell<Receipt, String>>() {

			@Override
			public TableCell<Receipt, String> call(TableColumn<Receipt, String> arg0) {
				TableCell<Receipt, String> cell = new TableCell<>() {
					Button selectBtn = new Button("Select");

					@Override
					protected void updateItem(String item, boolean empty) {
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
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
		table.getColumns().addAll(receiptIdColumn, orderIdColumn, dateColumn, customerNameColumn, totalColumn, typeColumn, viewAction);
	
		receiptData = FXCollections.observableArrayList(receiptList);
		table.setItems(receiptData);
	}
}
