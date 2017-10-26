package startScreen;

import GUI.GUIDelegate;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LanguageSelectButton extends Button {
	
	private GUIDelegate app;
	
	public LanguageSelectButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(50);
		this.setText("Clear");
		this.setLayoutX(600);
		this.setLayoutY(550);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->startProject());

	}
	
	private void startProject() {
		app.startProject();
	}
	
}
