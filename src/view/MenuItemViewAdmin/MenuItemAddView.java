package view.MenuItemViewAdmin;

import controller.MenuItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import main.Main;
import view.MenuItemViewCustomer.MenuCustomerView;

public class MenuItemAddView extends BorderPane {
	private Button backBtn, submitBtn;
	private Label titleLbl;
	private GridPane promptPane;
	
	private Label nameLbl, descLbl, priceLbl, statusLbl;
	private TextField nameTxt, priceTxt;
	private TextArea descTxt;
	private HBox actionBtnContainer;

	public MenuItemAddView() {
		if(Main.getCurrentUser().getUserRole() != "admin") {
			Main.getMainPane().setCenter(new MenuCustomerView());
		}
		
		showTopComponent();
		this.setTop(titleLbl);
		BorderPane.setMargin(this, new Insets(20, 0, 50, 0));
		
		// Show Prompt to Add
		showPromptToAdd();
		this.setCenter(promptPane);
		BorderPane.setMargin(promptPane, new Insets(20, 0, 0, 50));
		
		showActionBtn();
		BorderPane.setMargin(actionBtnContainer, new Insets(0, 0, 0, 50));
		
	}
	
	public void showActionBtn() {
//		TODO: Add Logic to submit MenuItem
		submitBtn = new Button("Submit");
		submitBtn.setOnAction(event -> {
			String status = 
					MenuItemController.createMenuItem(
							nameTxt.getText(), descTxt.getText(), priceTxt.getText());
			
			if(status == null) {
				// TODO add Add Menu Item SQL Logic
				statusLbl.setText("Success");
				statusLbl.setTextFill(Color.GREEN);
				
				nameTxt.setText("");
				descTxt.setText("");
				priceTxt.setText("");
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
	
	public void showPromptToAdd() {
		promptPane = new GridPane();
		nameLbl = new Label("Name");
		descLbl = new Label("Description");
		priceLbl = new Label("Price");
		
		nameTxt = new TextField();
		descTxt = new TextArea();
		priceTxt = new TextField();
		
		promptPane.setAlignment(Pos.TOP_LEFT);
		promptPane.setVgap(10);
		promptPane.setHgap(30);
		
		promptPane.add(nameLbl, 0, 0);
		promptPane.add(descLbl, 0, 1);
		promptPane.add(priceLbl, 0, 2);
		promptPane.add(nameTxt, 1, 0);
		promptPane.add(descTxt, 1, 1);
		promptPane.add(priceTxt, 1, 2);
		
		statusLbl = new Label();
		statusLbl.setText("");
		promptPane.add(statusLbl, 1, 3);
		
	}
	
	public void showTopComponent() {
		// Show Title
		titleLbl = new Label("Add Menu Item");
		titleLbl.setFont(Font.font("Open Sans", FontWeight.BLACK, FontPosture.REGULAR, 24));
		this.setTop(titleLbl);
		BorderPane.setAlignment(titleLbl, Pos.TOP_CENTER);
		BorderPane.setMargin(titleLbl, new Insets(20, 0, 20, 0));
		

	}

}
