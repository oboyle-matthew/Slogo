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
		this.setStyle("-fx-font-family: Andale Mono");
		this.setStyle("-fx-padding: 6");
		this.setStyle("-fx-font-size: 1em");
		this.setTextFill(Color.WHITE);
		this.setStyle("-fx-background-color: #001A57;");
		this.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createInstructionsWindow());

	}
	
	private void createInstructionsWindow() {
		app.createInstructionsWindow();
	}
	
}
