package controller;

import java.util.Vector;

import model.Order;
import model.OrderItem;

public class OrderItemController {

	public static String createOrderItem(String orderId, model.MenuItem menuItem, String quantity) {
		
		if(menuItem == null) {
			return "Menu Item must be chosen";
		}
		
		if(quantity.isEmpty()) {
			return "Quantity can't be empty";
		}
		
		if(Integer.parseInt(quantity) < 1) {
			return "Quantity cannot below 1";
		}
		
		if(orderId.isEmpty()) {
			return "Order ID can't be empty";
		}
		
		// If menuitem has been added to cart, update the quantity
		Vector<OrderItem> orderItem = OrderItem.getAllOrderItemsByOrderId(Integer.parseInt(orderId));
		for(OrderItem items: orderItem) {
			if(items.getMenuItem().getMenuItemId().equals(menuItem.getMenuItemId())) {
				Integer updatedQuantity = Integer.parseInt(quantity) + items.getQuantity();
//				Integer updatedTotal = (int) (updatedQuantity * items.getMenuItem().getMenuItemPrice());
				
				OrderItem.updateOrderItem(Integer.parseInt(orderId), menuItem, updatedQuantity);
				Order toUpdateOrder = Order.getOrderByOrderId(Integer.parseInt(orderId));
				return Order.updateOrder(Integer.parseInt(orderId), toUpdateOrder.getOrderItems(), toUpdateOrder.getOrderStatus());
			}
		}
		
		OrderItem.createOrderItem(Integer.parseInt(orderId), menuItem, Integer.parseInt(quantity));
		Order toUpdateOrder = Order.getOrderByOrderId(Integer.parseInt(orderId));
		return Order.updateOrder(Integer.parseInt(orderId), toUpdateOrder.getOrderItems(), toUpdateOrder.getOrderStatus());
	}
	
	public static String updateOrderItem(String orderId, model.MenuItem menuItem, String quantity) {
		if(menuItem == null) {
			return "Menu Item must be chosen";
		}
		
		if(quantity.isEmpty()) {
			return "Quantity can't be empty";
		}
		
		if(Integer.parseInt(quantity) < 0) {
			return "Quantity cannot be below 0";
		}
		
		if(orderId.isEmpty()) {
			return "Order ID can't be empty";
		}
		
		OrderItem.updateOrderItem(Integer.parseInt(orderId), menuItem, Integer.parseInt(quantity));
		Order toUpdateOrder = Order.getOrderByOrderId(Integer.parseInt(orderId));
		return Order.updateOrder(Integer.parseInt(orderId), toUpdateOrder.getOrderItems(), toUpdateOrder.getOrderStatus());
	}
	
	public static String deleteOrderItem(String orderId, String menuItemId) {
		if(orderId.isEmpty()) {
			return "Order ID can't be empty";
		}
		
		if(menuItemId.isEmpty()) {
			return "Menu Item ID can't be empty";
		}
		
		OrderItem.deleteOrderItem(Integer.parseInt(orderId), Integer.parseInt(menuItemId));
		Order toUpdateOrder = Order.getOrderByOrderId(Integer.parseInt(orderId));
		return Order.updateOrder(Integer.parseInt(orderId), toUpdateOrder.getOrderItems(), toUpdateOrder.getOrderStatus());
	}
	
	public static Vector<OrderItem> getAllOrderItemsByOrderId(String orderId) {
		
		if(orderId.isEmpty()) {
			return null;
		}
		
		return OrderItem.getAllOrderItemsByOrderId(Integer.parseInt(orderId));
	}

}
