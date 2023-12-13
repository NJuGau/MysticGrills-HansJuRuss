package view.order_view;

import java.sql.Date;
import java.util.Calendar;

import controller.OrderController;
import controller.OrderItemController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import model.OrderItem;
import view.menu_item_view_customer.MenuCustomerView;
import view.order_management.OrderChangeView;

public class OrderItemsDeleteView extends BorderPane{
	//Halaman order item delete confirmation. User dapat melakukan konfirmasi deletion order item yang telah dipilih dalam halaman detail.
	private Label titleLbl, statusLbl;
	private Button submitBtn, backBtn;
	private Label orderIdLbl, orderIdTxt, menuItemNameLbl, menuItemNameTxt, 
					menuItemDescLbl, menuItemDescTxt, menuItemPriceLbl, menuItemPriceTxt,
					quantityLbl, quantityTxt;
	private GridPane promptPane;
	private HBox actionBtnContainer;
	
	private OrderItem orderItem;

	public OrderItemsDeleteView(OrderItem orderItem) {
		this.orderItem = orderItem;
		
		showTitle();
		showData();
		showActionBtn();
	}
	
	private void showData() {
		orderIdLbl = new Label("Order ID: ");
		orderIdLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		menuItemNameLbl = new Label("Menu item name: ");
		menuItemNameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		menuItemDescLbl = new Label("Menu item Description: ");
		menuItemDescLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		menuItemPriceLbl = new Label("Menu item Price: ");
		menuItemPriceLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		quantityLbl = new Label("Buy Quantity: ");
		quantityLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		statusLbl = new Label();
		
		orderIdTxt = new Label(orderItem.getOrderId().toString());
		menuItemNameTxt = new Label(orderItem.getMenuItem().getMenuItemName());
		menuItemDescTxt = new Label(orderItem.getMenuItem().getMenuItemDescription());
		menuItemPriceTxt = new Label(orderItem.getMenuItem().getMenuItemPrice().toString());
		quantityTxt = new Label(orderItem.getQuantity().toString());
		
		promptPane = new GridPane();
		promptPane.setHgap(40);
		promptPane.setVgap(10);
		promptPane.add(orderIdLbl, 0, 0);
		promptPane.add(menuItemNameLbl, 0, 1);
		promptPane.add(menuItemDescLbl, 0, 2);
		promptPane.add(menuItemPriceLbl, 0, 3);
		promptPane.add(quantityLbl, 0, 4);
		promptPane.add(statusLbl, 0, 5);
		
		promptPane.add(orderIdTxt, 1, 0);
		promptPane.add(menuItemNameTxt, 1, 1);
		promptPane.add(menuItemDescTxt, 1, 2);
		promptPane.add(menuItemPriceTxt, 1, 3);
		promptPane.add(quantityTxt, 1, 4);
		
		BorderPane.setMargin(promptPane, new Insets(20, 0, 20, 50));
		this.setCenter(promptPane);
	}
	
	
	private void showActionBtn() {
//		TODO: Add Logic to delete order
		submitBtn = new Button("Delete");
		submitBtn.setMinWidth(100);
		submitBtn.setOnAction(event -> {
			
			String status = OrderItemController.deleteOrderItem(orderItem.getOrderId().toString(), orderItem.getMenuItem().getMenuItemId().toString());
			
			if(status == null) {
				submitBtn.setDisable(true);
				statusLbl.setText("Success");
				statusLbl.setTextFill(Color.GREEN);
			}
			else {
				statusLbl.setText(status);
				statusLbl.setTextFill(Color.RED);
			}
		});
		
		// Show Back Button
		backBtn = new Button("Back");
		backBtn.setOnAction(event -> {
			if(UserController.getCurrentUser().getUserRole().equals("Chef") || UserController.getCurrentUser().getUserRole().equals("Waiter")) {
				Main.getMainPane().setCenter(new OrderChangeView());
			}
			else {
				Main.getMainPane().setCenter(new OrderListView());	
			}
		});
		
		Label confirmLbl = new Label("Confirm Delete order item: ");
		HBox.setMargin(confirmLbl, new Insets(0, 10, 0, 0));
		
		actionBtnContainer = new HBox();
		actionBtnContainer.getChildren().addAll(confirmLbl, submitBtn, backBtn);
		HBox.setMargin(backBtn, new Insets(0, 0, 0, 10));
		actionBtnContainer.setAlignment(Pos.CENTER_LEFT);
		BorderPane.setMargin(actionBtnContainer, new Insets(0, 0, 60, 50));
		this.setBottom(actionBtnContainer);
	}
	
	private void showTitle() {
		titleLbl = new Label("Order Item Delete Confirmation");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
	}

}
