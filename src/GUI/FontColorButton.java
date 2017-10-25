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

public class FontColorButton extends ComboBox<String> {
	
	private GUIDelegate app;
	
	public FontColorButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(200);
		this.setLayoutX(200);
		this.setLayoutY(200);
		ArrayList<String> colors = new ArrayList<>(Arrays.asList("Black", "Red", "Yellow", "Green"));
		ObservableList<String> colorList = FXCollections.observableArrayList(colors);
		this.setPromptText("Choose a font color");
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
	
	public void changeBackgroundColor(String color) {
		System.out.println("Font Test");
	}
}
