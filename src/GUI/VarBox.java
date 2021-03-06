package GUI;

import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

public class VarBox extends VBox {
	public static String[] varName;

	private GUIDelegate app;
	private TableView varTable;
	private ObservableList<TurProperty> displayList;
	private Set<String> varSet;
	private Object[] varArray;

	public VarBox(GUIDelegate app) {
		this.app = app;

		varTable = new TableView();

		TableColumn firstCol = new TableColumn("Variable Name");
		TableColumn lastCol = new TableColumn("Value");
		varTable.getColumns().addAll(firstCol, lastCol);

		displayList = FXCollections.observableArrayList();

		firstCol.setPrefWidth(189);
		firstCol.setCellValueFactory(new PropertyValueFactory<TurProperty, String>("myName"));

		lastCol.setPrefWidth(189);
		lastCol.setCellValueFactory(new PropertyValueFactory<TurProperty, String>("myValue"));

		varTable.setItems(displayList);
		lastCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lastCol.setOnEditCommit(new EventHandler<CellEditEvent<TurProperty, String>>() {
			@Override
			public void handle(CellEditEvent<TurProperty, String> t) {
				((TurProperty) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setMyName(t.getNewValue());
				System.out.println(t.getNewValue());
				int row = t.getTablePosition().getRow();
				System.out.println(varArray[row]);
				app.updateVarSet(varArray[row], Double.parseDouble(t.getNewValue()));
			}

		});

		firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
		firstCol.setOnEditCommit(new EventHandler<CellEditEvent<TurProperty, String>>() {
			@Override
			public void handle(CellEditEvent<TurProperty, String> t) {
				((TurProperty) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setMyName(t.getNewValue());
				int rowNum = t.getTablePosition().getRow();
				String val = t.getNewValue();
			}
		});

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
		varArray = varSet.toArray();
		for (String s : varSet) {
			displayList.add(new TurProperty(s, Double.toString(map.get(s))));
		}
	}

}
