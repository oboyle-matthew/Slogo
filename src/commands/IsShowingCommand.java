package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the state of the turtle's visibility
 */
public class IsShowingCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (tortuga.isTurtleVisible() ? 1 : 0);
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 

}
