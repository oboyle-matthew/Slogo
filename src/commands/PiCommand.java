package commands;

import modelLogic.Turtle;

/** 
 * Executable Command representing the Pi command
 */
public class PiCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return Math.PI; 
	}
}
