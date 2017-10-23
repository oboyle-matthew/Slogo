package modelLogic;

public abstract class ParsedItem {
	
	protected static final String COMMAND = "command";
	protected static final String BRACKET_PARAM = "bracket parameter";
	protected static final String REGULAR_PARAM = "regular parameter";
	
	public abstract String getItemType();
}
