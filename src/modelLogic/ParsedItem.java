package modelLogic;

public abstract class ParsedItem {
	
	protected static final String COMMAND = "command";
	protected static final String BRACKET_PARAM = "bracket parameter";
	protected static final String REGULAR_PARAM = "regular parameter";
	protected String myName; 
	
	public abstract String getItemType();
	
	public String getName() {
		return myName;
	}
	
	public abstract ParsedItem getCopy();
}
