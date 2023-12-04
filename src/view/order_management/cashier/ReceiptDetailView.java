package view.order_management.cashier;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.OrderItem;
import model.Receipt;

public class ReceiptDetailView extends BorderPane {
	private Label titleLbl, receiptIdLbl, receiptIdContentLbl, orderIdLbl, orderIdContentLbl, nameLbl, nameContentLbl, statusLbl, statusContentLbl, paymentDateLbl, paymentDateContentLbl, paymentAmountLbl, paymentAmountContentLbl, paymentTypeLbl, paymentTypeContentLbl, totalPriceLbl, totalPriceContentLbl;
	private Button backBtn;
	private TableView<OrderItem> detailTable;
	private ObservableList<OrderItem> detailData;
	private VBox contentBox;
	private GridPane headerPane, paymentPane;
	
	public ReceiptDetailView(Receipt receipt) {
		titleLbl = new Label("Order Detail");
		receiptIdLbl = new Label("Receipt Id: ");
		receiptIdContentLbl = new Label(receipt.getReceiptId().toString());
		orderIdLbl = new Label("Order Id: ");
		orderIdContentLbl = new Label(receipt.getReceiptOrder().getOrderId().toString());
		nameLbl = new Label("Customer Name: ");
		nameContentLbl = new Label(receipt.getReceiptOrder().getOrderUser().getUserName());
		statusLbl = new Label("Order Status: ");
		statusContentLbl = new Label(receipt.getReceiptOrder().getOrderStatus());
		paymentDateLbl = new Label("Payment Date: ");
		paymentDateContentLbl = new Label(receipt.getReceiptPaymentDate().toString());
		paymentAmountLbl = new Label("Paid Amount: ");
		paymentAmountContentLbl = new Label(receipt.getReceiptPaymentAmount().toString());
		paymentTypeLbl = new Label("Payment Type: ");
		paymentTypeContentLbl = new Label(receipt.getReceiptPaymentType());
		totalPriceLbl = new Label("Total Price: ");
		totalPriceContentLbl = new Label(receipt.getReceiptOrder().getOrderTotal().toString());
		
		backBtn = new Button("Back to order management table");
		contentBox = new VBox();
		headerPane = new GridPane();
		paymentPane = new GridPane();
		
		initTable();
		
	}

	private void initTable() {
		// TODO Auto-generated method stub
		
	}
}
