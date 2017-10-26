package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CanvasHolder extends StackPane {

	private static final double XPOS = 0;
	private static final double YPOS = 38;
	private Canvas myCanvas;
	private GraphicsContext gc;

	// pass in a canvas
	public CanvasHolder(Canvas canvas) {
		myCanvas = canvas;
		initPane();
	}

	// pass in width and height of canvas
	public CanvasHolder(double width, double height) {
		myCanvas = new Canvas(width, height);
		initPane();
	}

	public void updateBackgroundColor(String color) {
		color = color.toLowerCase();
		this.setStyle("-fx-background-color: " + color);
	}

	// getter for GraphicsContext
	public GraphicsContext getGc() {
		return gc;
	}

	private void initPane() {
		this.getChildren().add(myCanvas);
		this.setStyle("-fx-background-color: white");
		gc = myCanvas.getGraphicsContext2D();
		this.setLayoutX(XPOS);
		this.setLayoutY(YPOS);

		// draw the border
		gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getWidth());
		gc.setStroke(Color.BLACK);
		gc.strokeRect(1, 1, myCanvas.getWidth() - 2, myCanvas.getWidth() - 2);
	}
}
