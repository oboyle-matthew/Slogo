package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Creates a button to move forwards
 * 
 * @author Matt
 */
public class ForwardButton extends Button {
	
	public ForwardButton(GUIDelegate app) {
		this.setPrefWidth(100);
		this.setLayoutX(600);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.forwardButtonPressed());
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
}
