package GUI;

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
	
	public HistoryBox getHistoryBox() {
		return textBox.getHistoryBox();
	}
	

}