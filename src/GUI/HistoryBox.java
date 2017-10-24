package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		ObservableList<String> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
        myCommandHistoryBox.setContent(commandHistoryView);
        commandHistoryView.getSelectionModel().selectedItemProperty().addListener(
        		e->System.out.println(commandHistoryView.getSelectionModel().getSelectedItem()));
		this.getChildren().add(myCommandHistoryBox);
		this.setLayoutX(500);
		this.setLayoutY(30);
	}

	
	public void addCommandToHistoryBox(String command) {
		commandHistory.add(command);
		ObservableList<String> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
	}

}