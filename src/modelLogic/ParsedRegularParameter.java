package modelLogic;

/**
 * Represents a parsed in parameter for a SLOGO command that is a constant not wrapped in brackets
 * @author Walker and Simran
 */
public class ParsedRegularParameter extends ParsedItem {

/* Instance Variables */
	private boolean isVariable;
	
	public ParsedRegularParameter(String value, boolean isVariableStatus) {
		myString = value;
		isVariable = isVariableStatus;
	}
	
	/**
	 * @return {@code true} if the parsed item is a variable (ie. of the form ":var"), 
	 * and false otherwise
	 */
	public boolean isVariable() {
		return isVariable;
	}
	
	@Override
	public String getItemType() {
		return REGULAR_PARAM;
	}

	@Override
	public ParsedItem getCopy() {
		return new ParsedRegularParameter(myString, isVariable);
	}
}
