package controller;

import java.sql.Date;
import java.util.Vector;

import model.Order;
import model.Receipt;

public class ReceiptController {
	public static String createReceipt(Order order, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		//TODO: Validate type and amount, validate order exists
		return null;
	}
	
	public static String updateReceipt(Integer orderId, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		//TODO: Validate type and amount, validate order exists
		return null;
	}
	
	public static String deleteReceipt(Integer orderId) {
		//TODO: Validate that database successfully deletes data
		return null;
	}
	
	public static Receipt getReceiptById(Integer receiptId) {
		return Receipt.getReceiptById(receiptId); //TODO: Make validation if Id is not found
	}
	
	public static Vector<Receipt> getAllReceipts(){
		return Receipt.getAllReceipts();
	}
}
