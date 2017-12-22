package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay implements GUIDelegate{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final int CANVAS_WIDTH = 350;
	private static final int GRIDSIZE = 3;
	private ResourceBundle instructionsResources;
	private ResourceBundle languageResources;
	private CanvasHolder canvasHolder;
	private String myLanguage;
	private Turtle ogTurtle = new Turtle();
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
	private BackwardButton myBackButton;
	private BackgroundColorButton myBackgroundColorButton;
	private FontColorButton myFontColorButton;
	private TabToolBar myTabToolBar;
	private CustomizeButton myCustomizeButton;
	private CreateNewTurtleButton myNewTurtleButton;
	private List<Turtle> turtleArray;
	private HBox ButtonBar;
	private GridPane myDirectionGrid;



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
		
		// button init
		this.ButtonInit();
		
		
		myInputBox = new InputBox(this);
		myHistoryBox = new HistoryBox(this);
	
		
		
		rootAdd(myInputBox);
		myInputBox.setPrefWidth(350);
		myInputBox.setPrefHeight(175);
		// I comment out this history box
		//rootAdd(myHistoryBox);
		
		
		//Initialize a grid pane for direction Buttons
		myDirectionGrid = new GridPane();
		for(int i = 0; i < GRIDSIZE ; i++) {
			ColumnConstraints column = new ColumnConstraints(64);
			myDirectionGrid.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < GRIDSIZE  ; i++) {
	        RowConstraints row = new RowConstraints(64);
	        myDirectionGrid.getRowConstraints().add(row);
	    }
	    
	    myDirectionGrid.setStyle("-fx-grid-lines-visible: true" );
	    //Insets(double top, double right, double bottom, double left)
	    myDirectionGrid.setLayoutX(368);
	    myDirectionGrid.setLayoutY(400);
	    //myDirectionGrid.setPadding(new Insets(60,60,60,50)); 
	    rootAdd(myDirectionGrid);
	    myDirectionGrid.add(myForwardButton, 1, 0);
	    myDirectionGrid.add(myBackButton, 1, 2);
		rootAdd(myTabToolBar);
	}
	
	
	
	private void ButtonInit() {
		myRunButton = new RunButton(this);
		myClearButton = new ClearButton(this);
		myInstructionButton = new InstructionsButton(this);
		myNewProjectButton = new NewProjectButton(this);
		myForwardButton = new ForwardButton(this);
		myBackButton = new BackwardButton(this);
		myCustomizeButton = new CustomizeButton(this);
		myNewTurtleButton = new CreateNewTurtleButton(this);
		Button redButton = new Button("RED");
		redButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->changeBackground("red"));
		Button whiteButton = new Button("WHITE");
		whiteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->changeBackground("white"));
		
		// add all the things into an button bar hbox
		ButtonBar = new HBox();
		ButtonBar.getChildren().add(myRunButton);
		ButtonBar.getChildren().add(myClearButton);
		ButtonBar.getChildren().add(myNewTurtleButton);
		ButtonBar.getChildren().add(myNewProjectButton);
		ButtonBar.getChildren().add(myInstructionButton);
		ButtonBar.getChildren().add(myCustomizeButton);
		ButtonBar.getChildren().add(redButton);
		ButtonBar.getChildren().add(whiteButton);
		ButtonBar.setSpacing(0);
		Pane ToolBar = new Pane();
		ToolBar.getChildren().add(ButtonBar);
		ToolBar.setPrefSize(805, 30);
		ToolBar.setLayoutX(-1);
		ToolBar.setLayoutY(-1);
		ToolBar.setStyle(  "-fx-border-width: 1px; -fx-border-color: #4d4d4d; -fx-background-color: #e6e6e6;");
		
		rootAdd(ToolBar);
		
		
	}
	
	private void createFirstTurtle() {
		turtleArray = new ArrayList<Turtle>();
		getRootChildren().add(ogTurtle.getImageViewForScreen());
		
		ogTurtle.moveToSimple(CANVAS_WIDTH/2 + canvasHolder.getLayoutX() - ogTurtle.getImageViewForScreen().getBoundsInParent().getWidth()/2 , CANVAS_WIDTH/2 + canvasHolder.getLayoutY() -  ogTurtle.getImageViewForScreen().getBoundsInParent().getWidth()/2);
		turtleArray.add(ogTurtle);
	}

	
	@Override
	public void createTurtle() {
		Turtle newTurtle = new Turtle();
		getRootChildren().add(newTurtle.getImageViewForScreen());
		newTurtle.moveToSimple(200, 200);
		turtleArray.add(newTurtle);
	}
	
	@Override
	public void runButtonPressed() {
		myTabToolBar.getHistoryBox().addCommandToHistoryBox(getText());
		runCommand(getText());
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
			try {
				parser.executeInput(text,  t);
			} catch (Exception e) {
				createNewErrorWindow(text);
				e.printStackTrace();
			}
		}
	}

	private void createNewErrorWindow(String errorText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Input Error");
		alert.setHeaderText(errorText + " is not a valid command");
		alert.setContentText("See instructions button for valid commands");
		alert.show();
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
	
	private void instructionMaker(VBox instructionsPane, String language) {
		languageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		createInstruction(instructionsPane, "Forward");
		createInstruction(instructionsPane, "Backward");
		createInstruction(instructionsPane, "Left");
		createInstruction(instructionsPane, "Right");
		createInstruction(instructionsPane, "SetHeading");
	}
	private void createInstruction(VBox instructionsPane, String instruction) {
		instructionsPane.getChildren().add(new Label(instruction + "=" + languageResources.getString(instruction)));
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
		ogTurtle.moveToSimple(newLocation, ogTurtle.getYPos());
	}
	
	public void setDirection(Double angle) {
		ogTurtle.rotateLeft(angle);
	}

	@Override
	public void changeBackground(String color) {
		canvasHolder.updateBackgroundColor(color);
	}

	@Override
	public void startProject() {
		
	}

	@Override
	public void forwardButtonPressed() {
		ogTurtle.moveForward(50);
	}

	@Override
	public void backwardButtonPressed() {
		ogTurtle.moveBackwards(50);
	}

	@Override
	public void rotateLeftButtonPressed() {
		// TODO Auto-generated method stub
		ogTurtle.rotateLeft(30);
	}
	
	public void rotateRightButtonPressed() {
		// TODO Auto-generated method stub
		ogTurtle.rotateLeft(30);
	}
}
