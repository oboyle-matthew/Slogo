package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CreateNewTurtleButton extends Button {
	
	private static final int Y_POS = 400;
	private static final int X_POS = 200;
	private static final int SIZE = 30;
	private static final String IMAGE_DIR = "turtleButton.png";

	public CreateNewTurtleButton(GUIDelegate app) {
		this.setPrefWidth(SIZE);
		this.setPrefHeight(SIZE);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.createTurtle());
		Image image = new Image(getClass().getResourceAsStream(IMAGE_DIR));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
}
