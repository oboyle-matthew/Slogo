package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class BackgroundColorButton extends ComboBox<String> {
	
	private static final int Y_POS = 50;
	private static final int X_POS = 100;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Choose a background color";
	private static final String[] COLORS = {"red", "orange", "green", "blue", "yellow"};
	
	public BackgroundColorButton(GUIDelegate app) {
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		this.setPromptText(PROMPT_TEXT);
		ObservableList<String> colorList = FXCollections.observableArrayList(COLORS);
		ChangeListener<String> propertyHandler = (obs, old, cur) -> app.changeBackground(cur);
		this.getSelectionModel().selectedItemProperty().addListener(propertyHandler);
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
}
