package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BackgroundColorButton extends ComboBox<String> {
	
	private GUIDelegate app;
	
	public BackgroundColorButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(200);
		this.setLayoutX(100);
		this.setLayoutY(50);
		ArrayList<String> colors = new ArrayList<>(Arrays.asList("red", "orange", "green", "blue", "yellow"));
		ObservableList<String> colorList = FXCollections.observableArrayList(colors);
		this.setPromptText("Choose a background color");
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
	
	public void changeBackgroundColor(String color) {
		app.changeBackground(color);
	}
}
