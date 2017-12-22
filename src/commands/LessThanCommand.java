package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

public class LessThanCommand extends ExecutableCommand {
	private static final String LESS = "less?";

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return (new BooleanOperationCommand(LESS)).execute(params, tortuga, variables);
	} 
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
	
}
