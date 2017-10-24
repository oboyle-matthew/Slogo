package GUI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class InputBox extends TextArea {
	
	private GUIDelegate app;
	
	public InputBox(GUIDelegate app) {
		this.app = app;
		this.setWidth(200);
		this.setHeight(100);
		this.setLayoutY(400);
	}

	public String readText() {
		return this.getText();
	}
	
	public void clearText() {
		this.clear();
	}
	
}
