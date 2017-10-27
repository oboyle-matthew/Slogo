package GUI;

import javafx.scene.control.TextArea;

public class InputBox extends TextArea {
	
//	private GUIDelegate app;
	
	public InputBox(GUIDelegate app) {
//		this.app = app;

		this.setLayoutY(410);
		this.setLayoutX(210);
		this.setPromptText("Input some command ……");
	}

	public String readText() {
		return this.getText();
	}
	
	public void clearText() {
		this.clear();
	}
	
}
