package modelLogic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;


public class CommandParser {

/* Final Variables */
	private static final String LANGUAGE_DNE_ERROR = "Sorry, we can't find the properties for the language: ";
	private static final String RESOURCES_DIR = "src/resources/languages/";
	private static final String SYNTAX_STRING = "Syntax"; 
	private static final String PROPERTIES_EXTENSION = ".properties";
/* Instance Variables */
	private String languageChoice;
	private Properties currentLanguageProperties;
	private Properties syntaxProperties; 
	
	public CommandParser(String language) {
		languageChoice = language; 
		
		currentLanguageProperties = loadProperties(language);
		syntaxProperties = loadProperties(SYNTAX_STRING);
		
	}
	
	private Properties loadProperties(String language) {
		try {
			InputStream input = new FileInputStream(RESOURCES_DIR + language + PROPERTIES_EXTENSION);
			Properties prop = new Properties(); 
			prop.load(input);
			return prop; 
		} catch (Exception e) {
			createErrorWindow(LANGUAGE_DNE_ERROR + language); 
			e.printStackTrace();
		}
		return null; 
	}
	
	private void createErrorWindow(String errorMessage) {
		Dialog<String> errorPopup = new Dialog<String>();
		errorPopup.setContentText(errorMessage);
		ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		errorPopup.getDialogPane().getButtonTypes().add(closeButton);
		errorPopup.showAndWait();
	}
	
	
	
}
