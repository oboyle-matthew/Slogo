package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Creates a button to change the font size (method functions properly but 
 * font size not working on back-end
 * 
 * @author Matt
 */
public class FontSizeButton extends ComboBox<Integer> {
	
	private static final int Y_POS = 300;
	private static final int X_POS = 100;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Choose a pen size";
	private static final Integer[] COLORS = {1,2,3,4,5,6,7,8,9,10};
	
	public FontSizeButton(GUIDelegate app) {
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.setPromptText(PROMPT_TEXT);
		ObservableList<Integer> colorList = FXCollections.observableArrayList(COLORS);
		ChangeListener<? super Integer> propertyHandler = (obs, old, cur) -> app.changeFontWidth(cur);
		this.getSelectionModel().selectedItemProperty().addListener(propertyHandler);
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
}
