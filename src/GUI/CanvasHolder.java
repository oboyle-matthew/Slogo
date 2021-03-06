package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Creates the canvas Holder (StackPane) that the turtle uses to write
 * 
 * @author Tony
 */
public class CanvasHolder extends StackPane {

	private static final double XPOS = 210;
	private static final double YPOS = 38;
	private Canvas myCanvas;
	private GraphicsContext gc;

	public CanvasHolder(Canvas canvas) {
		myCanvas = canvas;
		initPane();
	}

	public CanvasHolder(double width, double height) {
		myCanvas = new Canvas(width, height);
		initPane();
	}

	public void updateBackgroundColor(String color) {
		color = color.toLowerCase();
		this.setStyle("-fx-background-color: " + color);
	}

	public GraphicsContext getGc() {
		return gc;
	}

	private void initPane() {
		this.getChildren().add(myCanvas);
		this.setStyle("-fx-background-color: white");
		gc = myCanvas.getGraphicsContext2D();
		this.setLayoutX(XPOS);
		this.setLayoutY(YPOS);
		gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getWidth());
		gc.setStroke(Color.BLACK);
		gc.strokeRect(1, 1, myCanvas.getWidth() - 2, myCanvas.getWidth() - 2);
	}
}
