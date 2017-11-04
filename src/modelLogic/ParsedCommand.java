package modelLogic;

import java.util.Map;

import commands.ExecutableCommand;
import commands.GeneralCommand;

/**
 * Represents a parsed command for a SLOGO command
 * 
 * @author Walker and Simran
 */
public class ParsedCommand extends ParsedItem {

	/* Final Variables */
	private static final double DEFAULT_VARIABLE_VALUE = 0;
	private static final String COMMAND_POSTFIX = "Command";
	private static final String COMMANDS_DIR = "commands.";
	private ExecutableCommand myCommand;

	private boolean isCommand;

	ParsedCommand(String commandName) {
		myString = commandName;
		myCommand = createExecutableCommand(commandName);
		isCommand = myCommand != null;
	}

	/**
	 * Creates an ExecutableCommand object from the given command string
	 * 
	 * @param command
	 *            is a {@code String} that represents the command you want to create
	 * @return An {@code ExecutableCommand} object for the given command string
	 */
	private ExecutableCommand createExecutableCommand(String command) {
		try {
			Class<?> c = Class.forName(COMMANDS_DIR + command + COMMAND_POSTFIX);
			return (ExecutableCommand) c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Replaces the variables within the parameters used for execution with their
	 * double values in the form of a string
	 * 
	 * @param params
	 *            is a {@code ParsedItem[]} that contains the params for a command's
	 *            execution
	 * @param variables
	 *            is the {@code Map<String, Double>} that contains the user created
	 *            variables
	 */
	private void updateParams(ParsedItem[] params, Map<String, Double> variables) {
		for (int i = 0; i < params.length; i++) {
			if (!params[i].getItemType().equals(COMMAND) && params[i].getItemType().equals(REGULAR_PARAM)) {
				ParsedRegularParameter p = (ParsedRegularParameter) params[i];
				if (p.isVariable())
					params[i] = new ParsedRegularParameter(
							"" + variables.getOrDefault(p.toString(), DEFAULT_VARIABLE_VALUE), false);
			}
		}
	}

	/**
	 * Executes the command for the ParsedCommand object
	 * 
	 * @param params
	 *            is a {@code ParsedItems[]} that contains the parameters to use for
	 *            executing the commnad
	 * @param tortuga
	 *            is the {@code Turtle} to execute the commands with
	 * @param variables
	 *            is the {@code Map<String, Double>} of user created variable
	 * @return the {@code double} returned from the execution of the command
	 */
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		if (!myString.equals("MakeVariable"))
			updateParams(params, variables);
		return myCommand.execute(params, writer, variables, userFunctions);
	}

	/**
	 * Used to check if any random string that follows the command regex is actually
	 * a command that user defined
	 * 
	 * @param userFunctions
	 */
	public void checkUserFunctions(Map<String, CommandNameInfo> userFunctions) {
		if (userFunctions.keySet().contains(myString)) {
			isCommand = true;
			myCommand = new GeneralCommand(userFunctions.get(myString));
		}
	}

	/**
	 * @return A {@code String[]} that contains the parameters needed for the
	 *         command to execute
	 */
	public String[] getParameterOrder() {
		return myCommand.paramNumber();
	}

	/**
	 * Boolean used to make sure to execute the proper commands. There can be
	 * strings that follow command regex, but necessarily aren't commands. This adds
	 * an extra step of protection so that we don't try to execute invalid strings.
	 * 
	 * @return
	 */
	public boolean isCommand() {
		return isCommand;
	}

	/* (non-Javadoc)
	 * @see modelLogic.ParsedItem#getItemType()
	 */
	@Override
	public String getItemType() {
		return COMMAND;
	}

	/* (non-Javadoc)
	 * @see modelLogic.ParsedItem#getCopy()
	 */
	@Override
	public ParsedItem getCopy() {
		return new ParsedCommand(myString);
	}
}
