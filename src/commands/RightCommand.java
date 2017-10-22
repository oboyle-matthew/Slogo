package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable command for rotating the turtle to the left
 */
public class RightCommand implements ExecutableCommand {

	private static final double DEFAULT_AMOUNT = 10;

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() == 0) return tortuga.rotateRight(DEFAULT_AMOUNT);
		return tortuga.rotateRight(args.remove(0));
	}
}
