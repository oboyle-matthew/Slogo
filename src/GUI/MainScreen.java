package GUI;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import modelLogic.CommandParser;
import modelLogic.Turtle;

public class MainScreen extends ScreenDisplay implements GUIDelegate{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final int CANVAS_WIDTH = 350;
	private static final int GRIDSIZE = 3;
	private static final double NEW_TURTLE_INITIAL_X_POSITION = 345.0;
	private static final double NEW_TURTLE_INITIAL_Y_POSITION = 193.0;


	
	private ResourceBundle instructionsResources;
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
	private TabPane inputTabBox;
	private CustomizeButton myCustomizeButton;
	private CreateNewTurtleButton myNewTurtleButton;
	private List<Turtle> turtleArray;
	private HBox ButtonBar;
	private GridPane myDirectionGrid;
	private TurtleFileExplorer fileExplorer;
	private StackPane greyFilter;


	public MainScreen(int width, int height, Paint background, String language) {
		super(width, height, background);
		turtleArray = new ArrayList<Turtle>();
		myLanguage = language;
		parser = new CommandParser(language);
		createMainScreen(language);
	}

	public void createMainScreen(String language) {
		
	
		myTabToolBar = new TabToolBar(this);
		canvasHolder = new CanvasHolder(CANVAS_WIDTH, CANVAS_WIDTH);
		canvasHolder.updateBackgroundColor("white");
		rootAdd(canvasHolder);
//		createFirstTurtle();
		this.ButtonInit();
		
		
		myInputBox = new InputBox(this);
		myHistoryBox = new HistoryBox(this);
		
		// add a tab bar
		/*inputTabBox = new TabPane();
		Tab tabSize = new Tab();
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: white");
        border.setCenter(myInputBox);  
		
        tabSize.setText("CommandTab");
        tabSize.setContent(border);
        inputTabBox.getTabs().add(tabSize);
	
		rootAdd(inputTabBox);
		inputTabBox.setPrefWidth(350);
		inputTabBox.setPrefHeight(175);
		inputTabBox.setLayoutY(410);
		inputTabBox.setLayoutX(210);
		this.inputTabBox.getStylesheets().add(this.getClass().getResource("tab.css").toExternalForm());*/
		
	    rootAdd(myInputBox);
	    myInputBox.setPrefWidth(350);
		myInputBox.setPrefHeight(175);
		
		
		 //Handling the key typed event 
	      EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
	         @Override 
	         public void handle(MouseEvent event) { 
	        	 
	        	 
	 			//add a grey filter to the screen
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
	      
	    //Handling the key typed event 
	      EventHandler<MouseEvent> eventHandlerExit = new EventHandler<MouseEvent>() { 
	         @Override 
	         public void handle(MouseEvent event) { 
	        	 
	 			
	 			if (rootContain(greyFilter)) {
	 			rootRemove(greyFilter);
	        	 }
	         }           
	      };  
	      
	      //Adding an event handler to the text feld 
	      myInputBox.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerTextField);
	      myInputBox.addEventHandler(MouseEvent.MOUSE_EXITED, eventHandlerExit);
		//Initialize a grid pane for direction Buttons
		Image image = new Image(getClass().getResourceAsStream("Controls.png"),120,120,false,false);
		ImageView directionPad = new ImageView(image);
		directionPad.setLayoutX(608);
		directionPad.setLayoutY(430);
		rootAdd(directionPad);
		myDirectionGrid = new GridPane();
		for(int i = 0; i < GRIDSIZE ; i++) {
			ColumnConstraints column = new ColumnConstraints(40);
			myDirectionGrid.getColumnConstraints().add(column);
		}

	    for(int i = 0; i < GRIDSIZE  ; i++) {
	        RowConstraints row = new RowConstraints(40);
	        myDirectionGrid.getRowConstraints().add(row);
	    }
	    
	    myDirectionGrid.setStyle("-fx-grid-lines-visible: false" );
	    //Insets(double top, double right, double bottom, double left)
	    myDirectionGrid.setLayoutX(608);
	    myDirectionGrid.setLayoutY(430);
	    //myDirectionGrid.setPadding(new Insets(60,60,60,50)); 
	    rootAdd(myDirectionGrid);
	    myDirectionGrid.add(myForwardButton, 1, 0);
	    myDirectionGrid.add(myBackButton, 1, 2);
		rootAdd(myTabToolBar);
		
		// add css file
		//myTabToolBar.getStylesheets().add(this.getClass().getResource("tab.css").toExternalForm());
		
		myRightButton = new RotateRightButton(this);
		myLeftButton = new RotateLeftButton(this);
		rootAdd(myRightButton);
		rootAdd(myLeftButton);
		myRightButton.setLayoutX(758);
		myRightButton.setLayoutY(430);
		myLeftButton.setLayoutX(758);
		myLeftButton.setLayoutY(490);
		
		
		//try to add a package explorer on the screen
		 fileExplorer = new TurtleFileExplorer();
		 rootAdd(fileExplorer);
		 fileExplorer.setLayoutX(10);
		 fileExplorer.setLayoutY(38);
		 
		createTurtle(); 
		ogTurtle = turtleArray.get(0);

		 
		 //fileExplorer.lookup(".arrow").setVisible(false);
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
		Turtle newTurtle = new Turtle();
		newTurtle.moveTo(NEW_TURTLE_INITIAL_X_POSITION, NEW_TURTLE_INITIAL_Y_POSITION);
		getRootChildren().add(newTurtle.getImageViewForScreen());
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
			myTabToolBar.getPropertiesBox().updatePropertiesBox();

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
		operateOnTurtles("moveForward", new Class[] {Double.class}, new Object[] {newLocation} );
		//ogTurtle.moveToSimple(newLocation, ogTurtle.getYPos());
	}
	
	private void operateOnTurtles(String methodName, Class[] parameterTypes, Object[] params) {
		for(Turtle t : turtleArray) {
			try {
				Method m = t.getClass().getMethod(methodName, parameterTypes);
				m.invoke(t, params);
			} catch(Exception e) {
				System.out.println("Could not call: " + methodName);
				e.printStackTrace();
			}
		}
	
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
		moveX(50.0);
	}

	@Override
	public void backwardButtonPressed() {
		operateOnTurtles("moveBackwards",new Class[] {Double.class}, new Object[] {50.0});
	}

	@Override
	public void rotateLeftButtonPressed() {
		// TODO Auto-generated method stub
		for (Turtle t : turtleArray) {
			t.rotateLeft(30);
		}
//		operateOnTurtles("rotateLeft",new Class[] {Double.class}, new Object[] {30.0});
	}
	
	public void rotateRightButtonPressed() {
		// TODO Auto-generated method stub
		for (Turtle t : turtleArray) {
			t.rotateRight(30);
		}
//		operateOnTurtles("rotateRight",new Class[] {Double.class}, new Object[] {50.0});
	}

	@Override
	public String[] getInfo() {
		String[] info = {
				Double.toString(ogTurtle.getDirection()),
				Double.toString(ogTurtle.getXPos()),
				Double.toString(ogTurtle.getYPos()),
				"true", "green", "5", "DASHED"};
//				Boolean.toString(ogTurtle.getPenInfo()),
//				ogTurtle.getPenColor(),
//				Double.toString(ogTurtle.getPenSize()),
//				ogTurtle.getPenStyle()
//		};
		return info;
//		return new String[5];
	}
}
