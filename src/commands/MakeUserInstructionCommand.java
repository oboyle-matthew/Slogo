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
		double ret = 0;
		if (parameters.length == 2) {
			double repeat;
			try {
				repeat = Double.parseDouble(parameters[1]);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			for (int i = 1; i <= repeat; i++) {
				ParsedBracketParameter temp = (ParsedBracketParameter) p.getCopy();
				ret = p.executeCommands(tortuga, variables);
				variables.put(parameters[0], ret);
				p = temp;
			}
		}
		return 1;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM, BRACKET_PARAM, BRACKET_PARAM };
	}

}
