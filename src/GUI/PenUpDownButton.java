package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class PenUpDownButton extends ToggleSwitch{
	
	/*private static final int Y_POS = 200;
	private static final int X_POS = 200;
	private static final int WIDTH = 200;
	private static final String PROMPT_TEXT = "Pen up or down";
	private static final String[] OPTIONS = {"Pen Up", "Pen Down"};*/
	
	
	private GUIDelegate app;
	
	public PenUpDownButton(GUIDelegate app) {
		this.app = app;
		this.setUpSwitch();
	}
	
	private void setUpSwitch() {
		switchedOn.addListener((a,b,c) -> {
			if (c) {
                penUp();
            }
			else {
            	penDown();
            }
		});
	}

	public void penDown() {
		switchLabel.setText("UP");
		setStyle("-fx-background-color: grey;");
		button.toFront();
		app.changePenStatus(false);
	}

	public void penUp() {
		switchLabel.setText("DOWN");
		setStyle("-fx-background-color: green;");
		switchLabel.toFront();
		app.changePenStatus(true);
	}
	}
	
	
	

