package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RotateRightButton extends Button {
	
//	private GUIDelegate app;
	
	public RotateRightButton(GUIDelegate app) {
//		this.app = app;
		this.setPrefWidth(100);
		this.setLayoutX(600);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.rotateRightButtonPressed());
		
		//customize the image
//		Image image = new Image(getClass().getResourceAsStream("upButton.png"));
//		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
	
}
