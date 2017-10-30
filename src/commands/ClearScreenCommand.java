package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

public class ClearScreenCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		writer.removeDrawnNodes(); 
		writer.getMyPen().setPenStatus(false);
		return writer.goToRelativePosition(0.0, 0.0);
	}
}
