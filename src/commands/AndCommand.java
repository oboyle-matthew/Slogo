package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;

public class AndCommand extends ExecutableCommand {
	
	private static final String AND = "and";
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		return (new BooleanOperationCommand(AND)).execute(params, writer, variables, userFunctions);
	}

	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM,REGULAR_PARAM};
	} 
}
