package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String language = "Instructions";
	private static final int MAINWIDTH = 800;
	private static final int MAINHEIGHT = 600;
	private static final Paint BACKGROUND = Color.LIGHTYELLOW;
	private static final String RESOURCE_DIR = System.getProperty("user.dir") + "/src/resources/languages";
	private ComboBox<String> lanLoader = new ComboBox<String>();
	private ResourceBundle myResources;
	private Button goButton;
	private Stage stage;

	public LanguageLoader(double xPos, double yPos, Stage currentStage) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		stage = currentStage;
		this.setLayoutX(xPos);
		this.setLayoutY(yPos);
		init();
	}

	private void init() {
		List<String> fileNames = createFileList();
		ObservableList<String> LanList = FXCollections.observableArrayList(fileNames);
		createGoButton();
		createLanguageLoader(LanList);
		this.getChildren().add(lanLoader);
		this.getChildren().add(goButton);
	}

	private void createGoButton() {
		goButton = new Button(myResources.getString("Go"));
		goButton.setOnAction((event) -> {
			switchScene((String) lanLoader.getValue());
		});
	}

	private List<String> createFileList() {
		File folder = new File(RESOURCE_DIR);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		for (File file : listOfFiles) {
			if(!file.getName().equals("Syntax.properties"))
				fileNames.add(file.getName().replaceAll(".properties", ""));
		}
		return fileNames;
	}

	private void createLanguageLoader(ObservableList<String> LanList) {
		lanLoader.setPromptText(myResources.getString("LanguagePrompt"));
		lanLoader.setEditable(true);
		lanLoader.setVisibleRowCount(3);
		lanLoader.setItems(LanList);
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
