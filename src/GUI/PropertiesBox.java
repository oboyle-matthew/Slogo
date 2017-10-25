package GUI;



//import GUI.TableViewSample.EditingCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelLogic.Turtle;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PropertiesBox extends VBox {
	private GUIDelegate app;
	private Label text;
	private TableView propertyTable;
	private static String[] propertyArr = {"0.0","0.0","0.0","true","1","1.0","SOLID"};

	
	public PropertiesBox(GUIDelegate app) {
		this.app = app;
		text = new Label("properties");
		text.setFont(new Font("Andale Mono", 20));
		text.setStyle("-fx-effect: dropshadow(gaussian, rgba(67,96,156,0.25) , 0,0,2,2 )");
		
		
		propertyTable = new TableView();
		
		// Set up table colomn
		TableColumn firstCol = new TableColumn("Property");
        TableColumn lastCol = new TableColumn("Value");
        propertyTable.getColumns().addAll(firstCol, lastCol);
        
     
		ObservableList<TurProperty> displayList =FXCollections.observableArrayList (
			   new TurProperty( "Direction ",propertyArr[0]),
			   new TurProperty("X position ",propertyArr[1]), 
			   new TurProperty( 	"Pen Down ",propertyArr[2]), 
			   new TurProperty( 	"Pen Color ", propertyArr[3]), 
			   new TurProperty( 	"PenSize ", propertyArr[4]),
			   new TurProperty(	"PenStyle " , propertyArr[5]));
		
		
		
		//first col
		firstCol.setPrefWidth(140);
        firstCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myName"));
        
        // last col
        lastCol.setPrefWidth(140);
        lastCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myValue"));
        
        propertyTable.setItems(displayList);
        updatePropertiesBox(displayList);
        //Make it editable 
        lastCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastCol.setOnEditCommit(
            new EventHandler<CellEditEvent<TurProperty, String>>() {
                @Override
                public void handle(CellEditEvent<TurProperty, String> t) {
                    ((TurProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMyName(t.getNewValue());
                    app.moveX(Double.parseDouble(t.getNewValue()));
//                    System.out.println(t.getNewValue());
                }
            }
        );
        

		this.getChildren().add(text);
		this.getChildren().add(propertyTable);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(220, 220);
		propertyTable.setEditable(true);
		this.setLayoutX(500);
		this.setLayoutY(30);
		this.setSpacing(20);
	}
	
	
	public void updatePropertiesBox(ObservableList<TurProperty> displayList) {
		app.setDirection(Double.parseDouble(displayList.get(0).getMyValue()));
		app.moveX(Double.parseDouble(displayList.get(1).getMyValue()));
	}


	
	
	
}