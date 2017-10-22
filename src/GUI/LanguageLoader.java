package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LanguageLoader extends HBox {

	private static final int MAINWIDTH = 800;
	private static final int MAINHEIGHT = 600;
	private static final Paint BACKGROUND = Color.LIGHTBLUE;
	private static final String RESOURCE_DIR = System.getProperty("user.dir") + "/src/resources";
	private ComboBox lanLoader = new ComboBox<String>();
	private Button goButton;
	private ObservableList<String> LanList;
	private Stage stage;

	public LanguageLoader(double xPos, double yPos, Stage currentStage) {
		stage = currentStage;
		this.setLayoutX(xPos);
		this.setLayoutY(yPos);
		init();
	}

	private void init() {
		File folder = new File(RESOURCE_DIR + "/languages");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		for(File file: listOfFiles) {
			fileNames.add(file.getName().replaceAll(".properties", ""));
		}
		LanList = FXCollections.observableArrayList(fileNames);
		goButton = new Button("GO");
		lanLoader.setPromptText("Choose Language");
		lanLoader.setEditable(true);
		lanLoader.setVisibleRowCount(3);
		lanLoader.setItems(LanList);
		this.getChildren().add(lanLoader);
		this.getChildren().add(goButton);

		// Deal With Switch Scene
		goButton.setOnAction((event) -> {
			switchScene((String) lanLoader.getValue());
		});
	}

	private void switchScene(String inputLanguage) {
		ScreenDisplay myScene = new MainScreen(MAINWIDTH, MAINHEIGHT, BACKGROUND, inputLanguage);
		// center the scene
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX(primaryScreenBounds.getWidth() / 2 - MAINWIDTH / 2);
		stage.setY(primaryScreenBounds.getHeight() / 2 - MAINHEIGHT / 2);
		stage.setScene(myScene.getScene());
	}

}
