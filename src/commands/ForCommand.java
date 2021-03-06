package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

public class ForCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		ParsedBracketParameter firstParam = (ParsedBracketParameter) params[0];
		String[] repeatParameters = firstParam.getStringValues();
		double ret = 0;
		if (repeatParameters.length == 4) {
			double start, end, increment;
			try {
				start = Double.parseDouble(repeatParameters[1]);
				end = Double.parseDouble(repeatParameters[2]);
				increment = Double.parseDouble(repeatParameters[3]);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			for (double i = start; i <= end; i += increment) {
				ParsedBracketParameter temp = (ParsedBracketParameter) p.getCopy();
				ret = p.executeCommands(writer, variables, userFunctions);
				variables.put(repeatParameters[0], ret);
				p = temp;
			}
		}
		return ret;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { BRACKET_PARAM, BRACKET_PARAM };
	}

}
