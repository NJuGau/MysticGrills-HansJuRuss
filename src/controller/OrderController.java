package controller;

import java.sql.Date;
import java.util.Vector;

import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;

public class OrderController {
	private static Vector<Order> orderList = new Vector<>();
	private static Integer orderID = null;

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
	
	public static Vector<Order> getActiveUserOrderList() {
		return orderList;
	}

	public static Integer calculateCurrentOrderTotal(Vector<OrderItem> orders) {
		Integer total = 0;
		for(OrderItem o: orders) {
			Double price = (o.getQuantity()) * o.getMenuItem().getMenuItemPrice();
			total += price.intValue();
		}
		
		return total;
	}
	
	public static void getPendingOrder() {
		orderList = OrderController.getOrdersByCustomerId(UserController.getCurrentUser().getUserId());
		for(Order o : orderList) {
			if(o.getOrderStatus().equalsIgnoreCase("null")) {
				orderID = o.getOrderId();
				break;
			}
		}
	}
	
	public static Integer getOrderID() {
		return orderID;
	}
	
	public static void setOrderID(Integer id) {
		orderID = id;
	}

}
