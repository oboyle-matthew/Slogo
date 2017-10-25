package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay implements GUIDelegate{

	private static final int CANVAS_WIDTH = 400;
	private CanvasHolder canvasHolder;
	private HBox turtleBox;
	private MainScreenGUI gui;
	private Turtle turtle = new Turtle();
	private VBox instructionsPane;
	private VBox newProject;
	private Scene newScene;
	private Stage myStage;
	private CommandParser parser;
	private InputBox myInputBox;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private NewHistoryBox myHistoryBox;
	private InstructionsButton myInstructionButton;
	private NewProjectButton myNewProjectButton;
	private ForwardButton myForwardButton;
	private BackButton myBackButton;
	private BackgroundColorButton myBackgroundColorButton;
	private FontColorButton myFontColorButton;
	private TabToolBar myTabToolBar;
	private CustomizeButton myCustomizeButton;



	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		parser = new CommandParser(language);
		createMainScreen(language);
	}

	public void createMainScreen(String language) {
		myTabToolBar = new TabToolBar(this);
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
		canvasHolder.updateBackgroundColor("white");
		gui = new MainScreenGUI();
		rootAdd(canvasHolder);
		addTurtleToScreen();
		myInputBox = new InputBox(this);
		myRunButton = new RunButton(this);
		myClearButton = new ClearButton(this);
		myHistoryBox = new NewHistoryBox(this);
		myInstructionButton = new InstructionsButton(this);
		myNewProjectButton = new NewProjectButton(this);
		myForwardButton = new ForwardButton(this);
		myBackButton = new BackButton(this);
		myCustomizeButton = new CustomizeButton(this);
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
	}

	private void addTurtleToScreen() {
		getRootChildren().add(turtle.getImageViewForScreen());
		turtle.moveTo(200, 200);
	}
	
	public void executeCommand(String input, Turtle turtle) {
		parser.executeInput("left 90", turtle);
		parser.executeInput("right 90", turtle);
	}
	
	@Override
	public void runButtonPressed() {
		myTabToolBar.getHistoryBox().addCommandToHistoryBox(getText());
		parser.executeInput(getText(), turtle);
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
		parser.executeInput(text,  turtle);
	}

	@Override
	public void createInstructionsWindow() {
		instructionsPane = new VBox();
		instructionsPane.getChildren().add(new Label("Insert Instructions Here"));
		newScene = new Scene(instructionsPane, 400, 400);
		myStage = new Stage();
		myStage.setScene(newScene);
		myStage.show();
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
		turtle.setHeading(angle);
	}
}
