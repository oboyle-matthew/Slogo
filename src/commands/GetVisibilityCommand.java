package commands;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the state of the turtle's visibility
 */
public class GetVisibilityCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return (tortuga.isTurtleVisible() ? 1 : 0);
	}

}
