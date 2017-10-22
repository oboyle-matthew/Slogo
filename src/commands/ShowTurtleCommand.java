package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for making the turtle visible
 */
public class ShowTurtleCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.showTurtle();
	}
}
