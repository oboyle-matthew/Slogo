package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ClearButton extends Button {
	
	private GUIDelegate app;
	
	public ClearButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(30);
		this.setPrefHeight(30);
		//this.setText("Clear");
		this.setLayoutX(600);
		this.setLayoutY(550);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->clearText());
		
		Image image = new Image(getClass().getResourceAsStream("clearButton.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");


	}
	
	private void clearText() {
		app.clearButtonPressed();
	}
	
}
