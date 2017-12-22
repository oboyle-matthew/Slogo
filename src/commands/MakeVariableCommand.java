package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class MakeVariableCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		ParsedRegularParameter p = (ParsedRegularParameter) params[0];
		double value = Double.parseDouble(((ParsedRegularParameter) params[1]).toString());
		variables.put(p.toString(), value);
		return value;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	}
}
