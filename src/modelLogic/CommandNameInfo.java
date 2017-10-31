package modelLogic;

import java.util.Map;

public class CommandNameInfo {
	
	private Map<String, Double> commandVariables;
	private ParsedBracketParameter commands;
	
	public CommandNameInfo(Map<String, Double> variables, ParsedBracketParameter bracketCommands) {
		commandVariables = variables;
		commands = bracketCommands;
	}
	
	public ParsedBracketParameter getBracketCommand() {
		return commands;
	}
	
	public Map<String, Double> getVariables() {
		return commandVariables;
	}
}
