package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;

/**
 * Executable Command for putting the Turtle's pen up
 */

public class PenUpCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions) {
		return writer.getMyPen().setPenStatus(false);
	}
}