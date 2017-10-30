package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

public class ClearScreenCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		tortuga.removeLines(); 
		return tortuga.moveTo(0.0, 0.0);
	}
}
