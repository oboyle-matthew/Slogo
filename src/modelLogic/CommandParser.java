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

import commands.ExecutableCommand;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class CommandParser {

	/* Final Variables */
	private static final String PROPERTIES_DNE_ERROR = "Sorry, we can't find the properties for the file: ";
	private static final String RESOURCES_DIR = "src/resources/languages/";
	private static final String SYNTAX_STRING = "Syntax";
	private static final String PARAMETER_STRING = "CommandParameters";
	private static final String PROPERTIES_EXTENSION = ".properties";
	private static final Double DEFAULT_VARIABLE_VALUE = 0.0;

	/* Instance Variables */
	private String languageChoice;
	private Properties currentLanguageProperties;
	private Properties syntaxProperties;
	private Properties parameterProperties; 
	private Map<String, Double> userVariables;

	public CommandParser(String language) {
		languageChoice = language;
		currentLanguageProperties = loadProperties(language);
		userVariables = new HashMap<String, Double>();
		syntaxProperties = loadProperties(SYNTAX_STRING);
		parameterProperties = loadProperties(PARAMETER_STRING); 
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
	
	private List<ParsedItem> getParsedItemList(String input) {
		List<ParsedItem> list = new ArrayList<>(); 
		if(!matchesProperty(input, syntaxProperties, "Comment")) {
			List<String> tempList = Arrays.asList(input.split(syntaxProperties.getProperty("Whitespace")));
			ArrayList<String> splitInputList = new ArrayList<String>(tempList);
			for(int i = 0; i < splitInputList.size(); i++) {
				if(isCommand(splitInputList.get(i))) {
					ExecutableCommand newCommand = createExecutableCommand(getProperCommandString(splitInputList.get(i)));
					list.add(new ParsedCommand(newCommand, getProperCommandString(splitInputList.get(i))));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, "Constant")) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), false));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, "Variable")) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), true));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, "ListStart")) {
					String innerInput = getBracketContent(i, splitInputList);
					List<ParsedItem> l = getParsedItemList(innerInput); 
					list.add(new ParsedBracketParameter(l, languageChoice));
					i = i + l.size() + 1; 
				}
			}
		}
		return list;
	}
	
	public double executeCommands(List<ParsedItem> items, Turtle tortuga, Map<String, Double> variables) {
		userVariables = variables;
		double val = cleanList(items); 
		while(items.size() > 0 && notAllParams(items)) {
			executeNextCommand(items, tortuga); 
			val = cleanList(items);
		}
		return val;
	}
	
	private boolean notAllParams(List<ParsedItem> items) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getItemType().equals("command")) return true;
		}
		return false;
	}
	
	public void parseInput(String input, Turtle tortuga) {
		List<ParsedItem> p = getParsedItemList(input);
		executeCommands(p, tortuga, userVariables);
	}
	
	private void executeNextCommand(List<ParsedItem> list, Turtle tortuga) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItemType().equals("command")) {
//				for(ParsedItem s : list)
//					System.out.print(s.getName() + " ");
//				System.out.println("");
				ParsedCommand p = (ParsedCommand) list.get(i);
				int canExecute = checkNextParams(list, i, p.getParameterOrder());
				if(canExecute == -1) return; 
				if(canExecute == 0) continue; 
				ParsedItem[] params = new ParsedItem[p.getParameterOrder().length];
				for(int j = 0; j < params.length; j++)
					params[j] = list.remove(i + 1); 
				System.out.println(userVariables.get(":repcount"));
				double value = p.execute(params, tortuga, userVariables);
				System.out.println(p.getName() + " returned " + value);
				list.set(i, new ParsedRegularParameter("" + value, false));
//				for(ParsedItem s : list)
//					System.out.print(s.getName() + " ");
//				System.out.println("");
				return; 
			}
		}
	}
	
	private int checkNextParams(List<ParsedItem> list, int currIndex, String[] paramsNeeded) {
		if(currIndex + paramsNeeded.length >= list.size()) return -1; 
		for(int i = currIndex + 1; i < paramsNeeded.length + currIndex + 1; i++) {
			if(!list.get(i).getItemType().equals(paramsNeeded[i - currIndex - 1])) return 0;
		}
		return 1; 
	}
	
	private double cleanList(List<ParsedItem> list) {
		ParsedItem curr = list.get(0); 
		double val = 0;
		if(list.size() == 1 && !notAllParams(list)) return Double.parseDouble( ((ParsedRegularParameter)curr).getValue());
		while(list.size() > 1 && !curr.getItemType().equals("command")) {
			list.remove(0);
			val = Double.parseDouble( ((ParsedRegularParameter)curr).getValue());
			curr = list.get(0);
		}
		return val;
	}
	
	
	private String getBracketContent(int currIndex, ArrayList<String> splitInputList) {
		String s = ""; 
		int bracketNum = 1; 
		int index = currIndex + 1; 
		while(bracketNum != 0) {
			String curr = splitInputList.get(index);
			if(matchesProperty(curr, syntaxProperties, "ListStart")) {
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
	
	private boolean isConstantOrVariable(String input) {
		return matchesProperty(input, syntaxProperties, "Constant") || matchesProperty(input, syntaxProperties, "Variable");
	}
	
	private boolean isCommand(String input) {
		return !matchesProperty(input, syntaxProperties, "Constant") && matchesProperty(input, syntaxProperties, "Command");
	}
	
	private ExecutableCommand createExecutableCommand(String command) {
		try {
			Class<?> c = Class.forName("commands." + command + "Command");
			return (ExecutableCommand) c.newInstance();
		} catch (Exception e) {
			System.out.print("The command: " + command + " could not be found.");
			createErrorWindow("The command: " + command + " could not be found.");
			return null; 
		}
	}
	
	private boolean notAllNumbers(List<String> list) {
		for(String s : list) {
			if(!matchesProperty(s, syntaxProperties, "Constant")) return true;
		}
		return false; 
	}
	

	private String getProperCommandString(String inputCommand) {
		for (Object key : currentLanguageProperties.keySet()) {
			if (matchesProperty(inputCommand, currentLanguageProperties, (String) key))
				return (String) key;
		}
		System.out.println("The command: " + inputCommand + " could not be found.");
		return null;
	}

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

	public static void main(String[] args) {
		CommandParser p = new CommandParser("English");
		
		Turtle t = new Turtle();
		String test = "]";
		String command = "repeat 10 [ sum 10 :repcount ] ";
		p.parseInput(command, t);
		//System.out.println(Arrays.toString(ret));
	}
}