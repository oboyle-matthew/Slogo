package commands;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the turtle's x coordinate
 */
public class GetXCoordinateCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, double[] args) {
		return tortuga.getCoordinates()[0]; 
	}
}
