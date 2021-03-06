package commands;


import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

public class DoTimesCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		ParsedBracketParameter firstParam = (ParsedBracketParameter) params[0];
		String[] repeatParameters = firstParam.getStringValues();
		double ret = 0;
		if (repeatParameters.length == 2) {
			double repeat;
			try {
				repeat = Double.parseDouble(repeatParameters[1]);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			for (int i = 1; i <= repeat; i++) {
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
