package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;

public class GetPenColorCommand extends ExecutableCommand{

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		return writer.getPenIndex();
	}

}
