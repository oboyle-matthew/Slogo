package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SumCommand extends ExecutableCommand {
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		double value1 = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		double value2 = Double.parseDouble(((ParsedRegularParameter) params[1]).toString());
		return value1 + value2;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
	
	

}
