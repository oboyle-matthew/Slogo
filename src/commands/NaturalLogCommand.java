package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;
/** 
 * Executable Command representing the Log command
 */
public class NaturalLogCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return Math.log(value); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 
}
