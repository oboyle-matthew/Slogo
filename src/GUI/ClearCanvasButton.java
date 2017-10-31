package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ClearCanvasButton extends Button {
	
	private GUIDelegate app;
	
	public ClearCanvasButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(50);
		this.setPrefHeight(50);
		//this.setText("Run");
		this.setLayoutX(600);
		this.setLayoutY(500);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.clearCanvas());
		
		Image image = new Image(getClass().getResourceAsStream("play-button.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
	
	private void readText() {
		app.runButtonPressed();
	}
	
}
