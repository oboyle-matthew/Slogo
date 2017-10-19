package commands;

import modelLogic.Turtle;

/**
 * Executable command for setting the turtle's current heading
 */
public class SetHeadingCommand implements ExecutableCommand {

	private static final double DEFAULT_DIRECTION = 90;

	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length == 0) return tortuga.setHeading(DEFAULT_DIRECTION);
		return tortuga.setHeading(args[0]);
	}
}
