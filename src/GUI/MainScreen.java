package GUI;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import modelLogic.CanvasWriter;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay implements GUIDelegate{
	private static final String CONTROLS_IMAGE = "Controls.png";
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final int CANVAS_WIDTH = 350;
	private static final int GRIDSIZE = 3;
	private static final double NEW_TURTLE_INITIAL_X_POSITION = 345.0;
	private static final double NEW_TURTLE_INITIAL_Y_POSITION = 193.0;

	private ResourceBundle languageResources;
	private CanvasHolder canvasHolder;
	private String myLanguage;
	private Turtle ogTurtle;
	private VBox instructionsPane;
	private VBox newProject;
	private Scene newScene;
	private Stage myStage;
	private CommandParser parser;
	private InputBox myInputBox;
	private RunButton myRunButton;
	private RotateRightButton myRightButton;
	private RotateLeftButton myLeftButton;
	private ClearButton myClearButton;
	private HistoryBox myHistoryBox;
	private InstructionsButton myInstructionButton;
	private NewProjectButton myNewProjectButton;
	private ForwardButton myForwardButton;
	private BackwardButton myBackButton;
	private BackgroundColorButton myBackgroundColorButton;
	private FontColorButton myFontColorButton;
	private TabToolBar myTabToolBar;
	// for input box
	private CustomizeButton myCustomizeButton;
	private CreateNewTurtleButton myNewTurtleButton;
	private List<CanvasWriter> writerList;
	private HBox ButtonBar;
	private GridPane myDirectionGrid;
	private TurtleFileExplorer fileExplorer;
	private StackPane greyFilter;


	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		writerList = new ArrayList<>();
		myLanguage = language;
		parser = new CommandParser(language);
		createMainScreen(language);
	}

	public void createMainScreen(String language) {
		myTabToolBar = new TabToolBar(this);
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
		canvasHolder.updateBackgroundColor("white");
		rootAdd(canvasHolder);
		buttonInit();	
		
		myHistoryBox = new HistoryBox(this);
		
		// Setup InputBox
		myInputBox = new InputBox(this);
	    myInputBox.setPrefWidth(350);
		myInputBox.setPrefHeight(175);
		rootAdd(myInputBox);
		 
		// Setup Event Handlers for Input Box
	    EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
	    		@Override 
	    		public void handle(MouseEvent event) {  
	 			greyFilter = new StackPane();
	 			greyFilter.setStyle("-fx-background-color: rgba(33, 33, 146, 0.5); -fx-background-radius: 10;");
	 			greyFilter.setLayoutX(0);
	 			greyFilter.setLayoutY(0);
	 			if (!rootContain(greyFilter)) {
	 				greyFilter.setPrefSize(1000, 600);
	 				rootRemove(myInputBox);
	 				rootAdd(greyFilter);
	 				rootAdd(myInputBox);
	        	 	}
	         }           
	      };  
	      
	    EventHandler<MouseEvent> eventHandlerExit = new EventHandler<MouseEvent>() { 
	    		@Override 
	    		public void handle(MouseEvent event) { 	
	    			if (rootContain(greyFilter)) rootRemove(greyFilter);
	    		}           
	      };  
	    myInputBox.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerTextField);
	    myInputBox.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerExit);
	    
	    // Setup direction pad 
		Image image = new Image(getClass().getResourceAsStream("Controls.png"),120,120,false,false);
		ImageView directionPad = new ImageView(image);
		directionPad.setLayoutX(608);
		directionPad.setLayoutY(430);
		rootAdd(directionPad);
		myDirectionGrid = new GridPane();
		for(int i = 0; i < GRIDSIZE ; i++) {
			myDirectionGrid.getColumnConstraints().add(new ColumnConstraints(40));
			myDirectionGrid.getRowConstraints().add(new RowConstraints(40));
		}
	    myDirectionGrid.setStyle("-fx-grid-lines-visible: false" );
	    myDirectionGrid.setLayoutX(608);
	    myDirectionGrid.setLayoutY(430); 
	    rootAdd(myDirectionGrid);
	    myDirectionGrid.add(myForwardButton, 1, 0);
	    myDirectionGrid.add(myBackButton, 1, 2);
		rootAdd(myTabToolBar);
	
		// Setup rotation buttons
		myRightButton = new RotateRightButton(this);
		myLeftButton = new RotateLeftButton(this);
		rootAdd(myRightButton);
		rootAdd(myLeftButton);
		myRightButton.setLayoutX(758);
		myRightButton.setLayoutY(430);
		myLeftButton.setLayoutX(758);
		myLeftButton.setLayoutY(490);	
		
		// Setup file explorer
		fileExplorer = new TurtleFileExplorer();
		rootAdd(fileExplorer);
		fileExplorer.setLayoutX(10);
		fileExplorer.setLayoutY(38);
		
		// Create the initial Turtle
		createTurtle(); 
	}
	
	
	private void buttonInit() {
		myRunButton = new RunButton(this);
		myClearButton = new ClearButton(this);
		myInstructionButton = new InstructionsButton(this);
		myNewProjectButton = new NewProjectButton(this);
		myForwardButton = new ForwardButton(this);
		myBackButton = new BackwardButton(this);
		myCustomizeButton = new CustomizeButton(this);
		myNewTurtleButton = new CreateNewTurtleButton(this);

		// add all the things into an button bar hbox
		ButtonBar = new HBox();
		ButtonBar.getChildren().add(myRunButton);
		ButtonBar.getChildren().add(myClearButton);
		ButtonBar.getChildren().add(myNewTurtleButton);
		ButtonBar.getChildren().add(myNewProjectButton);
		ButtonBar.getChildren().add(myInstructionButton);
		ButtonBar.getChildren().add(myCustomizeButton);
		ButtonBar.setSpacing(0);
		Pane ToolBar = new Pane();
		ToolBar.getChildren().add(ButtonBar);
		ToolBar.setPrefSize(1005, 30);
		ToolBar.setLayoutX(-1);
		ToolBar.setLayoutY(-1);
		ToolBar.setStyle(  "-fx-border-width: 1px; -fx-border-color: #4d4d4d; -fx-background-color: #e6e6e6;");
		rootAdd(ToolBar);
	}
	
	//get fileExplorer
	public TurtleFileExplorer getTurExpo() {
		return fileExplorer;
	}
	
	@Override
	public void createTurtle() {
		Turtle newTurtle = new Turtle(this);
		newTurtle.goToRelativePosition(0.0, 0.0);
		writerList.add(newTurtle);
		updateTurtleProperties();
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
		for (CanvasWriter w : writerList) {
			try {
				if(w.isActivated()) parser.executeInput(text,  w);
			} catch (Exception e) {
				createNewErrorWindow(text);
				e.printStackTrace();
			}
			updateTurtleProperties();
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
	
	private void instructionMaker(VBox instructionsPane, String language) {
		languageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		for (String s : languageResources.keySet()) {
			createInstruction(instructionsPane, s);
		}
//		createInstruction(instructionsPane, "Forward");
	}
	
	private void createInstruction(VBox instructionsPane, String instruction) {
		instructionsPane.getChildren().add(new Label(instruction + " = " + languageResources.getString(instruction)));
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
		operateOnWriters("moveTo", new Class[] {Double.class, Double.class}, new Object[] {newLocation} );
	}
	
	private void operateOnWriters(String methodName, Class[] parameterTypes, Object[] params) {
		for(CanvasWriter w : writerList) {
			try {
				if(w.isActivated()) {
					Method m = w.getClass().getMethod(methodName, parameterTypes);
					m.invoke(w, params);
				}
			} catch(Exception e) {
				System.out.println("Could not call: " + methodName);
				e.printStackTrace();
			}
		}
		updateTurtleProperties();
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
		operateOnWriters("moveForward", new Class[]{Double.class}, new Object[] {50.0});
	}

	@Override
	public void backwardButtonPressed() {
		operateOnWriters("moveBackwards",new Class[] {Double.class}, new Object[] {50.0});
	}

	@Override
	public void rotateLeftButtonPressed() {		
		operateOnWriters("rotateLeft",new Class[] {Double.class}, new Object[] {30.0});
	}
	
	public void rotateRightButtonPressed() {
		operateOnWriters("rotateRight",new Class[] {Double.class}, new Object[] {30.0});
	}

	@Override
	public String[] getInfo() {
		String[] info = {
				Double.toString(writerList.get(0).getHeading()),
				Double.toString(writerList.get(0).getXPos()),
				Double.toString(writerList.get(0).getYPos()),
				"" + writerList.get(0).getMyPen().getPenInfo(), writerList.get(0).getMyPen().getPenColor().toString(), 
				"" + writerList.get(0).getMyPen().getPenSize(), "DASHED"};
		return info;
	}

	@Override
	public void updateTurtleProperties() {
		myTabToolBar.getPropertiesBox().updatePropertiesBox();
	}

	@Override
	public void addNode(Node n) {
		getRootChildren().add(n);
	}

	@Override
	public void removeNode(Node n) {
		getRootChildren().remove(n);
	}
	
	private void createNewErrorWindow(String errorText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Input Error");
		alert.setHeaderText(errorText + " is not a valid command");
		alert.setContentText("See instructions button for valid commands");
		alert.show();
	}

	@Override
	public void setDirection(Double angle) {
				
	}


}
