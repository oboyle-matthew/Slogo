package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BackButton extends Button {
	
//	private GUIDelegate app;
	
	public BackButton(GUIDelegate app) {
//		this.app = app;
		this.setPrefWidth(100);
		//this.setText("Back");
		this.setLayoutX(600);
		this.setLayoutY(450);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.runCommand("back 50"));
		
		//deal with background
		Image image = new Image(getClass().getResourceAsStream("downButton.png"));
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
	
}
