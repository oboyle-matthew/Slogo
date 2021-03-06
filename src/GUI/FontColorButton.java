package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Creates a button to change the font color
 * 
 * @author Matt
 */
public class FontColorButton extends ComboBox<String> {
	
	private static final int Y_POS = 200;
	private static final int X_POS = 200;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Choose a font color";
	private static final String[] COLORS = {"white", "blue", "orange", "yellow", "green", "purple", "grey", "red"};
	
	public FontColorButton(GUIDelegate app) {
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		ObservableList<String> colorList = FXCollections.observableArrayList(COLORS);
		ChangeListener<String> propertyHandler = (obs, old, cur) -> app.changeFontColor(colorList.indexOf(cur));
		this.getSelectionModel().selectedItemProperty().addListener(propertyHandler);
		this.setPromptText(PROMPT_TEXT);
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
}
