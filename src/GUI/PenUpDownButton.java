package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class PenUpDownButton extends ComboBox<String> {
	
	private static final int Y_POS = 200;
	private static final int X_POS = 200;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Pen up or down";
	private static final String[] OPTIONS = {"Pen Up", "Pen Down"};
	private GUIDelegate app;
	
	public PenUpDownButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(WIDTH);
		this.setLayoutX(X_POS);
		this.setLayoutY(Y_POS);
		ObservableList<String> colorList = FXCollections.observableArrayList(OPTIONS);
		ChangeListener<String> propertyHandler = (obs, old, cur) -> changePenUpDown(colorList.indexOf(cur));
		this.getSelectionModel().selectedItemProperty().addListener(propertyHandler);
		this.setPromptText(PROMPT_TEXT);
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
	
	public void changePenUpDown(int index) {
		if (index == 0) app.penUp();
		if (index == 1) app.penDown();
	}
}
