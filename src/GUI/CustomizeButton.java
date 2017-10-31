package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Creates a customizable button to bring up new customize window
 * 
 * @author Matt
 */
public class CustomizeButton extends Button {
	
	private static final String IMAGE_DIR = "CustomizeButton.png";
	private static final int Y_POS = 450;
	private static final int X_POS = 400;
	private static final int HEIGHT = 30;
	private static final int WIDTH = 100;
	private static final String BUTTON_TEXT = " (Custom)";
	
	public CustomizeButton(GUIDelegate app) {
		this.setText(BUTTON_TEXT);
		this.setPrefWidth(WIDTH);
		this.setPrefHeight(HEIGHT);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.createCustomizeWindow());

		Image image = new Image(getClass().getResourceAsStream(IMAGE_DIR));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}

}
