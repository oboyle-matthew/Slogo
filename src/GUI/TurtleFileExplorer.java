package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TurtleFileExplorer extends TitledPane {

	private ListView<HBox> paneListView = new ListView<>();
	private GUIDelegate app;
	int count = 0;

	// constructor
	public TurtleFileExplorer(GUIDelegate app) {
		this.app = app;
		this.setStyle("-fx-background-color: white, white, white;\n" + "  -fx-background-insets: 0, 1, 2;\n"
				+ "  -fx-background-radius: 60 60 0 0, 60 60 0 0, 0 0 0 0;\n");

		Image imageTur = new Image(getClass().getResourceAsStream("packageExpo.png"));
		this.setGraphic(HBoxBuilder.create().spacing(5).alignment(Pos.CENTER_LEFT).children(

				new ImageView(imageTur), new Label("Package Explorer")).build());

		this.setContent(createListView());

		this.setPrefSize(190, 546);
	}

	private Node createListView() {

		addTurtleFile();
		return paneListView;
	}

	public void addTurtleFile() {
		// update count
		count = count + 1;
		// create a subPane
		HBox subPane = new HBox();
		Image tur = new Image(getClass().getResourceAsStream("turtleButton.png"));
		subPane.getChildren().add(new ImageView(tur));
		Label name = new Label(" Turtle ");
		Label index = new Label(Integer.toString(count));
		name.setFont(new Font(11));
		index.setFont(new Font(11));
		subPane.getChildren().add(name);
		subPane.getChildren().add(index);

		Label gitControl = new Label(" [GUI master]");
		gitControl.setTextFill(Color.GOLDENROD);
		gitControl.setFont(new Font(11));
		subPane.getChildren().add(gitControl);
		gitControl.setAlignment(Pos.BASELINE_CENTER);

		paneListView.getItems().add(subPane);

		subPane.setAlignment(Pos.BOTTOM_LEFT);
		paneListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int test = Integer
						.parseInt(((Label) paneListView.getSelectionModel().getSelectedItem().getChildren().get(2))
								.getText().toString());
				app.changeTurtle(test - 1);
				app.updateTurtleProperties();
				app.changeTurtleImages(test - 1);
			}
		});

	}

	public void printIndex() {
		paneListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("clicked on "
						+ ((Label) paneListView.getSelectionModel().getSelectedItem().getChildren().get(2)).getText());
			}
		});
	}

}
