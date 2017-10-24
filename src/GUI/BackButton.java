package GUI;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class BackButton extends Button {
	
//	private GUIDelegate app;
	
	public BackButton(GUIDelegate app) {
//		this.app = app;
		this.setPrefWidth(100);
		this.setText("Back");
		this.setLayoutX(600);
		this.setLayoutY(450);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.runCommand("back 50"));
	}
	
}
