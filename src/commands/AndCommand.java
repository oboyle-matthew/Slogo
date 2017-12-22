package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

public class AndCommand extends ExecutableCommand {
	
	private static final String AND = "and";
	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return (new BooleanOperationCommand(AND)).execute(params, tortuga, variables);
	}

	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM,REGULAR_PARAM};
	} 
}
