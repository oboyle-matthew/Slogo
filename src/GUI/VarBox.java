package GUI;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

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
	public static String[] varName;

	private GUIDelegate app;
	private TableView varTable;
	private ObservableList<TurProperty> displayList;
	private static Set<String> varSet;

	
	public VarBox(GUIDelegate app) {
		this.app = app;
		
		
		
		varTable = new TableView();
		
		TableColumn firstCol = new TableColumn("Variable Name");
        TableColumn lastCol = new TableColumn("Value");
        varTable.getColumns().addAll(firstCol, lastCol);
        
        displayList =FXCollections.observableArrayList ();
			
		firstCol.setPrefWidth(189);
        firstCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myName"));
        
        lastCol.setPrefWidth(189);
        lastCol.setCellValueFactory(
                new PropertyValueFactory<TurProperty, String>("myValue"));
        
        varTable.setItems(displayList);
        lastCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastCol.setOnEditCommit(
            new EventHandler<CellEditEvent<TurProperty, String>>() {
                @Override
                public void handle(CellEditEvent<TurProperty, String> t) {
                    ((TurProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMyName(t.getNewValue());
                    System.out.println(t.getNewValue());
                    System.out.println(t.getTablePosition().getRow());
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
	
	public void update(Map<String, Double> map) {
		displayList.clear();
		varSet = map.keySet();
		for (String s : varSet) {
			displayList.add(new TurProperty(s, Double.toString(map.get(s))));
		}
	}
	
}

