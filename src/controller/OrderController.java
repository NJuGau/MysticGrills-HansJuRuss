package controller;

import java.sql.Date;
import java.util.Vector;

import model.Order;
import model.OrderItem;
import model.User;

public class OrderController {

	public static Integer createOrder(User orderUser, Vector<OrderItem> orderItems, Date orderDate) {
		
		return Order.createOrder(orderUser, orderItems, orderDate);
	}
	
	public static String updateOrder(String orderId, Vector<OrderItem> orderItems, String orderStatus) {
		if(orderId.isEmpty()) {
			return "Order Id must be chosen";
		}
		
		return Order.updateOrder(Integer.parseInt(orderId), orderItems, orderStatus);
	}
	
	public static String deleteOrder(Integer orderId) {
		
		return Order.deleteOrder(orderId);
	}
	
	public static Vector<Order> getOrdersByCustomerId(Integer customerId) {
		
		return Order.getOrdersByCustomerId(customerId);
	}
	
	public static Vector<Order> getAllOrders() {
		
		return Order.getAllOrders();
	}
	
	public static Order getOrderByOrderId(Integer orderId) {
		
		return Order.getOrderByOrderId(orderId);
	}

}
