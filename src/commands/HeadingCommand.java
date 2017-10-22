package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable command for retrieving the heading of the turtle
 */
public class HeadingCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.getHeading();
	}

}
