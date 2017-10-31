package commands;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;
import modelLogic.CommandParser;

public class MakeUserInstructionCommand extends ExecutableCommand {

	private static final String COMMAND_REGEX = "-?[0-9]+\\.?[0-9]*";

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		String commandName = params[0].toString();
		ParsedBracketParameter secondParam = (ParsedBracketParameter) params[1];
		String[] commandVariableNames = secondParam.getStringValues();
		ParsedBracketParameter commands = (ParsedBracketParameter) params[2];
		Map<String, Double> commandVariables = new HashMap<String, Double>();
		if (variables.keySet().contains(commandName)) {
			return 0;
		}
		for (String inputVariable : commandVariableNames) {
			Pattern pattern = Pattern.compile(COMMAND_REGEX);
			Matcher m = pattern.matcher(inputVariable);
			if (!m.matches()) {
				return 0;
			}
		}
		CommandNameInfo function = new CommandNameInfo(commandVariableNames, commands);
		userFunctions.put(commandName, function); 
		return 1;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM, BRACKET_PARAM, BRACKET_PARAM };
	}

}
