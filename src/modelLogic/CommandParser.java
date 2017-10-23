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
					list.add(new ParsedCommand(newCommand));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, "Constant")) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), false));
				} else if(matchesProperty(splitInputList.get(i), syntaxProperties, "Variable")) {
					list.add(new ParsedRegularParameter(splitInputList.get(i), true));
				} else {
					String innerInput = getBracketContent(i, splitInputList);
					List<ParsedItem> l = getParsedItemList(innerInput); 
					list.add(new ParsedBracketParameter(l));
					i = i + l.size() + 2; 
				}
			}
		}
		return list;
	}
	
	public void parseInput(String input, Turtle tortuga) {
		List<ParsedItem> p = getParsedItemList(input);
		cleanList(p); 
		while(p.size() > 0) {
			executeNextCommand(p, tortuga); 
			cleanList(p);
		}
	}
	
	private void executeNextCommand(List<ParsedItem> list, Turtle tortuga) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItemType().equals("command")) {
				ParsedCommand p = (ParsedCommand) list.get(i);
				int canExecute = checkNextParams(list, i, p.getParameterOrder());
				if(canExecute == -1) return; 
				if(canExecute == 0) continue; 
				ParsedItem[] params = new ParsedItem[p.getParameterOrder().length];
				for(int j = 0; j < params.length; j++)
					params[j + i + 1] = list.remove(i + 1); 
				double value = p.execute(params, tortuga, userVariables);
				list.set(i, new ParsedRegularParameter("" + value, false));
			}
		}
	}
	
	private int checkNextParams(List<ParsedItem> list, int currIndex, String[] paramsNeeded) {
		if(currIndex + paramsNeeded.length >= list.size()) return -1; 
		for(int i = currIndex + 1; i < paramsNeeded.length; i++) {
			if(!list.get(i).getItemType().equals(paramsNeeded[i - currIndex - 1])) return 0;
		}
		return 1; 
	}
	
	private void cleanList(List<ParsedItem> list) {
		ParsedItem curr = list.get(0); 
		while(!curr.getItemType().equals("command")) {
			list.remove(0);
			curr = list.get(0);
		}
	}
	
	
	private String getBracketContent(int currIndex, ArrayList<String> splitInputList) {
		String s = ""; 
		int bracketNum = 1; 
		int index = currIndex + 1; 
		while(bracketNum != 0) {
			String curr = splitInputList.get(index);
			if(matchesProperty(curr, syntaxProperties, "LeftBracket")) {
				bracketNum++;
			} else if(matchesProperty(curr, syntaxProperties, "RightBracket")) {
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
	
/*	public String[] parseInput(String input, Turtle tortuga) {
		String[] ret = new String[] {};
		// Check to make sure the input isn't a comment 
		if(!matchesProperty(input, syntaxProperties, "Comment")) {
			List<String> tempList = Arrays.asList(input.split(syntaxProperties.getProperty("Whitespace")));
			ArrayList<String> splitInputList = new ArrayList<String>(tempList);
			while(notAllNumbers(splitInputList)) {
				for(int i = 0; i < splitInputList.size(); i++) {
					if(isCommand(splitInputList.get(i))) {
						
						boolean success = createCommand(splitInputList, i , tortuga); 
						//boolean success = runSimpleCommand(splitInputList, i, tortuga);
						if(!success) return new String[] {}; 
					}
				}
			}
			ret = splitInputList.toArray(new String[0]);
		}
		return ret; 
	}
	
	private String[] parseNextParameters(int num, int splitInputList) {
	
		String[] params = new String[num];
	}
	
	private boolean createCommand(ArrayList<String> splitInputList, int currIndex, Turtle tortuga) {
		String currCommand = splitInputList.get(currIndex);
		switch(currCommand) {
			case("MakeVariable"): return makeVariable(splitInputList, currIndex); 
			case("Repeat"): return makeRepeat(splitInputList, currIndex);
			case("DoTimes"):
			case("For"):
			case("If"):
			case("IfElse"):
			case("MakeUserInstruction"): 
			default: return runSimpleCommand(splitInputList, currIndex, tortuga);
		} 
	}
	
	private boolean makeDoTimes()
	
	
	private boolean makeRepeat(ArrayList<String> splitInputList, int currIndex) {
		if(currIndex + 3 >= splitInputList.size()) return false; 
		String valueStr = splitInputList.get(currIndex + 1); 
		double value = 0;
		if(matchesProperty(valueStr,syntaxProperties,"Constant")) {
			value = Double.parseDouble(valueStr); 
		} else if(matchesProperty(valueStr,syntaxProperties,"Variable")) {
			value = userVariables.getOrDefault(valueStr, DEFAULT_VARIABLE_VALUE);
		} else {
			return true; 
		}
		
		String firstBracket = splitInputList.get(currIndex + 2);
		if(!matchesProperty(firstBracket, syntaxProperties, "ListStart")) return false; 
		int bracketNum = 1; 
		int index = currIndex + 3;
		ArrayList<String> innerContents = new ArrayList<String>();
		
		while(bracketNum != 0 && currIndex != splitInputList.size()) {
			String currString = splitInputList.get(index);
			if(matchesProperty(firstBracket, syntaxProperties, "ListStart")) {
				bracketNum++;
			} else if(matchesProperty(firstBracket, syntaxProperties, "ListEnd")) {
				bracketNum--; 
			} else {
				innerContents.add(currString); 
			}
			index++; 
		}
		
		// Remove the statement
		for(int i = 0; i < innerContents.size() + 3; i++) {
			splitInputList.remove(currIndex); 
		}
		
		for(int i = 0; i < value; i++) {
			for(int j = 0; j < innerContents.size(); j++) {
				splitInputList.add(currIndex, innerContents.get(j));
				currIndex++; 
			}
		}
		return true; 
	}
	
	
	private boolean makeVariable(ArrayList<String> splitInputList, int currIndex) {
		if(currIndex + 2 >= splitInputList.size()) return false; 
		String variableName = splitInputList.get(currIndex + 1);
		if(!matchesProperty(variableName, syntaxProperties, "Variable")) return false; 
		String valueStr = splitInputList.get(currIndex + 2);
		double value = 0;
		if(matchesProperty(valueStr,syntaxProperties,"Constant")) {
			value = Double.parseDouble(valueStr); 
		} else if(matchesProperty(valueStr,syntaxProperties,"Variable")) {
			value = userVariables.getOrDefault(valueStr, DEFAULT_VARIABLE_VALUE);
		} else {
			return true; 
		}
		splitInputList.set(currIndex, "" + value);
		for(int i = 0; i < 2; i++)
			splitInputList.remove(currIndex + 1); 
		userVariables.put(variableName, value);
		return true; 
	}
	
	
	
	private boolean runSimpleCommand(ArrayList<String> splitInputList, int i, Turtle tortuga) {
		ExecutableCommand command = createExecutableCommand(getProperCommandString(splitInputList.get(i))); 
		if(command == null) return false; 
		if(i + command.paramNumber() >= splitInputList.size()) return false; 
		
		// Check if enough parameters follow the current command
		List<Double> params = new ArrayList<Double>();
		for(int j = 0; j < command.paramNumber(); j++) {
			String nextInput = splitInputList.get(i + j + 1);
			if(matchesProperty(nextInput, syntaxProperties, "Constant")) {
				params.add(Double.parseDouble(nextInput));
			} else if (matchesProperty(nextInput, syntaxProperties, "Variable")) {
				params.add(userVariables.getOrDefault(nextInput, DEFAULT_VARIABLE_VALUE)); 
			}
		}
		
		if(params.size() != command.paramNumber()) return true; 
		
		// Execute the command if it has the correct number of params
		double result = command.execute(tortuga, params);  
	
		
		// Clean up the splitInputList
		splitInputList.set(i, "" + result); 
		for(int j = 0; j < command.paramNumber(); j++)
			splitInputList.remove(i+1); 
		return true;
	}
	*/
	
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

	public static void main(String[] args) {
		CommandParser p = new CommandParser("English");
		
		Turtle t = new Turtle();
		String command = "fd 10 fd 10 sum 10 10";
		p.parseInput(command, t);
		//System.out.println(Arrays.toString(ret));
	}
<<<<<<< HEAD
	

=======
>>>>>>> c68d3b3e4286dbcb95794a25209fd7bc382130f1
}