package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay {

	private static final int CANVAS_WIDTH = 400;
	private CanvasHolder canvasHolder;
	private MainScreenGUI gui;
	private Turtle turtle = new Turtle();

	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		// add a css file to customize button
		// this.getScene().getStylesheets().add("path/stylesheet.css");

		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
//		GraphicsContext gc = canvasHolder.getGc();
		canvasHolder.updateBackgroundColor("white");
		gui = new MainScreenGUI();
		rootAdd(gui.getTextBox());
		CommandParser p = new CommandParser(language);
		// drawShapes(gc);
		rootAdd(canvasHolder);
		addTurtleToScreen();
		
		

		// TODO Auto-generated constructor stub
	}

	private void addTurtleToScreen() {
		turtle.rotateLeft(90);
		turtle.rotateRight(180);
		getRootChildren().add(turtle.getImageViewForScreen());
	}

	public void step(double elapsedTime) {
		// TODO Auto-generated method stub

	}

	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);
		gc.strokeLine(40, 10, 10, 40);
		gc.fillOval(10, 60, 30, 30);
		gc.strokeOval(60, 60, 30, 30);
		gc.fillRoundRect(110, 60, 30, 30, 10, 10);
		gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
		gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
		gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
		gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
		gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
		gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
		gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
		gc.fillPolygon(new double[] { 10, 40, 10, 40 }, new double[] { 210, 210, 240, 240 }, 4);
		gc.strokePolygon(new double[] { 60, 90, 60, 90 }, new double[] { 210, 210, 240, 240 }, 4);
		gc.strokePolyline(new double[] { 110, 140, 110, 140 }, new double[] { 210, 210, 240, 240 }, 4);
	}
}
