package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class CosineCommand extends ExecutableCommand {

	public static final double DEFAULT_RETURN_VALUE = 0; 
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).getValue());
		return Math.cos(Math.toRadians(value)); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 
}
