package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

/**
 * Executable Command for moving the turtle to the home position, (0,0). 
 */
public class HomeCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return writer.goToRelativePosition(0.0, 0.0);
	}
}
