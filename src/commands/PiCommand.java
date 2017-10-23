package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;

/** 
 * Executable Command representing the Pi command
 */
public class PiCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return Math.PI; 
	}
}
