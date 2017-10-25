package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BackgroundColorButton extends ComboBox<String> {
	
	private GUIDelegate app;
	
	public BackgroundColorButton(GUIDelegate app) {
		this.app = app;
		this.setPrefWidth(200);
		this.setLayoutX(600);
		this.setLayoutY(200);
		ArrayList<String> colors = new ArrayList<>(Arrays.asList("red", "orange", "green", "blue", "yellow"));
		ObservableList<String> colorList = FXCollections.observableArrayList(colors);
		this.setPromptText("Choose a background color");
		this.setEditable(true);
		this.setVisibleRowCount(3);
		this.setItems(colorList);
	}
	
	public void changeBackgroundColor(String color) {
		System.out.println("Test");
	}
	
//	private void createLanguageLoader(ObservableList<String> LanList) {
//		lanLoader.setPromptText(myResources.getString("LanguagePrompt"));
//		lanLoader.setEditable(true);
//		lanLoader.setVisibleRowCount(3);
//		lanLoader.setItems(LanList);
//	}
//
//		
//	private void init() {
//		List<String> fileNames = createFileList();
//		ObservableList<String> LanList = FXCollections.observableArrayList(fileNames);
//		createGoButton();
//		createLanguageLoader(LanList);
//		this.getChildren().add(lanLoader);
//		this.getChildren().add(goButton);
//	}
//
//	private void createGoButton() {
//		goButton = new Button(myResources.getString("Go"));
//		goButton.setOnAction((event) -> {
//			switchScene((String) lanLoader.getValue());
//		});
//	}
//
//	private List<String> createFileList() {
//		File folder = new File(RESOURCE_DIR);
//		File[] listOfFiles = folder.listFiles();
//		ArrayList<String> fileNames = new ArrayList<String>();
//		for (File file : listOfFiles) {
//			if(!file.getName().equals("Syntax.properties"))
//				fileNames.add(file.getName().replaceAll(".properties", ""));
//		}
//		return fileNames;
//	}
//
//	
//
//	private void switchScene(String inputLanguage) {
//		ScreenDisplay myScene = new MainScreen(MAINWIDTH, MAINHEIGHT, BACKGROUND, inputLanguage);
//		// center the scene
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX(primaryScreenBounds.getWidth() / 2 - MAINWIDTH / 2);
//		stage.setY(primaryScreenBounds.getHeight() / 2 - MAINHEIGHT / 2);
//		stage.setScene(myScene.getScene());
//	}
}
