package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * Executable Command for retrieving the up or down status of the pen
 */
public class GetPenDownCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (tortuga.isPenDown() ? 1 : 0);
	}

}
