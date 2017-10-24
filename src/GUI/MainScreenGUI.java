package GUI;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainScreenGUI {
	// private TextArea commandField;
	private TextInputBox textBox;
	
	

	public MainScreenGUI() {
		/*
		 * commandField = new TextArea(); commandField.setPrefColumnCount(15);
		 * commandField.setPromptText("Enter your command....");
		 * commandField.setPrefWidth(400); commandField.setPrefHeight(100);
		 * commandField.setLayoutX(200); commandField.setLayoutY(450);
		 */
		textBox = new TextInputBox();
		
		
	}
	
	
	
	public TextInputBox getTextBox() {
		return textBox;
	}
	
//	public HistoryBox getHistoryBox() {
//		return textBox.getHistoryBox();
//	}
	

}