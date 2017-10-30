package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class FontColorButton extends ComboBox<String> {
	
	private static final int Y_POS = 200;
	private static final int X_POS = 200;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Choose a font color";
	private static final String[] COLORS = {"Black", "Red", "Yellow", "Green"};
	
	public FontColorButton(GUIDelegate app) {
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		ObservableList<String> colorList = FXCollections.observableArrayList(COLORS);
		this.setPromptText(PROMPT_TEXT);
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
	
	public void changeFontColor(String color) {
		System.out.println("Font Test");
	}
}
