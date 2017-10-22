package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the up or down status of the pen
 */
public class IsPenDownCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (tortuga.isPenDown() ? 1 : 0);
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 

}
