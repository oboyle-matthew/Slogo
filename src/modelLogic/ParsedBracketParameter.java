package modelLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParsedBracketParameter extends ParsedItem {
	
	private List<ParsedItem> myContents;
	private String currLanguage;
	
	public ParsedBracketParameter(List<ParsedItem> contents, String language) {
		myName = "[ "; 
		for(ParsedItem p : contents)
			myName = myName + p.getName();
		myName = myName + " ]";
		currLanguage = language;
		myContents = contents; 
	}
	
	public double executeCommands(Turtle tortuga, Map<String, Double> variables) {
		CommandParser p = new CommandParser(currLanguage);
		return p.executeCommands(myContents, tortuga, variables);
	}

	@Override
	public String getItemType() {
		return BRACKET_PARAM;
	}

	@Override
	public ParsedItem getCopy() {
		List<ParsedItem> vals = new ArrayList<ParsedItem>();
		for(ParsedItem s : myContents)
			vals.add(s.getCopy());
		return new ParsedBracketParameter(vals, currLanguage);
	}

}
