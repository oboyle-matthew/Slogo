package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

public class GreaterThanCommand extends ExecutableCommand {
	private static final String GREATER = "greater?";

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return (new BooleanOperationCommand(GREATER)).execute(params, tortuga, variables);
	} 
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
}
