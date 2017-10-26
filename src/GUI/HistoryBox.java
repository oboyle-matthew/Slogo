package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HistoryBox extends HBox {
	
	private GUIDelegate app;
	private List<Button> commandHistory;
	private ScrollPane myCommandHistoryBox;
	private ListView<Button> commandHistoryView;
	
	public HistoryBox(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(300);
		this.setLayoutX(500);
		this.setLayoutY(30);
		myCommandHistoryBox = new ScrollPane();
		commandHistoryView = new ListView<Button>();
		commandHistory = new ArrayList<Button>();
		ObservableList<Button> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
        commandHistoryView.getSelectionModel();
        myCommandHistoryBox.setContent(commandHistoryView);
//		ChangeListener<Button> propertyHandler = (obs, old, cur) -> {
//			if (cur != null) {
//				app.runCommand(old.getText());
//			}
//			commandHistoryView.getSelectionModel().clearSelection();
//		};
//        commandHistoryView.getSelectionModel().selectedItemProperty().addListener(propertyHandler);
//        commandHistoryView.getSelectionModel().getSelectedItem().addEventHandler
//        	(MouseEvent.MOUSE_CLICKED, e->System.out.println("Test"));
//        	app.runCommand(commandHistoryView.
//        			getSelectionModel().getSelectedItem().getText()));
        this.getChildren().add(myCommandHistoryBox);
	}
	
	public void addCommandToHistoryBox(String command) {
		Button button = new Button(command);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, e->app.runCommand(command));
		commandHistory.add(button);
		ObservableList<Button> items =FXCollections.observableArrayList(commandHistory);
        commandHistoryView.setItems(items);
	}
	
	private void readHistory(String text) {
		app.runCommand(text);
	}
	
}
