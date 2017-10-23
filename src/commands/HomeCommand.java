package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/**
 * Executable Command for moving the turtle to the home position, (0,0). 
 */
public class HomeCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return tortuga.moveTo(0, 0); 
	}
}
