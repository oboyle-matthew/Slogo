package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RotateLeftButton extends Button {
	
//	private GUIDelegate app;
	private final String ROTATE_LEFT_IMAGE = "turn-left.png";
	
	public RotateLeftButton(GUIDelegate app) {
//		this.app = app;
		this.setPrefWidth(100);
		this.setLayoutX(600);
		this.setLayoutY(400);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.rotateLeftButtonPressed());
		
		//customize the image
		Image image = new Image(getClass().getResourceAsStream(ROTATE_LEFT_IMAGE),45,45,false,false);
		this.setGraphic(new ImageView(image));
		this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
	}
	
}
