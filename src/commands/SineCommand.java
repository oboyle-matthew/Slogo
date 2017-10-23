package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class SineCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variabless) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).getValue());
		return Math.sin(Math.toRadians(value)); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 

}
