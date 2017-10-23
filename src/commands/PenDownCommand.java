package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/**
 * Executable Command for putting the Turtle's pen down
 */

public class PenDownCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return tortuga.penDown();
	}
}
