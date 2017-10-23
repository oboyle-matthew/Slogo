package modelLogic;

public class ParsedRegularParameter extends ParsedItem {

	private String myValue;
	private boolean isVariable;
	
	public ParsedRegularParameter(String value, boolean isVariableStatus) {
		myName = value;
		myValue = value;
		isVariable = isVariableStatus;
	}
	
	public boolean isVariable() {
		return isVariable;
	}
	
	public String getValue() {
		return myValue;
	}
	
	@Override
	public String getItemType() {
		return REGULAR_PARAM;
	}

	@Override
	public ParsedItem getCopy() {
		return new ParsedRegularParameter(myValue, isVariable);
	}

}
