package modelLogic;

/**
 * This class is used to store information about user defined commands,
 * specifically their variables and the commands they want to execute.
 * 
 * @author Simran
 *
 */
public class CommandNameInfo {

	/* Instance Variables */
	private String[] variableNames;
	private ParsedBracketParameter commands;

	public CommandNameInfo(String[] variables, ParsedBracketParameter bracketCommands) {
		variableNames = variables;
		commands = bracketCommands;
	}

	/**
	 * Gives information about the bracket parameter so that the proper commands can
	 * execute them
	 * 
	 * @return
	 */
	public ParsedBracketParameter getBracketCommand() {
		return commands;
	}

	/**
	 * Yields information about variables so the commands know what to expect when
	 * seeing a user defined command
	 * 
	 * @return
	 */
	public String[] getVariables() {
		return variableNames;
	}
}
