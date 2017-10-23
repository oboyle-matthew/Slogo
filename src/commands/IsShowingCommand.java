package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/**
 * Executable Command for retrieving the state of the turtle's visibility
 */
public class IsShowingCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return (tortuga.isTurtleVisible() ? 1 : 0);
	}

}
