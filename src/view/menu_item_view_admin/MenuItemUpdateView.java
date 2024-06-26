package view.menu_item_view_admin;

import controller.MenuItemController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import view.menu_item_view_customer.MenuCustomerView;

public class MenuItemUpdateView extends BorderPane {
	//Halaman update menu item yang hanya dapat diakses admin. Dapat mengubah nama, deskripsi, dan quantity.
	private Button backBtn, submitBtn;
	private Label titleLbl;
	private GridPane promptPane;
	
	private Label idLbl, idTxt, nameLbl, descLbl, priceLbl, statusLbl;
	private TextField nameTxt, priceTxt;
	private TextArea descTxt;
	private HBox actionBtnContainer;

	public MenuItemUpdateView(model.MenuItem item) {
		if(!UserController.getCurrentUser().getUserRole().equals("Admin")) {
			Main.getMainPane().setCenter(new MenuCustomerView());
			return;
		}
		
		showTopComponent();
		this.setTop(titleLbl);
		BorderPane.setMargin(this, new Insets(20, 0, 50, 0));
		
		// Show Prompt to Add
		showPromptToAdd(item);
		this.setCenter(promptPane);
		BorderPane.setMargin(promptPane, new Insets(20, 0, 0, 50));
		
		showActionBtn();
		BorderPane.setMargin(actionBtnContainer, new Insets(20, 0, 0, 50));
		
	}
	
	public void showActionBtn() {
		submitBtn = new Button("Submit");
		submitBtn.setOnAction(event -> {
			String status = 
					MenuItemController.updateMenuItem(Integer.parseInt(idTxt.getText()),
							nameTxt.getText(), descTxt.getText(), priceTxt.getText());
			
			if(status == null) {
				statusLbl.setText("Success");
				statusLbl.setTextFill(Color.GREEN);
				
				submitBtn.setDisable(true);
				nameTxt.setDisable(true);
				descTxt.setDisable(true);
				priceTxt.setDisable(true);
			}
			else {
				statusLbl.setText(status);
				statusLbl.setTextFill(Color.RED);
			}
		});
		
		// Show Back Button
		backBtn = new Button("Cancel");
		backBtn.setOnAction(event -> {
			Main.getMainPane().setCenter(new MenuItemManagementView());
		});
		
		actionBtnContainer = new HBox();
		actionBtnContainer.getChildren().addAll(submitBtn, backBtn);
		HBox.setMargin(backBtn, new Insets(0, 0, 0, 10));
		this.setBottom(actionBtnContainer);
	}
	
	public void showPromptToAdd(model.MenuItem item) {
		promptPane = new GridPane();
		idLbl = new Label("Id");
		idLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		nameLbl = new Label("Name");
		nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		descLbl = new Label("Description");
		descLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		priceLbl = new Label("Price");
		priceLbl.setFont(Font.font("Arial", FontWeight.BOLD, BASELINE_OFFSET_SAME_AS_HEIGHT));
		
		idTxt = new Label(item.getMenuItemId().toString());
		nameTxt = new TextField();
		nameTxt.setText(item.getMenuItemName());
		descTxt = new TextArea();
		descTxt.setText(item.getMenuItemDescription());
		priceTxt = new TextField();
		priceTxt.setTextFormatter(new TextFormatter<>(formatter -> {
			if (formatter.getControlNewText().matches("\\d*")) {
				return formatter;
			} else {
				return null;
			}
		}));
		priceTxt.setText(String.valueOf(item.getMenuItemPrice().intValue()));
		
		promptPane.setAlignment(Pos.TOP_LEFT);
		promptPane.setVgap(10);
		promptPane.setHgap(30);
		
		promptPane.add(idLbl, 0, 0);
		promptPane.add(nameLbl, 0, 1);
		promptPane.add(descLbl, 0, 2);
		promptPane.add(priceLbl, 0, 3);
		
		promptPane.add(idTxt, 1, 0);
		promptPane.add(nameTxt, 1, 1);
		promptPane.add(descTxt, 1, 2);
		promptPane.add(priceTxt, 1, 3);
		
		statusLbl = new Label();
		statusLbl.setText("");
		promptPane.add(statusLbl, 1, 4);
	}
	
	public void showTopComponent() {
		// Show Title
		titleLbl = new Label("Update Menu Item");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		

	}

}
