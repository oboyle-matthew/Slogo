package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;


/**
 * Executable Command for the not command 
 */
public class NotCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return ( value == 0 ? 1 : 0);
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 
	
}
