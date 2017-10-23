package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/**
 * Executable Command for retrieving the up or down status of the pen
 */
public class IsPenDownCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return (tortuga.isPenDown() ? 1 : 0);
	}

}
