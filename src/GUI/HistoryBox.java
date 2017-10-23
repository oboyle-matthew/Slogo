package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HistoryBox extends HBox{
//	private ListView<String> myHistoryPaneView;
	private List<String> commandHistory;
	private ScrollPane myCommandHistoryBox;
	private ListView<String> commandHistoryView;
	
	public HistoryBox() {
		myCommandHistoryBox = new ScrollPane();
		commandHistoryView = new ListView<String>();
		commandHistory = new ArrayList<String>();
		addRandomCommands();
		ObservableList<String> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
        myCommandHistoryBox.setContent(commandHistoryView);
		this.getChildren().add(myCommandHistoryBox);
		this.setLayoutX(500);
		this.setLayoutY(30);
//		this.getChildren().add(commandField);
//		this.setLayoutX(30);
//		this.setLayoutY(450);
	}

	private void addRandomCommands() {
		addCommandToHistoryBox("Command One");
		addCommandToHistoryBox("Command Two");
		addCommandToHistoryBox("Command Three");
		addCommandToHistoryBox("Command Four");
		addCommandToHistoryBox("Command Five");
	}
	
	public void addCommandToHistoryBox(String command) {
		commandHistory.add(command);
	}

}
