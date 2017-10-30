package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

/**
 * Executable Command for putting the Turtle's pen down
 */

public class PenDownCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return writer.getMyPen().setPenStatus(true);
	}
}
