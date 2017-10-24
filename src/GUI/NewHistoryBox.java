package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class NewHistoryBox extends HBox {
	
	private GUIDelegate app;
//	private ListView<String> myHistoryPaneView;
	private List<String> commandHistory;
	private ScrollPane myCommandHistoryBox;
	private ListView<String> commandHistoryView;
	
	public NewHistoryBox(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(300);
		this.setLayoutX(500);
		this.setLayoutY(30);
		myCommandHistoryBox = new ScrollPane();
		commandHistoryView = new ListView<String>();
		commandHistory = new ArrayList<String>();
		commandHistory.add("test");
		ObservableList<String> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
        myCommandHistoryBox.setContent(commandHistoryView);
		commandHistoryView.getSelectionModel().selectedItemProperty().addListener(
        		e->readHistory(commandHistoryView.getSelectionModel().getSelectedItem()));
		this.getChildren().add(myCommandHistoryBox);
	}
	
	public void addCommandToHistoryBox(String command) {
		commandHistory.add(command);
		ObservableList<String> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
	}
	
	private void readHistory(String text) {
		app.runCommand(text);
	}
	
}
