package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class RepeatCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		double repeat = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		double ret = 0; 
		ParsedBracketParameter p = (ParsedBracketParameter) params[1];
		for(int i = 0; i < repeat; i++) {
			ParsedBracketParameter temp = (ParsedBracketParameter) p.getCopy();
			variables.put(":repcount", i + 1.0);
			ret = p.executeCommands(writer, variables);
			p = temp; 
		}
		return ret;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, BRACKET_PARAM};
	}

}
