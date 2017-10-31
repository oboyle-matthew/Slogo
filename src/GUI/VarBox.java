package GUI;



import java.util.ArrayList;
import java.util.Arrays;

//import GUI.TableViewSample.EditingCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelLogic.Turtle;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class VarBox extends VBox {
	public static String[] varName = {"circle"};

	private GUIDelegate app;
	private TableView varTable;
	private ObservableList<TurProperty> displayList;
	private static String[] varValue = {"heart line"};

	
	public VarBox(GUIDelegate app) {
		this.app = app;
		
		
		
		varTable = new TableView();
		
		// Set up table colomn
		TableColumn firstCol = new TableColumn("Variable Name");
        TableColumn lastCol = new TableColumn("Value");
        varTable.getColumns().addAll(firstCol, lastCol);
        
        
        displayList =FXCollections.observableArrayList ();
			
	        	displayList.add(new TurProperty(varName[0], varValue[0]));
	        
//			   new TurProperty( "Direction ",propertyArr[0]),
//			   new TurProperty("X position ",propertyArr[1]), 
//			   new TurProperty( 	"Pen Down ",propertyArr[2]), 
//			   new TurProperty( 	"Pen Color ", propertyArr[3]), 
//			   new TurProperty( 	"PenSize ", propertyArr[4]),
//			   new TurProperty(	"PenStyle " , propertyArr[5]));
		
		
		
		//first col
		firstCol.setPrefWidth(189);
        firstCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myName"));
        
        // last col
        lastCol.setPrefWidth(189);
        lastCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myValue"));
        
        varTable.setItems(displayList);
       // updatePropertiesBox(displayList);
        //Make it editable 
        lastCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastCol.setOnEditCommit(
            new EventHandler<CellEditEvent<TurProperty, String>>() {
                @Override
                public void handle(CellEditEvent<TurProperty, String> t) {
                    ((TurProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMyName(t.getNewValue());
                    


                    int rowNum = t.getTablePosition().getRow();
                    String val = t.getNewValue();
                    System.out.print(rowNum + " : ");
                    System.out.println(val);
                    System.out.println(varName[rowNum]+"(" + val + ")" + "\n");
                    
//                    for (Command c : Command.values()) {
//                    	System.out.print(c + " : ");
//                    	System.out.println(c.name());
//                    }

                    
                    app.moveX(Double.parseDouble(t.getNewValue()));
                    
                    
//                    app.moveX(Double.parseDouble(t.getNewValue()));
//                    System.out.println(t.getNewValue());
                }
            }
        );
        
        
        firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstCol.setOnEditCommit(
            new EventHandler<CellEditEvent<TurProperty, String>>() {
                @Override
                public void handle(CellEditEvent<TurProperty, String> t) {
                    ((TurProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMyName(t.getNewValue());
                    


                    int rowNum = t.getTablePosition().getRow();
                    String val = t.getNewValue();
                    System.out.print(rowNum + " : ");
                    System.out.println(val);
                    System.out.println(varName[rowNum]+"(" + val + ")" + "\n");
                    
//                    for (Command c : Command.values()) {
//                    	System.out.print(c + " : ");
//                    	System.out.println(c.name());
//                    }

                    
                    app.moveX(Double.parseDouble(t.getNewValue()));
                    
                    
//                    app.moveX(Double.parseDouble(t.getNewValue()));
//                    System.out.println(t.getNewValue());
                }
            }
        );
        


		this.getChildren().add(varTable);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(220, 220);
		varTable.setEditable(true);
		this.setLayoutX(500);
		this.setLayoutY(30);
		this.setSpacing(20);
	}
	
	
	public void updatePropertiesBox(ObservableList<TurProperty> displayList) {
		app.setDirection(Double.parseDouble(displayList.get(0).getMyValue()));
		app.moveX(Double.parseDouble(displayList.get(1).getMyValue()));
	}
	
	public void update() {
		
	}
	
	
	
	public void updatePropertiesBox() {
		varValue = app.getInfo();
		displayList.clear();
		for (int i = 0; i < 7; i++) {
        	displayList.add(new TurProperty(varName[i], varValue[i]));
        }
	}


	
	
	
}

