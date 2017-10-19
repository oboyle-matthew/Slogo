package commands;

import modelLogic.Turtle;

/**
 * Executable Command for putting the Turtle's pen down
 */

public class PenDownCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.penDown();
	}
}
