package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the turtle's y coordinate
 */
public class YCoordinateCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.getCoordinates()[1]; 
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 
}
