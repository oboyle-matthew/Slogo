package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable command for setting the turtle's current heading
 */
public class SetHeadingCommand implements ExecutableCommand {

	private static final double DEFAULT_DIRECTION = 90;

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() == 0) return tortuga.setHeading(DEFAULT_DIRECTION);
		return tortuga.setHeading(args.remove(0));
	}
	
	@Override
	public int paramNumber() {
		return 1;
	} 
}
