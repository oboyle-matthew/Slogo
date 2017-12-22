package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CreateNewTurtleButton extends Button {
	
	private GUIDelegate app;
	
	public CreateNewTurtleButton(GUIDelegate app) {
		this.app = app;
		//this.setText("Add another turtle!!");
		this.setPrefWidth(30);
		this.setPrefHeight(30);
		this.setLayoutX(200);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.createTurtle());
		
		Image image = new Image(getClass().getResourceAsStream("turtleButton.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}


}
