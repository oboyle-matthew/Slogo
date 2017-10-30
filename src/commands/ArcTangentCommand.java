package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class ArcTangentCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return Math.atan(value); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 

}
