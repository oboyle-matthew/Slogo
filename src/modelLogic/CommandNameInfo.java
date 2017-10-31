package modelLogic;

import java.util.Map;

public class CommandNameInfo {
	
	private String[] variableNames;
	private ParsedBracketParameter commands;
	
	public CommandNameInfo(String[] variables, ParsedBracketParameter bracketCommands) {
		variableNames = variables;
		commands = bracketCommands;
	}
	
	public ParsedBracketParameter getBracketCommand() {
		return commands;
	}
	
	public String[] getVariables() {
		return variableNames;
	}
}
