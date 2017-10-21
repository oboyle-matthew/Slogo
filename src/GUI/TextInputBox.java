package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TextInputBox extends HBox{
	private TextArea commandField;
	private Button runButton;
	private Button clearButton;
	private VBox ButtonBlock;
	
	public TextInputBox () {
		//init TextArea
		commandField = new TextArea();
		commandField.setPrefColumnCount(15);
		commandField.setPromptText("Enter your command....");
		commandField.setPrefWidth(400);
		commandField.setPrefHeight(100);
		
		
		
		//init Button
		runButton = new Button("run");
		clearButton = new Button ("clear");
		runButton.setPrefWidth(50);
		clearButton.setPrefWidth(50);
		createRunButton();
//    	Old way of writing this below
//    	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//	        @Override public void handle(MouseEvent e) {
//	        	begin(gametype);}
//        };
		EventHandler<? super MouseEvent> eventHandler =  e->readText("test");
		runButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    	
		clearButton.setStyle("-fx-background-color: RED");
		ButtonBlock = new VBox();
		ButtonBlock.getChildren().add(runButton);
		ButtonBlock.getChildren().add(clearButton);
		
		//set up whole box
		this.getChildren().add(commandField);
		this.getChildren().add(ButtonBlock);
		this.setLayoutX(30);
		this.setLayoutY(450);
		}

	private void readText(String test) {
		String tester = commandField.getText();
		commandField.clear();
		System.out.println(tester);
	}
	
	private void createRunButton() {
		runButton.setStyle("-fx-font-family: Andale Mono");
		runButton.setStyle("-fx-padding: 6");
		runButton.setStyle("-fx-font-size: 1em");
		runButton.setTextFill(Color.WHITE);
		runButton.setStyle("-fx-background-color: #001A57;");
		runButton.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
	}

}
