package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Creates a button to clear the textInputBox
 * 
 * @author Matt
 */
public class ClearButton extends Button {
	
	private static final String CLEAR_BUTTON_IMAGE = "clearButton.png";
	private static final int Y_POS = 550;
	private static final int X_POS = 600;
	private static final int SIZE = 30;
	
	public ClearButton(GUIDelegate app) {
		this.setPrefWidth(SIZE);
		this.setPrefHeight(SIZE);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.clearButtonPressed());

		Image image = new Image(getClass().getResourceAsStream(CLEAR_BUTTON_IMAGE));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
}
