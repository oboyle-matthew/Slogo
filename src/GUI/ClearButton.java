package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ClearButton extends Button {
	
	private GUIDelegate app;
	
	public ClearButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(50);
		this.setText("Clear");
		this.setLayoutX(600);
		this.setLayoutY(550);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->clearText());

	}
	
	private void clearText() {
		app.clearButtonPressed();
	}
	
}
