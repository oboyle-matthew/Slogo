package commands;

import modelLogic.Turtle;

/**
 * Executable Command for making the turtle invisible
 */
public class HideTurtleCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.hideTurtle();
	}
}
