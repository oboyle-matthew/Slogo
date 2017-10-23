package commands;

import java.util.Map;

import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class IfElseCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).getValue());
		double ret = 0; 
		if(value != 0) {
			ParsedBracketParameter p = (ParsedBracketParameter) params[1];
			ret = p.executeCommands(tortuga, variables);
		} else {
			ParsedBracketParameter p = (ParsedBracketParameter) params[2];
			ret = p.executeCommands(tortuga, variables);
		}
		return ret;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, BRACKET_PARAM, BRACKET_PARAM};
	}

}
