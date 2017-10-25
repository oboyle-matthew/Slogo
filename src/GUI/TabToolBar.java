package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TabToolBar extends TabPane {
	
	private GUIDelegate app;
	private HistoryBox historyBox;
	
	public TabToolBar(GUIDelegate app) {
		
		this.app = app;
		//addTab();
		historyBox = new HistoryBox(app);
		this.setStyle("-fx-padding: 1; -fx-background-color: grey, -fx-control-inner-background; -fx-background-insets: 0, 1; ");
		
		this.setLayoutX(370);
		this.setLayoutY(38);
		this.setPrefSize(420, 350);
		addTab("Property",this.sizingSample());
		addTab("CommandHistory",this.historyCommand());
	}
	
	public HistoryBox getHistoryBox() {
		return historyBox;
	}
	
	
	private void addTab(String tabName,Pane tabContext ) {
		Tab tabSize = new Tab();
        tabSize.setText(tabName);
        tabSize.setContent(tabContext);
        this.getTabs().addAll(tabSize);
	}
	
	
private Pane sizingSample() {
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 20, 20, 20));
        border.setStyle("-fx-background-color: white");
        border.setCenter(new PropertiesBox(app));    
       
        return border;
    }



private Pane historyCommand() {
    
    BorderPane border = new BorderPane();
    border.setPadding(new Insets(20, 20, 20, 20));
    border.setStyle("-fx-background-color: white");
    border.setCenter(historyBox);    
   
    return border;
}
}