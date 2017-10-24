package GUI;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay {

	private static final int CANVAS_WIDTH = 400;
	private CanvasHolder canvasHolder;
	private MainScreenGUI gui;
	private Turtle turtle = new Turtle();
	private VBox instructionsPane;
	private VBox newProject;
	private Scene newScene;
	private Stage myStage;

	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		createMainScreen(language);
	}

	public void createMainScreen(String language) {
		// add a css file to customize button
		// this.getScene().getStylesheets().add("path/stylesheet.css");
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
//		GraphicsContext gc = canvasHolder.getGc();
		canvasHolder.updateBackgroundColor("white");
		gui = new MainScreenGUI();
		rootAdd(gui.getTextBox());
		createInstructionsButton();
		createNewProjectButton();
		CommandParser p = new CommandParser(language);
		// drawShapes(gc);
		rootAdd(canvasHolder);
		rootAdd(gui.getHistoryBox());
		addTurtleToScreen();
	}
	
	public void createInstructionsButton() {
		Button instructions = new Button("Instructions");
		instructions.setLayoutX(50);
		instructions.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				e->showInstructions());
		rootAdd(instructions);
	}
	
	public void createNewProjectButton() {
		Button newProjectButton = new Button("+");
		newProjectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createNewProject());
//		newProjectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->createMainScreen());
		rootAdd(newProjectButton);
	}
	
	public void createNewProject() {
		newProject = new VBox();
//		newProject.getChildren().add(new Label("new project"));
		newScene = new Scene(newProject, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		Main restart = new Main();
		restart.start(myStage);
	}
	
	public void showInstructions() {
		instructionsPane = new VBox();
		instructionsPane.getChildren().add(new Label("Insert Instructions Here"));
		newScene = new Scene(instructionsPane, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		
	}
	
	

	private void addTurtleToScreen() {
		getRootChildren().add(turtle.getImageViewForScreen());
		turtle.moveTo(200, 200);
		turtle.rotateLeft(210);
		turtle.moveForward(300);
		turtle.rotateRight(210);
		turtle.moveForward(300);
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
