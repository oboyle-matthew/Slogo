package GUI;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
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
	private FlowPane instructionsPane;
	private Scene newScene;
	private Stage myStage;
	
	
//    public void createGenericButton(int columnindex, int rowindex, String label,
//    		EventHandler<? super MouseEvent> eventHandler) {
//    	Button button = new Button(label);  
//    	button.setTextFill(Color.BLUE);
//    	scene1.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
//    	button.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
//    }
//    
//    public void showInstructions() {
//    	createLabel("test"), 1, 1, NORMAL_FONT);
//    	createLabel(myResources.getString("rightkey"), 1, 2, NORMAL_FONT);
//    	createLabel(myResources.getString("esckey"), 1, 3, NORMAL_FONT);
//    	createLabel(myResources.getString("buttons"), 1, 4, NORMAL_FONT);
//    }

	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		// add a css file to customize button
		// this.getScene().getStylesheets().add("path/stylesheet.css");
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
//		GraphicsContext gc = canvasHolder.getGc();
		canvasHolder.updateBackgroundColor("white");
		gui = new MainScreenGUI();
		rootAdd(gui.getTextBox());
		createInstructionsButton();
		
		CommandParser p = new CommandParser(language);
		// drawShapes(gc);
		rootAdd(canvasHolder);
		rootAdd(gui.getHistoryBox());
		addTurtleToScreen();
		
		

		// TODO Auto-generated constructor stub
	}
	
	public void createInstructionsButton() {
		Button instructions = new Button("Instructions");
		instructions.addEventHandler(MouseEvent.MOUSE_CLICKED, e->showInstructions());
		rootAdd(instructions);
	}
	
	public void showInstructions() {
		instructionsPane = new FlowPane();
		newScene = new Scene(instructionsPane, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		
	}
	
	

	private void addTurtleToScreen() {
		turtle.moveToSimple(200, 200);
		turtle.rotateRight(360);
		turtle.moveTo(5, 10);
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
