package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import GUI.HistoryBox;

public class TextInputBox extends HBox{
	private TextArea commandField;
	private Button runButton;
	private Button clearButton;
	private VBox ButtonBlock;
	private HistoryBox historyBox;
	
	public TextInputBox () {
		historyBox = new HistoryBox();
		createCommandField();
		createButtons();
		createTextInputBox();
		}

	private void createTextInputBox() {
		this.getChildren().add(commandField);
		this.getChildren().add(ButtonBlock);
		this.setLayoutX(30);
		this.setLayoutY(450);
	}

	private void createButtons() {
		createRunButton();
		createClearButton();
		createButtonBlock();
	}

	private void createButtonBlock() {
		ButtonBlock = new VBox();
		ButtonBlock.getChildren().add(runButton);
		ButtonBlock.getChildren().add(clearButton);
	}

	private void createClearButton() {
		clearButton = new Button ("clear");
		clearButton.setPrefWidth(50);
		clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->commandField.clear());
		clearButton.setStyle("-fx-background-color: RED");
	}

	private void createCommandField() {
		commandField = new TextArea();
		commandField.setPrefColumnCount(15);
		commandField.setPromptText("Enter your command....");
		commandField.setPrefWidth(400);
		commandField.setPrefHeight(100);
	}

	private void readText() {
		String tester = commandField.getText();
		commandField.clear();
		Controller.stringInput(tester);
		historyBox.addCommandToHistoryBox(tester);
	}
	


	private void createRunButton() {
		runButton = new Button("run");
		runButton.setPrefWidth(50);
		runButton.setStyle("-fx-font-family: Andale Mono");
		runButton.setStyle("-fx-padding: 6");
		runButton.setStyle("-fx-font-size: 1em");
		runButton.setTextFill(Color.WHITE);
		runButton.setStyle("-fx-background-color: #001A57;");
		runButton.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
		runButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->readText());
	}
	
	public HistoryBox getHistoryBox() {
		return historyBox;
	}

}
