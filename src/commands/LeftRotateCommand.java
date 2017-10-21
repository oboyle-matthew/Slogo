package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable command for rotating the turtle to the left
 */
public class LeftRotateCommand implements ExecutableCommand {

	private static final double DEFAULT_AMOUNT = 10;

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() == 0) return tortuga.rotateLeft(DEFAULT_AMOUNT);
		return tortuga.rotateLeft(args.remove(0));
	}
}
