package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TabToolBar extends TabPane {
	
	private GUIDelegate app;
	private HistoryBox historyBox;
	private PropertiesBox propertiesBox;
	private VarBox myVarBox;
	
	public TabToolBar(GUIDelegate app) {
		propertiesBox = new PropertiesBox(app);
		this.app = app;
		//addTab();
		historyBox = new HistoryBox(app);
		myVarBox = new VarBox(app);
		this.setStyle("-fx-padding: 1; -fx-background-color: grey, -fx-control-inner-background; -fx-background-insets: 0, 1; ");
		// change it over here
		this.setLayoutX(570);
		this.setLayoutY(38);
		this.setPrefSize(420, 350);
		addTab("Property",this.sizingSample());
		addTab("History",this.historyCommand());
		addTab("New Variables",this.CustomizePane());
	}
	
	public HistoryBox getHistoryBox() {
		return historyBox;
	}
	
	public PropertiesBox getPropertiesBox() {
		return propertiesBox;
	}
	
	public VarBox getVarBox() {
		return myVarBox;
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
        border.setCenter(propertiesBox);    
       
        return border;
    }



private Pane historyCommand() {
    
    BorderPane border = new BorderPane();
    border.setPadding(new Insets(20, 20, 20, 20));
    border.setStyle("-fx-background-color: white");
    Label text = new Label("Command History");
	text.setFont(new Font("Andale Mono", 20));
	text.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
	border.setTop(text);
	
    border.setCenter(historyBox);  
    //top Right Bottom Left
    historyBox.setPadding(new Insets(20, 0, 0, 0));
    border.setAlignment(text, Pos.CENTER);
    
    
    
   
    return border;
	}




//third pane
private Pane CustomizePane() {
    
    BorderPane border = new BorderPane();
    border.setPadding(new Insets(20, 20, 20, 20));
    border.setStyle("-fx-background-color: white");
    Label text = new Label("Customized Variable");
	text.setFont(new Font("Andale Mono", 20));
	text.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
	border.setTop(text);
	
    border.setCenter(myVarBox);  
    //top Right Bottom Left
    myVarBox.setPadding(new Insets(20, 0, 0, 0));
    border.setAlignment(text, Pos.CENTER);
    
    
    
   
    return border;
	}



}