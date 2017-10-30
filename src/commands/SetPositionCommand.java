package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

/**
 * ExecutableCommand for setting the position of the turtle
 */

public class SetPositionCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value1 = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		double value2 = Double.parseDouble(((ParsedRegularParameter) params[1]).toString());
		return tortuga.goTo(value1, value2);
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 

}
