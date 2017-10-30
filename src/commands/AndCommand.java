package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

public class AndCommand extends ExecutableCommand {
	
	private static final String AND = "and";
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return (new BooleanOperationCommand(AND)).execute(params, writer, variables);
	}

	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM,REGULAR_PARAM};
	} 
}
