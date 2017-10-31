package GUI;

import javafx.scene.control.TextArea;

/**
 * Creates a TextArea for users to enter commands
 * 
 * @author Matt
 */
public class InputBox extends TextArea {
		
	private static final String PROMPT_TEXT = "Input some command ……";
	private static final int X_POS = 210;
	private static final int Y_POS = 410;

	public InputBox(GUIDelegate app) {
		this.setLayoutY(Y_POS);
		this.setLayoutX(X_POS);
		this.setPromptText(PROMPT_TEXT);
	}

	public String readText() {
		return this.getText();
	}
	
	public void clearText() {
		this.clear();
	}
	
}
