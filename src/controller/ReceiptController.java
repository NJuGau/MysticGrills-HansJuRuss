package controller;

import java.sql.Date;
import java.util.Vector;

import model.Order;
import model.Receipt;

public class ReceiptController {
	//CREATE method
	public static String createReceipt(Order order, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		String orderIdValidation = validateOrderId(order.getOrderId());
		String amountValidation = validatePaymentAmount(receiptPaymentAmount, order.getOrderTotal());
		String typeValidation = validateReceiptType(receiptPaymentType);
		
		if(orderIdValidation != null) {
			return orderIdValidation;
		}else if(amountValidation != null) {
			return amountValidation;
		}else if(typeValidation != null) {
			return typeValidation;
		}
		//if all is correct, return the newly created receipt ID, in String
		return Receipt.createReceipt(order, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate).toString();
	}
	
	//UPDATE method
	public static String updateReceipt(Integer orderId, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		String orderIdValidation = validateOrderId(orderId);
		
		if(orderIdValidation != null) {
			return orderIdValidation;
		}
		Order order = OrderController.getOrderByOrderId(orderId);
		String amountValidation = validatePaymentAmount(receiptPaymentAmount, order.getOrderTotal());
		String typeValidation = validateReceiptType(receiptPaymentType);
		if(amountValidation != null) {
			return amountValidation;
		}else if(typeValidation != null) {
			return typeValidation;
		}
		return ReceiptController.updateReceipt(orderId, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate);
	}
	
	//DELETE method
	public static String deleteReceipt(Integer orderId) {
		return Receipt.deleteReceipt(orderId);
	}
	
	//SELECT by receipt Id method
	public static Receipt getReceiptById(Integer receiptId) {
		return Receipt.getReceiptById(receiptId);
	}
	
	//SELECT all method
	public static Vector<Receipt> getAllReceipts(){
		return Receipt.getAllReceipts();
	}
	
	//validation
	private static String validateOrderId(Integer orderId) {
		Vector<Order> orderList = OrderController.getAllOrders();
		for(Order o : orderList) {
			if(orderId == o.getOrderId()) {
				return null;
			}
		}
		return "Order ID Not Found";
	}
	
	private static String validatePaymentAmount(Double paymentAmount, Double totalPrice) {
		if(paymentAmount < totalPrice) {
			return "payment amount must be greater than or equal to total price";
		}
		System.out.println("amount safe");
		return null;
	}
	
	private static String validateReceiptType(String type) {
		if(type == null || (!type.equals("Cash") && !type.equals("Debit") && !type.equals("Credit"))) {
			return "payment type must be either 'Cash', 'Debit', or 'Credit'";
		}
		System.out.println("type safe");
		return null;
	}
}
