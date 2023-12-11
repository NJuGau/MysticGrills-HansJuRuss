package view.menu_item_view_customer;

import java.sql.Date;
import java.util.Calendar;

import controller.MenuItemController;
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
import view.order_management.OrderChangeView;
import view.order_view.OrderListView;

public class MenuDetailsCustomerView extends BorderPane{

	private Button backBtn, submitBtn;
	private Label titleLbl;
	private GridPane promptPane;
	
	private Label idLbl, idTxt, nameLbl, descLbl, priceLbl, statusLbl, nameTxt, priceTxt, descTxt, qtyLbl;
	private Spinner<Integer> qtySpinner;
	private HBox actionBtnContainer;
	
	private model.MenuItem item;

	public MenuDetailsCustomerView(model.MenuItem item) {
		this.item = item;
		System.out.println(OrderController.getOrderID());
		
		showTopComponent();
		this.setTop(titleLbl);
		BorderPane.setMargin(this, new Insets(20, 0, 50, 0));
		
		// Show Prompt to Add
		showPromptToAdd();
		this.setCenter(promptPane);
		BorderPane.setMargin(promptPane, new Insets(20, 0, 0, 50));
		
		showActionBtn();
		BorderPane.setMargin(actionBtnContainer, new Insets(20, 0, 0, 50));
		
	}
	
	public void showActionBtn() {
//		TODO: Add Logic to submit MenuItem
		submitBtn = new Button("Add");
		submitBtn.setMinWidth(100);
		submitBtn.setOnAction(event -> {
			
			// If orderID is empty and user click add to orderlist
			// then create new order row in mysql
			// should return orderID from newly created orderID in SQL
			if(OrderController.getOrderID() == null) {
				OrderController.setOrderID(OrderController.createOrder(
						UserController.getCurrentUser(), null, new Date(Calendar.getInstance().getTimeInMillis())));
				// TODO DELETE this for testing only
				System.out.println(OrderController.getOrderID());
			}
			
			// Add menu item that is bought into database
			String status = OrderItemController.createOrderItem(
					OrderController.getOrderID().toString(), item, qtySpinner.getValue().toString());
			
			if(status == null) {
				statusLbl.setText("Success");
				statusLbl.setTextFill(Color.GREEN);
				
				submitBtn.setDisable(true);
				nameTxt.setDisable(true);
				descTxt.setDisable(true);
				priceTxt.setDisable(true);
				qtySpinner.setDisable(true);
			}
			else {
				statusLbl.setText(status);
				statusLbl.setTextFill(Color.RED);
			}
		});
		
		// Show Back Button
		backBtn = new Button("Back");
		backBtn.setOnAction(event -> {
			Main.getMainPane().setCenter(new MenuCustomerView());
		});
		
		Label confirmLbl = new Label("Add order item: ");
		HBox.setMargin(confirmLbl, new Insets(0, 10, 0, 0));
		
		actionBtnContainer = new HBox();
		actionBtnContainer.getChildren().addAll(confirmLbl, submitBtn, backBtn);
		HBox.setMargin(backBtn, new Insets(0, 0, 0, 10));
		actionBtnContainer.setAlignment(Pos.CENTER_LEFT);
		this.setBottom(actionBtnContainer);
	}
	
	public void showPromptToAdd() {
		promptPane = new GridPane();
		idLbl = new Label("Id");
		idLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl = new Label("Name");
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		descLbl = new Label("Description");
		descLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		priceLbl = new Label("Price");
		priceLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		qtyLbl = new Label("Quantity");
		qtyLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		idTxt = new Label(item.getMenuItemId().toString());
		nameTxt = new Label();
		nameTxt.setText(item.getMenuItemName());
		descTxt = new Label();
		descTxt.setText(item.getMenuItemDescription());
		priceTxt = new Label();
		priceTxt.setText(item.getMenuItemPrice().toString());
		qtySpinner = new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		
		promptPane.setAlignment(Pos.TOP_LEFT);
		promptPane.setVgap(10);
		promptPane.setHgap(30);
		
		promptPane.add(idLbl, 0, 0);
		promptPane.add(nameLbl, 0, 1);
		promptPane.add(descLbl, 0, 2);
		promptPane.add(priceLbl, 0, 3);
		promptPane.add(qtyLbl, 0, 4);
		
		promptPane.add(idTxt, 1, 0);
		promptPane.add(nameTxt, 1, 1);
		promptPane.add(descTxt, 1, 2);
		promptPane.add(priceTxt, 1, 3);
		promptPane.add(qtySpinner, 1, 4);
		
		statusLbl = new Label();
		statusLbl.setText("");
		promptPane.add(statusLbl, 1, 5);
	}
	
	public void showTopComponent() {
		// Show Title
		titleLbl = new Label("Menu Details");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		

	}

}
