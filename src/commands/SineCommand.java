package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SineCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variabless) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		return Math.sin(Math.toRadians(value)); 
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 

}
