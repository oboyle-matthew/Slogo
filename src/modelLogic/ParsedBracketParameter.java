package modelLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a parsed in parameter for a SLOGO command that was denoted with brackets
 * @author Walker and Simran
 */
public class ParsedBracketParameter extends ParsedItem {
	
/* Instance Variables */
	private List<ParsedItem> myContents;
	private String currLanguage;

	public ParsedBracketParameter(List<ParsedItem> contents, String language) {
		myContents = contents;
		currLanguage = language;
		myString = buildNameString(); 
	}
	
	/**
	 * @return A {@code String} that represents the string representation of this command
	 */
	private String buildNameString() {
		String name = "[ "; 
		for(ParsedItem p : myContents) {
			name = name + p + " "; 
		}
		return name + "]";
	}
	
	/**
	 * Executes the commands contained within this bracket parameter
	 * @param tortuga is the {@code Turtle} to use for executing the commands
	 * @param variables is the {@code Map<String, Double>} containing all the user created
	 * variables to use for execution 
	 * @return A {@code double} that is the value from the last executed command
	 */
	public double executeCommands(Turtle tortuga, Map<String, Double> variables) {
		CommandParser p = new CommandParser(currLanguage);
		return p.executeCommands(myContents, tortuga, variables);
	}

	/**
	 * @return A {@code String[]} that contains the string form of all the commands and constants within this 
	 * BracketParameter 
	 */
	public String[] getStringValues() {
		return myContents.stream().map(item -> item.toString()).toArray(String[]::new);
	}

	@Override
	public String getItemType() {
		return BRACKET_PARAM;
	}

	@Override
	public ParsedItem getCopy() {
		List<ParsedItem> vals = new ArrayList<ParsedItem>();
		for (ParsedItem s : myContents)
			vals.add(s.getCopy());
		return new ParsedBracketParameter(vals, currLanguage);
	}
}