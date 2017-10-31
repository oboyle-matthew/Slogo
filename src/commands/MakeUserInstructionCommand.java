package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class MakeUserInstructionCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		String commandName = params[0].toString();
		ParsedBracketParameter secondParam = (ParsedBracketParameter) params[1];
		String[] commandVariables = secondParam.getStringValues();
		ParsedBracketParameter commands = (ParsedBracketParameter) params[2];
		if (variables.keySet().contains(commandName)) {
			return 0;
		}
		return 1;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM, BRACKET_PARAM, BRACKET_PARAM };
	}

}
