package modelLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a parsed in parameter for a SLOGO command that was denoted with
 * brackets
 * 
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
	 * Executes the commands contained within this bracket parameter
	 * 
	 * @param tortuga
	 *            is the {@code Turtle} to use for executing the commands
	 * @param variables
	 *            is the {@code Map<String, Double>} containing all the user created
	 *            variables to use for execution
	 * @return A {@code double} that is the value from the last executed command
	 */
	public double executeCommands(CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> functions) {
		CommandParser p = new CommandParser(currLanguage);
		return p.executeCommands(myContents, writer, variables, functions);
	}

	/**
	 * @return A {@code String[]} that contains the string form of all the commands
	 *         and constants within this BracketParameter
	 */
	public String[] getStringValues() {
		return myContents.stream().map(item -> item.toString()).toArray(String[]::new);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelLogic.ParsedItem#getItemType()
	 */
	@Override
	public String getItemType() {
		return BRACKET_PARAM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modelLogic.ParsedItem#getCopy()
	 * 
	 * Copies are necessary so for things like repeats. If you execute a bracket
	 * command and not its copy, you lost the actually bracket command when you want
	 * to execute it multiple times. Instead you execute a copy so you have an
	 * initial version to execute again in case you need to.
	 */
	@Override
	public ParsedBracketParameter getCopy() {
		List<ParsedItem> vals = new ArrayList<ParsedItem>();
		for (ParsedItem s : myContents)
			vals.add(s.getCopy());
		return new ParsedBracketParameter(vals, currLanguage);
	}

	/**
	 * Used to get the number of elements in the bracket parameter. This is similar
	 * to the code to calculate the size of the entire list of parsed Items, but
	 * there is no way sensible reason or way to avoid this.
	 * 
	 * @return
	 */
	public int getSize() {
		int size = 0;
		for (ParsedItem item : myContents) {
			if (item.getItemType().equals(BRACKET_PARAM)) {
				ParsedBracketParameter bracketItem = (ParsedBracketParameter) item;
				size += bracketItem.getSize();
			} else {
				size++;
			}
		}
		return size;
	}
	
	/**
	 * @return A {@code String} that represents the string representation of this
	 *         command
	 */
	private String buildNameString() {
		String name = "[ ";
		for (ParsedItem p : myContents)
			name = name + p + " ";
		return name + "]";
	}
}