package modelLogic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * The {@code CommandParser} is responsible for parsing and executing SLOGO
 * commands passed to it through its
 * {@code parseInput(String input, Turtle tortuga)} method.
 * 
 * @author Walker and Simran
 */
public class CommandParser {

	/* Final Variables */
	private static final String LAST_BRACKET_PROPERTY = "ListEnd";
	private static final int NOT_ENOUGH_PARAMS = 0;
	private static final int LIST_TOO_SHORT = -1;
	private static final String COMMAND_ITEM = "command";
	protected static final String BRACKET_PARAM = "bracket parameter";
	private static final String WHITESPACE_PROPERTY = "Whitespace";
	private static final String COMMENT_PROPERTY = "Comment";
	private static final String FIRST_BRACKET_PROPERTY = "ListStart";
	private static final String VARIABLE_PROPERTY = "Variable";
	private static final String CONSTANT_PROPERTY = "Constant";
	private static final String PROPERTIES_DNE_ERROR = "Sorry, we can't find the properties for the file: ";
	private static final String RESOURCES_DIR = "src/resources/languages/";
	private static final String SYNTAX_STRING = "Syntax";
	private static final String PROPERTIES_EXTENSION = ".properties";
	private static final String INVALID_INPUT = "The input entered was invalid. Please check the help manual for a list of allowed commands.";

	/* Instance Variables */
	private String languageChoice;
	private Properties currentLanguageProperties;
	private Properties syntaxProperties;
	private Map<String, Double> userVariables;
	private Map<String, CommandNameInfo> userFunctions;

	public CommandParser(String language) {
		languageChoice = language;
		currentLanguageProperties = loadProperties(language);
		userVariables = new HashMap<String, Double>();
		userFunctions = new HashMap<String, CommandNameInfo>();
		syntaxProperties = loadProperties(SYNTAX_STRING);
	}
	
	/**
	 * Releases variable map for the front end so they can create a box to show all
	 * the variables and edit them as the user requires
	 * 
	 * @return
	 */
	public Map<String, Double> getVariableMap() {
		return userVariables;
	}
	
	/**
	 * Executes the SLOGO commands contained within the passed in lists
	 * 
	 * @param items
	 *            is a {@code List<ParsedItem>} that contains all the
	 *            {@code ParsedItem} objects to use for execution
	 * @param tortuga
	 *            is a {@code Turtle} that is the turtle to use with the execution
	 *            of the passed in commands
	 * @param variables
	 *            is a {@code Map<String, Double>} that contains all the <Variable,
	 *            value> pairs for the current user defined variables
	 * @return A {@code double} that is the result of the last executed command
	 */
	public double executeCommands(List<ParsedItem> items, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> functions) {
		userVariables = variables;
		userFunctions = functions;
		double val = cleanList(items);
		List<ParsedItem> oldItems = null;
		while (items.size() > 0 && notAllParams(items)) {
			oldItems = copyParsedList(items);
			boolean succeeded = executeNextCommand(items, writer, functions);
			if (!succeeded || !itemsDiffer(oldItems, items)) {
				createErrorWindow(INVALID_INPUT);
				return -1;
			}
			val = cleanList(items);
		}
		return val;
	}

