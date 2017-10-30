package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class HistoryBox extends HBox {
	
	private static final int WIDTH = 380;
	private GUIDelegate app;
	private List<Button> commandHistory;
	private ScrollPane myCommandHistoryBox;
	private ListView<Button> commandHistoryView;
	
	public HistoryBox(GUIDelegate app) {
		this.app = app;
		this.setLayoutX(500);
		this.setLayoutY(30);
		myCommandHistoryBox = new ScrollPane();
		commandHistoryView = new ListView<Button>();
		commandHistory = new ArrayList<Button>();
		ObservableList<Button> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
        commandHistoryView.getSelectionModel();
        myCommandHistoryBox.setContent(commandHistoryView);
        this.getChildren().add(myCommandHistoryBox);
        this.setPrefWidth(WIDTH);
        this.setAlignment(Pos.CENTER);
	}
	
	public void addCommandToHistoryBox(String command) {
		Button button = new Button(command);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.runCommand(command));
		commandHistory.add(button);
		//set button's image invisible
		button.setStyle(  "-fx-border-color: transparent; -fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
		ObservableList<Button> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
	}
	
}
