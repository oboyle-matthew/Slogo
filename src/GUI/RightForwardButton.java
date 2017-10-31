package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class RightForwardButton extends Button {
	
	private static final int Y_POS = 450;
	private static final int X_POS = 600;
	private static final int WIDTH = 100;

	public RightForwardButton(GUIDelegate app) {
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.rightForwardButtonPressed());
		this.setStyle("-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
}