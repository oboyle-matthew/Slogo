package commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class ForCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		ParsedBracketParameter firstParam = (ParsedBracketParameter) params[0];
		String[] parameters = firstParam.getStringValues();
		double ret = 0;
		if (parameters.length == 4) {
			double start, end, increment;
			try {
				start = Double.parseDouble(parameters[1]);
				end = Double.parseDouble(parameters[2]);
				increment = Double.parseDouble(parameters[3]);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			for (double i = start; i <= end; i += increment) {
				ParsedBracketParameter temp = (ParsedBracketParameter) p.getCopy();
				ret = p.executeCommands(tortuga, variables);
				variables.put(parameters[0], ret);
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
