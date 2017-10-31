package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SetBackgroundCommand extends ExecutableCommand {
	
	SetBackgroundCommand(String[] numParams) {
		
	}
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		int index = (int) Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		writer.setBackgroundColor(index);
		return index;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM };
	}
}
