package commands;

import modelLogic.Turtle;

/**
 * Executable Command for putting the Turtle's pen up
 */

public class PenUpCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.penUp();
	}
}