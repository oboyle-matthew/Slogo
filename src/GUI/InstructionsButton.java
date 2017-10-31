package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class InstructionsButton extends Button {
		
	private static final String INFO_BUTTON_IMAGE = "infoButton.png";
	private static final int X_POS = 50;
	private static final int SIZE = 30;

	public InstructionsButton(GUIDelegate app) {
		this.setPrefWidth(SIZE);
		this.setPrefHeight(SIZE);
		this.setLayoutX(X_POS);
		
		//Deal with image
		Image image = new Image(getClass().getResourceAsStream(INFO_BUTTON_IMAGE));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.createInstructionsWindow());
	}
}
