package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

public class SetBackgroundCommand extends ExecutableCommand {
	private static final String[] COLOR_STRINGS = {"white", "blue", "orange", "yellow", "green", "purple",
												  "grey", "red"};
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		int index = (int) Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		writer.setBackgroundColor(COLOR_STRINGS[index % (COLOR_STRINGS.length - 1)]);
		return index;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	}
	

}
