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
 * The {@code CommandParser} is responsible for parsing and executing SLOGO commands passed to it
 * through its {@code parseInput(String input, Turtle tortuga)} method.
 * @author Walker and Simran
 */
public class CommandParser {
/* Final Variables */
	private static final String COMMAND_ITEM = "command";
	private static final String WHITESPACE_PROPERTY = "Whitespace";
	private static final String COMMENT_PROPERTY = "Comment";
	private static final String FIRST_BRACKET_PROPERTY = "ListStart";
	private static final String VARIABLE_PROPERTY = "Variable";
	private static final String CONSTANT_PROPERTY = "Constant";
	private static final String PROPERTIES_DNE_ERROR = "Sorry, we can't find the properties for the file: ";
	private static final String RESOURCES_DIR = "src/resources/languages/";
	private static final String SYNTAX_STRING = "Syntax";
	private static final String PROPERTIES_EXTENSION = ".properties";

/* Instance Variables */
	private String languageChoice;
	private Properties currentLanguageProperties;
	private Properties syntaxProperties;
	private Map<String, Double> userVariables;

	public CommandParser(String language) {
		languageChoice = language;
		currentLanguageProperties = loadProperties(language);
		userVariables = new HashMap<String, Double>();
		syntaxProperties = loadProperties(SYNTAX_STRING);
	}

	
	/**
	 * Reads in the passed property file, and creates a new {@code Properties} object from the file
	 * @param fileName is a {@code String} that corresponds to the name of the properties file to use
	 * to create the new {@code Properties} object. Note: This parameter SHOULD NOT include the ".properties"
	 * extension 
	 * @return An {@code Properties} object containing the properties of the passed in file
	 * @exception An {@code Exception} if the file does not exist
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
		}
		return null;
	}
	
	
	/**
	 * Creates a list of {@code ParsedItem} objects corresponding to the given input
	 * @param input is a {@code String} that contains a sequence of valid SLOGO commands
	 * @return A {@code List<ParsedItem>} that contains all of the appropriate {@code ParsedItem} objects in order
	 */
	private List<ParsedItem> getParsedItemList(String input) {
		List<ParsedItem> list = new ArrayList<>(); 
		if(!matchesProperty(input, syntaxProperties, COMMENT_PROPERTY)) {
			List<String> tempList = Arrays.asList(input.split(syntaxProperties.getProperty(WHITESPACE_PROPERTY)));
			ArrayList<String> splitInputList = new ArrayList<String>(tempList);
			for(int i = 0; i < splitInputList.size(); i++) {
				if(isCommand(splitInputList.get(i))) {
					list.add(new ParsedCommand(getProperCommandString(splitInputList.get(i))));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, CONSTANT_PROPERTY)) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), false));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, VARIABLE_PROPERTY)) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), true));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, FIRST_BRACKET_PROPERTY)) {
					String innerInput = getBracketContent(splitInputList, i);
					List<ParsedItem> l = getParsedItemList(innerInput); 
					list.add(new ParsedBracketParameter(l, languageChoice));
					i = i + l.size() + 1; 
				}
			}
		}
		return list;
	}

	
	/**
	 * Executes the SLOGO commands contained within the passed in lists
	 * @param items is a {@code List<ParsedItem>} that contains all the {@code ParsedItem} objects to use for execution 
	 * @param tortuga is a {@code Turtle} that is the turtle to use with the execution of the passed in commands
	 * @param variables is a {@code Map<String, Double>} that contains all the <Variable, value> pairs for the current
	 * user defined variables
	 * @return A {@code double} that is the result of the last executed command 
	 */
	public double executeCommands(List<ParsedItem> items, Turtle tortuga, Map<String, Double> variables) {
		userVariables = variables;
		double val = cleanList(items); 
		while(items.size() > 0 && notAllParams(items)) {
			executeNextCommand(items, tortuga); 
			val = cleanList(items);
		}
		return val;
	}
	
	/**
	 * Checks if the passed in list contains only param objects
	 * @param items is a {@code List<ParsedItem>} that is the list you want to check
	 * @return {@code true} if there exists a non-parameter object, and false otherwise
	 */
	private boolean notAllParams(List<ParsedItem> items) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getItemType().equalsIgnoreCase(COMMAND_ITEM)) return true;
		}
		return false;
	}
	
	/**
	 * Parses and executes the given SLOGO input
	 * @param input is a {@code String} of SLOGO commands to execute 
	 * @param tortuga is the {@code Turtle} object to use for the execution of the commands
	 */
	public void executeInput(String input, Turtle tortuga) {
		List<ParsedItem> p = getParsedItemList(input);
		executeCommands(p, tortuga, userVariables);
	}
	
	/**
	 * Execute the next command that can be executed in the passed in list 
	 * @param list is a {@code List<ParsedItem>} that contains all the commands and current values of the list
	 * @param tortuga is the {@code Turtle} to use for the execution of commands
	 */
	private void executeNextCommand(List<ParsedItem> list, Turtle tortuga) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItemType().equals(COMMAND_ITEM)) {
				ParsedCommand p = (ParsedCommand) list.get(i);
				int canExecute = checkNextParams(list, i, p.getParameterOrder());
				if(canExecute == -1) return; 
				if(canExecute == 0) continue; 
				ParsedItem[] params = new ParsedItem[p.getParameterOrder().length];
				for(int j = 0; j < params.length; j++)
					params[j] = list.remove(i + 1); 
				double value = p.execute(params, tortuga, userVariables);
				list.set(i, new ParsedRegularParameter("" + value, false));
				return; 
			}
		}
	}

	
	/**
	 * Checks the parameters next to the command at the current index 
	 * @param list is a {@code List<ParsedItem>} that contains the current {@code ParsedItem} objects 
	 * for a SLOGO command 
	 * @param currIndex is an {@code int} representing the index with the list of the {@code ParsedCommandItem} whose parameters
	 * you want to check 
	 * @param paramsNeeded is a {@code String[]} that contains the string's representing the types of parameters needed for the 
	 * {@code ParsedCommandItem} object's execution 
	 * @return An {@code int} that acts as a flag: <br/>
	 * 		1 - The proper parameters are available for the command at currIndex to execute <br/>
	 * 		0 - The proper parameters are NOT available for the command at currIndex to execute <br/>
	 * 	   -1 - There isn't enough room left in the list for the command to be able to execute, which is an error  
	 */
	private int checkNextParams(List<ParsedItem> list, int currIndex, String[] paramsNeeded) {
		if(currIndex + paramsNeeded.length >= list.size()) return -1; 
		for(int i = currIndex + 1; i < paramsNeeded.length + currIndex + 1; i++) {
			if(!list.get(i).getItemType().equals(paramsNeeded[i - currIndex - 1])) return 0;
		}
		return 1; 
	}
	
	
	/**
	 *  Cleans the constants left at the front of the list of SLOGO commands that were left as the 
	 *  result of previous command executions because these constants cannot be used again 
	 * @param list is a {@code List<ParsedItem>} that represents the current state of execution for a SLOGO command
	 * @return A {@code double} that is the value of the last constant cleaned from the list
	 */
	private double cleanList(List<ParsedItem> list) {
		ParsedItem curr = list.get(0); 
		double val = 0;
		if(list.size() == 1 && !notAllParams(list)) return Double.parseDouble( ((ParsedRegularParameter)curr).toString());
		while(list.size() > 1 && !curr.getItemType().equals(COMMAND_ITEM)) {
			list.remove(0);
			val = Double.parseDouble( ((ParsedRegularParameter)curr).toString());
			curr = list.get(0);
		}
		return val;
	}
	
	/**
	 * Retrieves the contents within a pair of brackets in a list
	*  @param splitInputlist is a {@code List<ParsedItem>} that represents the current state of execution for a SLOGO command
	 * @param currIndex is a {@code int} that represents the index of the first bracket for a pair of brackets within the list
	 * @return
	 */
	private String getBracketContent(List<String> splitInputList, int currIndex) {
		String s = ""; 
		int bracketNum = 1; 
		int index = currIndex + 1; 
		while(bracketNum != 0) {
			String curr = splitInputList.get(index);
			if(matchesProperty(curr, syntaxProperties, FIRST_BRACKET_PROPERTY)) {
				bracketNum++;
			} else if(matchesProperty(curr, syntaxProperties, "ListEnd")) {
				bracketNum--;
			} else {
				s = s + " " + curr; 
			}
			index++;
		}
		return s;
	}
	
	/**
	 * Determines if the passed in input string is a SLOGO command or not
	 * @param input is a {@code String} that represents the input to check
	 * @return {@code true} if the string is a command, and false otherwise
	 */
	private boolean isCommand(String input) {
		return !matchesProperty(input, syntaxProperties, CONSTANT_PROPERTY) && matchesProperty(input, syntaxProperties, "Command");
	}

	/**
	 * Retrieves the proper command string from the current language properties object
	 * @param inputCommand is a {@code String} that represents the command you want the proper name
	 * for but in the current language being used
	 * @return A {@code String} that contains the proper name of the command which can then be used to 
	 * create a new {@code ParsedCommand} object
	 */
	private String getProperCommandString(String inputCommand) {
		for (Object key : currentLanguageProperties.keySet()) {
			if (matchesProperty(inputCommand, currentLanguageProperties, (String) key))
				return (String) key;
		}
		return null;
	}

	/**
	 * Determines if the string passed in matches the given property in the properties list 
	 * @param str is a {@code String} that represents the string you're checking 
	 * @param p is a {@code Properties} object that contains the property passed in the third parameter
	 * @param property is a {@code String} that contains the property that you want to see if the str matches
	 * @return {@code true} if the input string does indeed match the property, and false otherwise
 	 */
	private boolean matchesProperty(String str, Properties p, String property) {
		Pattern pattern = Pattern.compile(p.getProperty(property));
		Matcher m = pattern.matcher(str);
		return m.matches();
	}

	/**
	 * Creates an error popup window
	 * @param errorMessage is a {@code String} that is the error message you want to display
	 * within the popup window
	 */
	private void createErrorWindow(String errorMessage) {
		Dialog<String> errorPopup = new Dialog<String>();
		errorPopup.setContentText(errorMessage);
		ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		errorPopup.getDialogPane().getButtonTypes().add(closeButton);
		errorPopup.showAndWait();
	}
}