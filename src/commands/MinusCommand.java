package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class MinusCommand extends ExecutableCommand { 

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return -1 * value;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 
	
}
