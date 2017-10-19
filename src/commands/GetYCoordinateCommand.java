package commands;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the turtle's y coordinate
 */
public class GetYCoordinateCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.getCoordinates()[1]; 
	}
}
