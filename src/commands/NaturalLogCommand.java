package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
/** 
 * Executable Command representing the Log command
 */
public class NaturalLogCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return Math.log(value); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 
}
