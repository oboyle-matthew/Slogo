package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for making the turtle invisible
 */
public class HideTurtleCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.hideTurtle();
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 
}
