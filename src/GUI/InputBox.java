package GUI;

import javafx.scene.control.TextArea;

public class InputBox extends TextArea {
	
//	private GUIDelegate app;
	
	public InputBox(GUIDelegate app) {
//		this.app = app;
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
