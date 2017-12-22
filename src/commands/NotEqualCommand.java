package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;

public class NotEqualCommand extends ExecutableCommand {
	private static final String NOTEQUAL = "notequal?";

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		return (new BooleanOperationCommand(NOTEQUAL)).execute(params, writer, variables, userFunctions);
	} 
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
}
