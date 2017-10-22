package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for putting the Turtle's pen down
 */

public class PenDownCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return tortuga.penDown();
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 
}
