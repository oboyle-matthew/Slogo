package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ForwardButton extends Button {
	
//	private GUIDelegate app;
	
	public ForwardButton(GUIDelegate app) {
//		this.app = app;
		this.setPrefWidth(100);
		this.setText("Forward");
		this.setLayoutX(600);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.runCommand("fd 50"));
	}
	
}
