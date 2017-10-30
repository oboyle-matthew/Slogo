package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

/**
 * Executable Command for retrieving the up or down status of the pen
 */
public class IsPenDownCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return (writer.getMyPen().getPenInfo() ? 1 : 0);
	}

}
