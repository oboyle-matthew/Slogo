package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the turtle's x coordinate
 */
public class GetXCoordinateCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.getCoordinates()[0]; 
	}
}