	/**
	 * Reads in the passed property file, and creates a new {@code Properties}
	 * object from the file
	 * 
	 * @param fileName
	 *            is a {@code String} that corresponds to the name of the properties
	 *            file to use to create the new {@code Properties} object. Note:
	 *            This parameter SHOULD NOT include the ".properties" extension
	 * @return An {@code Properties} object containing the properties of the passed
	 *         in file
	 * @exception An
	 *                {@code Exception} if the file does not exist
	 */
	private Properties loadProperties(String fileName) {
		try {
			InputStream input = new FileInputStream(RESOURCES_DIR + fileName + PROPERTIES_EXTENSION);
			Properties prop = new Properties();
			prop.load(input);
			return prop;
		} catch (Exception e) {
			createErrorWindow(PROPERTIES_DNE_ERROR + fileName);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a list of {@code ParsedItem} objects corresponding to the given input
	 * 
	 * @param input
	 *            is a {@code String} that contains a sequence of valid SLOGO
	 *            commands
	 * @return A {@code List<ParsedItem>} that contains all of the appropriate
	 *         {@code ParsedItem} objects in order
	 */
	private List<ParsedItem> getParsedItemList(String input) {
		List<ParsedItem> list = new ArrayList<>();
		if (!matchesProperty(input, syntaxProperties, COMMENT_PROPERTY)) {
			List<String> tempList = Arrays.asList(input.split(syntaxProperties.getProperty(WHITESPACE_PROPERTY)));
			ArrayList<String> splitInputList = new ArrayList<String>(tempList);
			for (int i = 0; i < splitInputList.size(); i++) {
				if (isCommand(splitInputList.get(i))) {
					list.add(new ParsedCommand(getProperCommandString(splitInputList.get(i))));
				} else if (matchesProperty(splitInputList.get(i), syntaxProperties, CONSTANT_PROPERTY)) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), false));
				} else if (matchesProperty(splitInputList.get(i), syntaxProperties, VARIABLE_PROPERTY)) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), true));
				} else if (matchesProperty(splitInputList.get(i), syntaxProperties, FIRST_BRACKET_PROPERTY)) {
					String innerInput = getBracketContent(splitInputList, i);
					List<ParsedItem> l = getParsedItemList(innerInput);
					list.add(new ParsedBracketParameter(l, languageChoice));
					i = i + totalLength(l) + 1;
				}
			}
		}
		return list;
	}

	/**
	 * Used to find the entire size of a list, including the components of the
	 * brackets
	 * 
	 * @param l
	 * @return
	 */
	private int totalLength(List<ParsedItem> l) {
		int size = 0;
		for (ParsedItem item : l) {
			if (item.getItemType().equals(BRACKET_PARAM)) {
				ParsedBracketParameter bracketItem = (ParsedBracketParameter) item;
				size += bracketItem.getSize();
			}
			size++;
		}
		return size;
	}

	/**
	 * Used to check if two lists of Parsed Items differ. If there is no difference
	 * then you know you are stuck and you can stop trying to run commands.
	 * 
	 * @param oldItems
	 * @param items
	 * @return
	 */
	private boolean itemsDiffer(List<ParsedItem> oldItems, List<ParsedItem> items) {
		if (oldItems == null || oldItems.size() != items.size()) {
			return true;
		}
		for (int i = 0; i < oldItems.size(); i++) {
			ParsedItem old = oldItems.get(i);
			ParsedItem recent = items.get(i);
			if (old.myString != recent.myString) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a copied list of ParsedItems, so that it is easy to document changes
	 * between lists as you try to execute commands.
	 * 
	 * @param items
	 * @return
	 */
	private List<ParsedItem> copyParsedList(List<ParsedItem> items) {
		List<ParsedItem> copy = new ArrayList<ParsedItem>();
		for (ParsedItem item : items) {
			copy.add(item.getCopy());
		}
		return copy;
	}

	/**
	 * Checks if the passed in list contains only param objects
	 * 
	 * @param items
	 *            is a {@code List<ParsedItem>} that is the list you want to check
	 * @return {@code true} if there exists a non-parameter object, and false
	 *         otherwise
	 */
	private boolean notAllParams(List<ParsedItem> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemType().equalsIgnoreCase(COMMAND_ITEM))
				return true;
		}
		return false;
	}

	/**
	 * Parses and executes the given SLOGO input
	 * 
	 * @param input
	 *            is a {@code String} of SLOGO commands to execute
	 * @param tortuga
	 *            is the {@code Turtle} object to use for the execution of the
	 *            commands
	 */
	public void executeInput(String input, CanvasWriter writer) {
		List<ParsedItem> p = getParsedItemList(input);
		executeCommands(p, writer, userVariables, userFunctions);
	}

	/**
	 * Execute the next command that can be executed in the passed in list
	 * 
	 * @param list
	 *            is a {@code List<ParsedItem>} that contains all the commands and
	 *            current values of the list
	 * @param functions
	 * @param tortuga
	 *            is the {@code Turtle} to use for the execution of commands
	 */
	private boolean executeNextCommand(List<ParsedItem> list, CanvasWriter writer,
			Map<String, CommandNameInfo> functions) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getItemType().equals(COMMAND_ITEM)) {
				ParsedCommand p = (ParsedCommand) list.get(i);
				p.checkUserFunctions(userFunctions);
				if (!p.isCommand())
					return false;
				int canExecute = checkNextParams(list, i, p.getParameterOrder());
				if (canExecute == LIST_TOO_SHORT)
					return false;
				if (canExecute == NOT_ENOUGH_PARAMS)
					continue;
				ParsedItem[] params = new ParsedItem[p.getParameterOrder().length];
				for (int j = 0; j < params.length; j++)
					params[j] = list.remove(i + 1);
				double value = p.execute(params, writer, userVariables, userFunctions);
				list.set(i, new ParsedRegularParameter("" + value, false));
				return true;
			}
		}
		return true;
	}

	/**
	 * Checks the parameters next to the command at the current index
	 * 
	 * @param list
	 *            is a {@code List<ParsedItem>} that contains the current
	 *            {@code ParsedItem} objects for a SLOGO command
	 * @param currIndex
	 *            is an {@code int} representing the index with the list of the
	 *            {@code ParsedCommandItem} whose parameters you want to check
	 * @param paramsNeeded
	 *            is a {@code String[]} that contains the string's representing the
	 *            types of parameters needed for the {@code ParsedCommandItem}
	 *            object's execution
	 * @return An {@code int} that acts as a flag: <br/>
	 *         1 - The proper parameters are available for the command at currIndex
	 *         to execute <br/>
	 *         0 - The proper parameters are NOT available for the command at
	 *         currIndex to execute <br/>
	 *         -1 - There isn't enough room left in the list for the command to be
	 *         able to execute, which is an error
	 */
	private int checkNextParams(List<ParsedItem> list, int currIndex, String[] paramsNeeded) {
		if (currIndex + paramsNeeded.length >= list.size())
			return LIST_TOO_SHORT;
		for (int i = currIndex + 1; i < paramsNeeded.length + currIndex + 1; i++) {
			if (!list.get(i).getItemType().equals(paramsNeeded[i - currIndex - 1]))
				return NOT_ENOUGH_PARAMS;
		}
		return 1;
	}

	/**
	 * Cleans the constants left at the front of the list of SLOGO commands that
	 * were left as the result of previous command executions because these
	 * constants cannot be used again
	 * 
	 * @param list
	 *            is a {@code List<ParsedItem>} that represents the current state of
	 *            execution for a SLOGO command
	 * @return A {@code double} that is the value of the last constant cleaned from
	 *         the list
	 */
	private double cleanList(List<ParsedItem> list) {
		ParsedItem curr = list.get(0);
		double val = 0;
		if (list.size() == 1 && !notAllParams(list))
			return Double.parseDouble(((ParsedRegularParameter) curr).toString());
		while ((list.size() > 1 && !curr.getItemType().equals(COMMAND_ITEM)) && !curr.myString.contains(":")) {
			list.remove(0);
			val = Double.parseDouble(((ParsedRegularParameter) curr).toString());
			curr = list.get(0);
		}
		return val;
	}

	/**
	 * Retrieves the contents within a pair of brackets in a list
	 * 
	 * @param splitInputlist
	 *            is a {@code List<ParsedItem>} that represents the current state of
	 *            execution for a SLOGO command
	 * @param currIndex
	 *            is a {@code int} that represents the index of the first bracket
	 *            for a pair of brackets within the list
	 * @return A {@code String} that contains everything within the brackets
	 */
	private String getBracketContent(List<String> splitInputList, int currIndex) {
		String s = "";
		int bracketNum = 1;
		int index = currIndex + 1;
		while (bracketNum != 0) {
			String curr = splitInputList.get(index);
			if (matchesProperty(curr, syntaxProperties, FIRST_BRACKET_PROPERTY)) {
				bracketNum++;
			} else if (matchesProperty(curr, syntaxProperties, LAST_BRACKET_PROPERTY)) {
				bracketNum--;
				if (bracketNum == 0) {
					break;
				}
			}
			s = s + " " + curr;
			index++;
		}
		return s;
	}

	/**
	 * Determines if the passed in input string is a SLOGO command or not
	 * 
	 * @param input
	 *            is a {@code String} that represents the input to check
	 * @return {@code true} if the string is a command, and false otherwise
	 */
	private boolean isCommand(String input) {
		return !matchesProperty(input, syntaxProperties, CONSTANT_PROPERTY)
				&& matchesProperty(input, syntaxProperties, "Command");
	}

	/**
	 * Retrieves the proper command string from the current language properties
	 * object
	 * 
	 * @param inputCommand
	 *            is a {@code String} that represents the command you want the
	 *            proper name for but in the current language being used
	 * @return A {@code String} that contains the proper name of the command which
	 *         can then be used to create a new {@code ParsedCommand} object
	 */
	private String getProperCommandString(String inputCommand) {
		inputCommand = inputCommand.toLowerCase();
		for (Object key : currentLanguageProperties.keySet()) {
			if (matchesProperty(inputCommand, currentLanguageProperties, (String) key))
				return (String) key;
		}
		return inputCommand;
	}

	/**
	 * Determines if the string passed in matches the given property in the
	 * properties list
	 * 
	 * @param str
	 *            is a {@code String} that represents the string being checked
	 * @param p
	 *            is a {@code Properties} object that contains the property passed
	 *            in the third parameter
	 * @param property
	 *            is a {@code String} that contains the property that you want to
	 *            see if the str matches
	 * @return {@code true} if the input string does indeed match the property, and
	 *         false otherwise
	 */
	private boolean matchesProperty(String str, Properties p, String property) {
		Pattern pattern = Pattern.compile(p.getProperty(property));
		Matcher m = pattern.matcher(str);
		return m.matches();
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

}