package controller;

import java.util.Vector;

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
				return OrderItem.updateOrderItem(Integer.parseInt(orderId), menuItem, updatedQuantity);
			}
		}
		
		return OrderItem.createOrderItem(Integer.parseInt(orderId), menuItem, Integer.parseInt(quantity));
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
		
		return OrderItem.updateOrderItem(Integer.parseInt(orderId), menuItem, Integer.parseInt(quantity));
	}
	
	public static String deleteOrderItem(String orderId, String menuItemId) {
		if(orderId.isEmpty()) {
			return "Order ID can't be empty";
		}
		
		if(menuItemId.isEmpty()) {
			return "Menu Item ID can't be empty";
		}
		
		return OrderItem.deleteOrderItem(Integer.parseInt(orderId), Integer.parseInt(menuItemId));
	}
	
	public static Vector<OrderItem> getAllOrderItemsByOrderId(String orderId) {
		
		if(orderId.isEmpty()) {
			return null;
		}
		
		return OrderItem.getAllOrderItemsByOrderId(Integer.parseInt(orderId));
	}

}
