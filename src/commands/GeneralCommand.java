package commands;

import java.util.HashMap;
import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

/**
 * A general command is necessary for user commands. These are very
 * simple in that they essentially take information from the CommandNameInfo
 * Objects created by user commands and determine the information about a
 * command programatically form that information.
 * 
 * @author Simran
 *
 */
public class GeneralCommand extends ExecutableCommand {

	private CommandNameInfo myCommand;

	public GeneralCommand(CommandNameInfo commandInfo) {
		myCommand = commandInfo;
	}

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		String[] varNames = myCommand.getVariables();
		Map<String, Double> allVariables = new HashMap<String, Double>(variables);
		for (int i = 0; i < params.length; i++) {
			Double varVal = Double.parseDouble(params[i].toString());
			allVariables.put(varNames[i], varVal);
		}
		ParsedBracketParameter copyCommand = myCommand.getBracketCommand().getCopy();
		return copyCommand.executeCommands(writer, allVariables, userFunctions);
	}

	@Override
	public String[] paramNumber() {
		int numberOfParams = myCommand.getVariables().length;
		String[] params = new String[numberOfParams];
		for (int i = 0; i < numberOfParams; i++) {
			params[i] = REGULAR_PARAM;
		}
		return params;
	}

}
