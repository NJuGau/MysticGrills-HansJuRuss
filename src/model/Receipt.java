package model;

import java.sql.Date;
import java.util.Vector;

public class Receipt {
	private Integer receiptId;
	private Order receiptOrder;
	private Double receiptPaymentAmount;
	private Date receiptPaymentDate;
	private String receiptPaymentType;
	
	public Receipt(Integer receiptId, Order receiptOrder, Double receiptPaymentAmount, Date receiptPaymentDate,
			String receiptPaymentType) {
		super();
		this.receiptId = receiptId;
		this.receiptOrder = receiptOrder;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate;
		this.receiptPaymentType = receiptPaymentType;
	}
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public Order getReceiptOrder() {
		return receiptOrder;
	}
	public void setReceiptOrder(Order receiptOrder) {
		this.receiptOrder = receiptOrder;
	}
	public Double getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}
	public void setReceiptPaymentAmount(Double receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}
	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}
	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}
	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}
	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
	public static String createReceipt(Order order, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		//TODO: Connect to DB
		return null;
	}
	
	public static String updateReceipt(Integer orderId, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		//TODO: Connect to DB
		return null;		
	}
	
	public static String deleteReceipt(Integer orderId) {
		//TODO: Connect to DB
		return null;
	}
	
	public static Receipt getReceiptById(Integer receiptId) {
		//TODO: Connect to DB
		return null;
	}
	
	public static Vector<Receipt> getAllReceipts(){
		//TODO: Connect to DB
		return new Vector<Receipt>();
	}
}
