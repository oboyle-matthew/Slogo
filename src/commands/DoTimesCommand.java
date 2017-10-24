package commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class DoTimesCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		ParsedBracketParameter firstParam = (ParsedBracketParameter) params[0];
		String[] parameters = firstParam.getStringValues();
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
		return ret;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { BRACKET_PARAM, BRACKET_PARAM };
	}

}
