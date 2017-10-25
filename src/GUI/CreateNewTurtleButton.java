package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CreateNewTurtleButton extends Button {
	
	private GUIDelegate app;
	
	public CreateNewTurtleButton(GUIDelegate app) {
		this.app = app;
		this.setText("Add another turtle!!");
		this.setPrefWidth(200);
		this.setPrefHeight(200);
		this.setLayoutX(200);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.createTurtle());

	}


}
