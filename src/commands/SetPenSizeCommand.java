package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SetPenSizeCommand extends ExecutableCommand {
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		double newSize = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		writer.getMyPen().setPenSize(newSize);
		return newSize;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM };
	}
}
