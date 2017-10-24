package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class NewProjectButton extends Button {
	
	private GUIDelegate app;
	
	public NewProjectButton(GUIDelegate app) {
		this.app = app;
		this.setText("+");
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createNewProject());

	}
	
	private void createNewProject() {
		app.createNewProject();
	}
	
}
