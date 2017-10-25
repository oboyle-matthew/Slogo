package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtleScreen extends StackPane {

	private static final double XPOS = 30;
	private static final double YPOS = 30;
	private Canvas myCanvas;
	private GraphicsContext gc;

	// pass in a canvas
	public TurtleScreen(GUIDelegate app) {
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.setWidth(300);
		this.setHeight(200);
		this.setBackground(new Color(0,0,0));
//		initPane();
	}

	
}
