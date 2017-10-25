package GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class NewProjectButton extends Button {
	
	private GUIDelegate app;
	
	public NewProjectButton(GUIDelegate app) {
		this.app = app;
		//this.setText("+");
		this.setPrefSize(30, 30);
		Image image = new Image(getClass().getResourceAsStream("addFile.png"));
	    
	    this.setGraphic(new ImageView(image));
	    this.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
 
	    
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createNewProject());

	}
	
	private void createNewProject() {
		app.createNewProject();
	}
	
}
