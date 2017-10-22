package commands;

import java.util.List;

import modelLogic.Turtle;

/** 
 * Executable Command representing the Pi command
 */
public class PiCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return Math.PI; 
	}
}
