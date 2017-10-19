package commands;

import modelLogic.Turtle;

/**
 * Executable command for rotating the turtle to the left
 */
public class RightRotateCommand implements ExecutableCommand {

	private static final double DEFAULT_AMOUNT = 10;

	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length == 0) return tortuga.rotateRight(DEFAULT_AMOUNT);
		return tortuga.rotateRight(args[0]);
	}
}
