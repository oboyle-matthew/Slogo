package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay implements GUIDelegate{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final int CANVAS_WIDTH = 400;
	private ResourceBundle instructionsResources;
	private ResourceBundle languageResources;
	private CanvasHolder canvasHolder;
	private String myLanguage;
	private HBox turtleBox;
	private Turtle turtle = new Turtle();
	private VBox instructionsPane;
	private VBox newProject;
	private Scene newScene;
	private Stage myStage;
	private CommandParser parser;
	private InputBox myInputBox;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private HistoryBox myHistoryBox;
	private InstructionsButton myInstructionButton;
	private NewProjectButton myNewProjectButton;
	private ForwardButton myForwardButton;
	private BackButton myBackButton;
	private BackgroundColorButton myBackgroundColorButton;
	private FontColorButton myFontColorButton;
	private TabToolBar myTabToolBar;
	private CustomizeButton myCustomizeButton;
	private CreateNewTurtleButton myNewTurtleButton;
	private List<Turtle> turtleArray;



	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		myLanguage = language;
		parser = new CommandParser(language);
		createMainScreen(language);
	}

	public void createMainScreen(String language) {
		myTabToolBar = new TabToolBar(this);
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
		canvasHolder.updateBackgroundColor("white");
		rootAdd(canvasHolder);
		createFirstTurtle();
		myInputBox = new InputBox(this);
		myRunButton = new RunButton(this);
		myClearButton = new ClearButton(this);
		myHistoryBox = new HistoryBox(this);
		myInstructionButton = new InstructionsButton(this);
		myNewProjectButton = new NewProjectButton(this);
		myForwardButton = new ForwardButton(this);
		myBackButton = new BackButton(this);
		myCustomizeButton = new CustomizeButton(this);
		myNewTurtleButton = new CreateNewTurtleButton(this);
		rootAdd(myInputBox);
		rootAdd(myRunButton);
		rootAdd(myClearButton);
		rootAdd(myHistoryBox);
		rootAdd(myInstructionButton);
		rootAdd(myNewProjectButton);
		rootAdd(myForwardButton);
		rootAdd(myBackButton);
		rootAdd(myTabToolBar);
		rootAdd(myCustomizeButton);
		rootAdd(myNewTurtleButton);
	}
	
	private void createFirstTurtle() {
		turtleArray = new ArrayList<Turtle>();
		getRootChildren().add(turtle.getImageViewForScreen());
		turtle.moveTo(200, 200);
		turtleArray.add(turtle);
	}

	
	@Override
	public void createTurtle() {
		Turtle newTurtle = new Turtle();
		getRootChildren().add(newTurtle.getImageViewForScreen());
		newTurtle.moveTo(200, 200);
		turtleArray.add(newTurtle);
	}
	
	@Override
	public void runButtonPressed() {
		myTabToolBar.getHistoryBox().addCommandToHistoryBox(getText());
		for (Turtle t : turtleArray) {
			parser.executeInput(getText(), t);
		}
		clearButtonPressed();
	}

	@Override
	public void clearButtonPressed() {
		myInputBox.clearText();
	}

	@Override
	public String getText() {
		return myInputBox.readText();
	}

	@Override
	public void runCommand(String text) {
		for (Turtle t : turtleArray) {
			parser.executeInput(text,  t);
		}
	}

	@Override
	public void createInstructionsWindow() {
		instructionsPane = new VBox();
		newScene = new Scene(instructionsPane, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		instructionMaker(instructionsPane, myLanguage);
	}
	

	@Override
	public void createNewProject() {
		newProject = new VBox();
		newScene = new Scene(newProject, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		Main restart = new Main();
		restart.start(myStage);
	}

	@Override
	public void step(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCustomizeWindow() {
		newProject = new VBox();
		newScene = new Scene(newProject, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
		myBackgroundColorButton = new BackgroundColorButton(this);
		newProject.getChildren().add(myBackgroundColorButton);
		myFontColorButton = new FontColorButton(this);
		newProject.getChildren().add(myFontColorButton);
	}

	@Override
	public void moveX(Double newLocation) {
		turtle.moveToSimple(newLocation, turtle.getYPos());
	}
	
	public void setDirection(Double angle) {
		turtle.rotateLeft(angle);
	}
	
	public void instructionMaker(VBox instructionsPane, String language) {
		languageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		createInstruction(instructionsPane, "Forward");
		createInstruction(instructionsPane, "Backward");
		createInstruction(instructionsPane, "Left");
		createInstruction(instructionsPane, "Right");
		createInstruction(instructionsPane, "SetHeading");
	}
	public void createInstruction(VBox instructionsPane, String instruction) {
		instructionsPane.getChildren().add(new Label(instruction + "=" + languageResources.getString(instruction)));
	}

	@Override
	public void changeBackground(String color) {
		canvasHolder.updateBackgroundColor(color);
	}
}
