package modelLogic;

import java.util.List;

public class ParsedBracketParameter extends ParsedItem {
	
	private List<ParsedItem> myContents;
	
	public ParsedBracketParameter(List<ParsedItem> contents) {
		myContents = contents; 
	}

	@Override
	public String getItemType() {
		return BRACKET_PARAM;
	}

}
