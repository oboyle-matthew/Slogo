package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/**
 * Executable Command for making the turtle visible
 */
public class ShowTurtleCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return tortuga.showTurtle();
	}
}
