package modelLogic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.CommandCreator;
import commands.ExecutableCommand;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class CommandParser {

	/* Final Variables */
	private static final String PROPERTIES_DNE_ERROR = "Sorry, we can't find the properties for the file: ";
	private static final String RESOURCES_DIR = "src/resources/languages/";
	private static final String SYNTAX_STRING = "Syntax";
	private static final String PROPERTIES_EXTENSION = ".properties";
	private static final Double DEFAULT_VARIABLE_VALUE = 0.0;
	private static final String[] NON_NORMAL_COMMANDS = { "MakeVariable", "Repeat", "DoTimes", "For", "If", "IfElse",
			"MakeUserInstruction" };

	/* Instance Variables */
	private String languageChoice;
	private Properties currentLanguageProperties;
	private Properties syntaxProperties;
	private Map<String, Double> currentUserVariables;

	public CommandParser(String language) {
		languageChoice = language;
		currentLanguageProperties = loadProperties(language);
		currentUserVariables = new HashMap<String, Double>();
		syntaxProperties = loadProperties(SYNTAX_STRING);
	}

	public static void main(String[] args) {
		CommandParser p = new CommandParser("English");
		Map<String, List<?>> m = p.getCommandsFromInput("fd sum :shit sum 10 sum 20 20");
		CommandCreator c = new CommandCreator();
		Turtle t = new Turtle();
		List<String> commands = (List<String>) m.get("commands");
		List<Double> constants = (List<Double>) m.get("constants");
		while (!commands.isEmpty()) {
			System.out.print(commands.get(0) + " was called, and returned: ");
			ExecutableCommand nextCommand = c.getExecutableCommand(commands.remove(0));
			Double valueReturned = nextCommand.execute(t, constants);
			constants.add(0, valueReturned);
			System.out.println(valueReturned);
		}

	}

	public Map<String, List<?>> getCommandsFromInput(String input) {
		input = input.trim();
		Map<String, List<?>> ret = new HashMap<String, List<?>>();
		LinkedList<String> commands = new LinkedList<String>();
		LinkedList<Double> constants = new LinkedList<Double>();
		if (!matchesProperty(input, syntaxProperties, "Comment")) {
			List<String> temp = Arrays.asList(input.split(syntaxProperties.getProperty("Whitespace")));
			ArrayList<String> splitInput = new ArrayList<String>(temp);
			while (!splitInput.isEmpty()) {
				String textItem = splitInput.remove(0);
				if (matchesProperty(textItem, syntaxProperties, "Constant")) {
					constants.addFirst(Double.parseDouble(textItem));
				} else if (matchesProperty(textItem, syntaxProperties, "Variable")) {
					constants.addFirst(currentUserVariables.getOrDefault(textItem, DEFAULT_VARIABLE_VALUE));
				} else if (matchesProperty(textItem, syntaxProperties, "Command")) {
					List<String> commandsToAdd = handleCommandInput(textItem, splitInput);
					for (String s : commandsToAdd) {
						commands.addFirst(s);
					}
				}
			}
		}
		ret.put("commands", commands);
		ret.put("constants", constants);
		return ret;
	}

	private List<String> handleCommandInput(String currCommand, List<String> remainingCommands) {
		List<String> commandsToAdd = new ArrayList<String>();
		String properCommand = "";
		for (Object command : currentLanguageProperties.keySet()) {
			if (matchesProperty(currCommand, currentLanguageProperties, (String) command)) {
				properCommand = (String) command;
				break;
			}
		}
		if (properCommand.equals("")) {
			return null;
		} else if (properCommand.equals(anObject))
			return commandsToAdd;

	}

	private boolean matchesProperty(String str, Properties p, String property) {
		Pattern pattern = Pattern.compile(p.getProperty(property));
		Matcher m = pattern.matcher(str);
		return m.find();
	}

	/**
	 * Creates an error popup window
	 * 
	 * @param errorMessage
	 *            is a {@code String} that is the error message you want to display
	 *            within the popup window
	 */
	private void createErrorWindow(String errorMessage) {
		Dialog<String> errorPopup = new Dialog<String>();
		errorPopup.setContentText(errorMessage);
		ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		errorPopup.getDialogPane().getButtonTypes().add(closeButton);
		errorPopup.showAndWait();
	}

	private Properties loadProperties(String fileName) {
		try {
			InputStream input = new FileInputStream(RESOURCES_DIR + fileName + PROPERTIES_EXTENSION);
			Properties prop = new Properties();
			prop.load(input);
			return prop;
		} catch (Exception e) {
			createErrorWindow(PROPERTIES_DNE_ERROR + fileName);
			e.printStackTrace();
		}
		return null;
	}
}
