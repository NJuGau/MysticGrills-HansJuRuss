package view.order_management.cashier;

import java.util.Vector;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import model.OrderItem;
import model.Receipt;
import view.order_management.OrderManagementView;

public class ReceiptDetailView extends BorderPane {
	//Halaman receipt detail yang hanya dapat dilihat oleh cashier. Ditampilkan detail dari receipt yang dipilih.
	private Label titleLbl, receiptIdLbl, receiptIdContentLbl, orderIdLbl, orderIdContentLbl, nameLbl, nameContentLbl, statusLbl, statusContentLbl, paymentDateLbl, paymentDateContentLbl, paymentAmountLbl, paymentAmountContentLbl, paymentTypeLbl, paymentTypeContentLbl, totalPriceLbl, totalPriceContentLbl;
	private Button backBtn;
	private TableView<OrderItem> detailTable;
	private ObservableList<OrderItem> detailData;
	private VBox contentBox;
	private GridPane headerPane, paymentPane;
	
	public ReceiptDetailView(Receipt receipt) {
		titleLbl = new Label("Order Receipt");
		receiptIdLbl = new Label("Receipt Id: ");
		receiptIdContentLbl = new Label(receipt.getReceiptId().toString());
		orderIdLbl = new Label("Order Id: ");
		orderIdContentLbl = new Label(receipt.getReceiptOrder().getOrderId().toString());
		nameLbl = new Label("Customer Name: ");
		nameContentLbl = new Label(receipt.getReceiptOrder().getOrderUser().getUserName());
		statusLbl = new Label("Order Status: ");
		statusContentLbl = new Label("Paid");
		paymentDateLbl = new Label("Payment Date: ");
		paymentDateContentLbl = new Label(receipt.getReceiptPaymentDate().toString());
		paymentAmountLbl = new Label("Paid Amount: ");
		paymentAmountContentLbl = new Label(receipt.getReceiptPaymentAmount().toString());
		paymentTypeLbl = new Label("Payment Type: ");
		paymentTypeContentLbl = new Label(receipt.getReceiptPaymentType());
		totalPriceLbl = new Label("Total Price: ");
		totalPriceContentLbl = new Label(receipt.getReceiptOrder().getOrderTotal().toString());
		
		backBtn = new Button("Back to order management table");
		initTable(receipt.getReceiptOrder().getOrderItems());
		contentBox = new VBox();
		headerPane = new GridPane();
		paymentPane = new GridPane();
		
		headerPane.add(receiptIdLbl, 0, 0);
		headerPane.add(receiptIdContentLbl, 1, 0);
		headerPane.add(orderIdLbl, 0, 1);
		headerPane.add(orderIdContentLbl, 1, 1);
		headerPane.add(nameLbl, 0, 2);
		headerPane.add(nameContentLbl, 1, 2);
		headerPane.add(statusLbl, 0, 3);
		headerPane.add(statusContentLbl, 1, 3);
		headerPane.add(paymentDateLbl, 0, 4);
		headerPane.add(paymentDateContentLbl, 1, 4);
		
		paymentPane.add(totalPriceLbl, 0, 0);
		paymentPane.add(totalPriceContentLbl, 1, 0);
		paymentPane.add(paymentAmountLbl, 0, 1);
		paymentPane.add(paymentAmountContentLbl, 1, 1);
		paymentPane.add(paymentTypeLbl, 0, 2);
		paymentPane.add(paymentTypeContentLbl, 1, 2);
		
		contentBox.getChildren().addAll(headerPane, detailTable, paymentPane);
		
		backBtn.setOnAction(e ->{
			Main.getMainPane().setCenter(new OrderManagementView());
		});
		
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		
		receiptIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		orderIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		statusLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		totalPriceLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		paymentDateLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		paymentAmountLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		paymentTypeLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		headerPane.setAlignment(Pos.TOP_LEFT);
		paymentPane.setAlignment(Pos.TOP_LEFT);
		headerPane.setVgap(10);
		headerPane.setHgap(30);
		paymentPane.setVgap(10);
		paymentPane.setHgap(30);
		
		BorderPane.setMargin(contentBox, new Insets(20));
		
		VBox.setMargin(headerPane, new Insets(0, 0, 30, 0));
		VBox.setMargin(paymentPane, new Insets(30, 0, 0, 0));
		
		BorderPane.setMargin(backBtn, new Insets(0, 0, 20, 20));
		
		this.setTop(titleLbl);
		this.setCenter(contentBox);
		this.setBottom(backBtn);
		
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
