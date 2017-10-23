package GUI;

import java.util.ArrayList;
import java.util.List;

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
	
	public HistoryBox() {
		myCommandHistoryPane = new ScrollPane();
		this.getChildren().add(myCommandHistoryPane);
		this.setLayoutX(500);
//		this.getChildren().add(commandField);
//		this.setLayoutX(30);
//		this.setLayoutY(450);
	}

	private void createHistoryBox() {
//		this.getChildren().add(commandField);
		this.getChildren().add(ButtonBlock);
		this.setLayoutX(500);
		this.setLayoutY(50);
	}

}
