package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for putting the Turtle's pen up
 */

public class PenUpCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.penUp();
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 
	
	
}