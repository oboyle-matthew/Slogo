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
	private List<String> myHistory;
	private ScrollPane myCommandHistoryPane;
	private ListView<String> myHistoryPaneView;
	
	public HistoryBox() {
		myCommandHistoryPane = new ScrollPane();
		myHistoryPaneView = new ListView<String>();
		myHistory = new ArrayList<String>();
		myHistory.add("Command One");
		myHistory.add("Command Two");
		myHistory.add("Command Three");
		myHistory.add("Command Four");
		myHistory.add("Command Five");
		ObservableList<String> items =FXCollections.observableArrayList(myHistory);
        myHistoryPaneView.setItems(items);
        myCommandHistoryPane.setContent(myHistoryPaneView);
		this.getChildren().add(myCommandHistoryPane);
		this.setLayoutX(500);
		this.setLayoutY(30);
//		this.getChildren().add(commandField);
//		this.setLayoutX(30);
//		this.setLayoutY(450);
	}

}
