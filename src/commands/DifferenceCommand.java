package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class DifferenceCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value1 = Double.parseDouble(((ParsedRegularParameter) params[0]).getValue());
		double value2 = Double.parseDouble(((ParsedRegularParameter) params[1]).getValue());
		return value1 - value2; 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
}
