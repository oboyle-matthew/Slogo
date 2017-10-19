package commands;

import modelLogic.Turtle;

/**
 * Executable command for retrieving the heading of the turtle
 */
public class GetHeadingCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.getHeading();
	}

}
