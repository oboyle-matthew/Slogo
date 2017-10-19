package commands;

import modelLogic.Turtle;

/**
 * Executable command for rotating the turtle to the left
 */
public class LeftRotateCommand implements ExecutableCommand {

	private static final double DEFAULT_AMOUNT = 10;

	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length == 0) return tortuga.rotateLeft(DEFAULT_AMOUNT);
		return tortuga.rotateLeft(args[0]);
	}
}
