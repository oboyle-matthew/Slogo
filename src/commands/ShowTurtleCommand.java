package commands;

import modelLogic.Turtle;

/**
 * Executable Command for making the turtle visible
 */
public class ShowTurtleCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.showTurtle();
	}
}
