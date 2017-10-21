package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for moving the turtle to the home position, (0,0). 
 */
public class HomeCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.moveTo(0, 0); 
	}
}
