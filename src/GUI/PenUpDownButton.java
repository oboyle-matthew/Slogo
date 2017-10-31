package GUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Creates a toggle to put the pen up/down
 * 
 * @author Matt and Tony
 */
public class PenUpDownButton extends ToggleSwitch{
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
	
	
	

