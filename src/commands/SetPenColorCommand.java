package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SetPenColorCommand extends ExecutableCommand {
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		int index = (int) Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		writer.setPenColor(index);
		return index;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	}
}
