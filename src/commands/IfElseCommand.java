package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class IfElseCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		double ret = 0; 
		if(value != 0) {
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			ret = p.executeCommands(writer, variables);
		} else {
			ParsedBracketParameter p = (ParsedBracketParameter) params[2];
			ret = p.executeCommands(writer, variables);
		}
		return ret;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, BRACKET_PARAM, BRACKET_PARAM};
	}

}
