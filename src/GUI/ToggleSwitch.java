package GUI;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ToggleSwitch extends HBox {
	
	private final Label	switchLabel = new Label();
	private final Button button = new Button();
	
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	
	private void init() {
		
		switchLabel.setText("OFF");
		
		getChildren().addAll(switchLabel, button);	
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		switchLabel.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		setStyle();
		bindProperties();
	}
	
	private void setStyle() {
		//Default Width
		setWidth(80);
		switchLabel.setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
		setAlignment(Pos.CENTER_LEFT);
	}
	
	private void bindProperties() {
		switchLabel.prefWidthProperty().bind(widthProperty().divide(2));
		switchLabel.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}
	
	public ToggleSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
                		switchLabel.setText("ON");
                		setStyle("-fx-background-color: green;");
                		switchLabel.toFront();
                		
            		}
            		else {
            			switchLabel.setText("OFF");
        			setStyle("-fx-background-color: grey;");
                		button.toFront();
                		
            		}
		});
	}

	
}