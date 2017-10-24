package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class InstructionsButton extends Button {
	
	private GUIDelegate app;
	
	public InstructionsButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(50);
		this.setText("Instructions");
		this.setLayoutX(50);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createInstructionsWindow());

	}
	
	private void createInstructionsWindow() {
		app.createInstructionsWindow();
	}
	
}
