package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CustomizeButton extends Button {
	
	private GUIDelegate app;
	
	public CustomizeButton(GUIDelegate app) {
		this.app = app;
		this.setText(" (Custom)");
		this.setPrefWidth(100);
		this.setPrefHeight(30);
		this.setLayoutX(400);
		this.setLayoutY(450);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createNewCustomizeWindow());

		Image image = new Image(getClass().getResourceAsStream("CustomizeButton.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
	
	private void createNewCustomizeWindow() {
		app.createCustomizeWindow();
	}


}
