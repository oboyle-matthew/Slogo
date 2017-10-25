package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CustomizeButton extends Button {
	
	private GUIDelegate app;
	
	public CustomizeButton(GUIDelegate app) {
		this.app = app;
		this.setText("Customize");
		this.setPrefWidth(100);
		this.setPrefHeight(50);
		this.setLayoutX(400);
		this.setLayoutY(450);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createNewCustomizeWindow());

	}
	
	private void createNewCustomizeWindow() {
		app.createCustomizeWindow();
	}


}
