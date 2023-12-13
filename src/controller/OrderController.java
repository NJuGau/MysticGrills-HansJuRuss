package controller;

import java.sql.Date;
import java.util.Vector;

import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;

public class OrderController {
	//static variable untuk menyimpan order yang sedang diakses
	private static Integer orderID = null;

	//CREATE method
	public static Integer createOrder(User orderUser, Vector<OrderItem> orderItems, Date orderDate) {
		
		return Order.createOrder(orderUser, orderItems, orderDate);
	}
	
	//UPDATE method
	public static String updateOrder(String orderId, Vector<OrderItem> orderItems, String orderStatus) {
		if(orderId.isEmpty()) {
			return "Order Id must be chosen";
		}
		
		return Order.updateOrder(Integer.parseInt(orderId), orderItems, orderStatus);
	}
	
	//DELETE method
	public static String deleteOrder(Integer orderId) {
		
		return Order.deleteOrder(orderId);
	}
	
	//SELECT by Id method
	public static Vector<Order> getOrdersByCustomerId(Integer customerId) {
		
		return Order.getOrdersByCustomerId(customerId);
	}
	
	//SELECT all method
	public static Vector<Order> getAllOrders() {
		
		return Order.getAllOrders();
	}
	
	//SELECT by order Id method
	public static Order getOrderByOrderId(Integer orderId) {
		
		return Order.getOrderByOrderId(orderId);
	}
	
	//method tambahan untuk menghitung order total setiap order item diedit / dihapus
	public static Integer calculateCurrentOrderTotal(Vector<OrderItem> orders) {
		Integer total = 0;
		for(OrderItem o: orders) {
			Double price = (o.getQuantity()) * o.getMenuItem().getMenuItemPrice();
			total += price.intValue();
		}
		
		return total;
	}
	
	//method tambahan untuk hanya melakukan SELECT pada order berstatus 'null'. Order berstatus 'null' adalah order yang belum disubmit ke chef. Artinya customer masih dapat menambahkan menu item ke dalam order item. 
	public static void getPendingOrder() {
		Vector<Order> orderList = OrderController.getOrdersByCustomerId(UserController.getCurrentUser().getUserId());
		for(Order o : orderList) {
			if(o.getOrderStatus().equalsIgnoreCase("null")) {
				orderID = o.getOrderId();
				return;
			}
		}
		orderID = null;
	}
	
	public static Integer getOrderID() {
		return orderID;
	}
	
	public static void setOrderID(Integer id) {
		orderID = id;
	}
	
	//method untuk melakukan SELECT order berdasarkan statusnya
	public static Vector<Order> getOrderByStatus(String status){
		Vector<Order> allOrder = OrderController.getAllOrders();
		Vector<Order> orders = new Vector<>();
		for(Order o: allOrder) {
			if(o.getOrderStatus().equals(status)) {
				orders.add(o);
			}
		}
		return orders;
	}

}
