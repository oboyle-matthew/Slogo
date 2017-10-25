package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class InstructionsButton extends Button {
	
	private GUIDelegate app;
	
	public InstructionsButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(30);
		this.setPrefHeight(30);
		//this.setText("Instructions");
		this.setLayoutX(50);
		
		//Deal with image
		Image image = new Image(getClass().getResourceAsStream("infoButton.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createInstructionsWindow());

	}
	
	private void createInstructionsWindow() {
		app.createInstructionsWindow();
	}
	
}
