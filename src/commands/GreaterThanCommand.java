package commands;

import java.util.List;

import modelLogic.Turtle;

public class GreaterThanCommand implements ExecutableCommand {
	private static final String GREATER = "greater?";

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(GREATER)).execute(tortuga, args);
	} 
	
	@Override
	public int paramNumber() {
		return 2;
	} 
}
