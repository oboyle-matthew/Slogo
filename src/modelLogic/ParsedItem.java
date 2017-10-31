package modelLogic;

/**
 * The abstract class representing a parsed item from a user input string
 * @author Walker and Simran
 */
public abstract class ParsedItem {

/* Final Variables */
	protected static final String COMMAND = "command";
	protected static final String BRACKET_PARAM = "bracket parameter";
	protected static final String REGULAR_PARAM = "regular parameter";
	protected static final String USER_COMMAND = "regular parameter";

/* Instance Variables */
	protected String myString; 
	
	/**
	 * An abstract method that returns the type of a parsed item, which should
	 * be one of the final variables above 
	 * @return A {@code String} representing the type of the ParsedItem
	 */
	public abstract String getItemType();
	
	/**
	 * @return A copy of the {@code ParsedItem}
	 */
	public abstract ParsedItem getCopy();
	
	@Override
	public String toString() {
		return myString;
	}
}
