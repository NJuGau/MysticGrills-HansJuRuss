package view.receipt_cashier;

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
		// TODO Auto-generated constructor stub
	}
}
