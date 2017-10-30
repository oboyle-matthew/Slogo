package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

/**
 * Executable Command for retrieving the turtle's x coordinate
 */
public class XCoordinateCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return writer.getXPos();
	}
	
}
