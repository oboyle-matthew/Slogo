package commands;

import java.util.List;

import modelLogic.Turtle;

public class EqualCommand implements ExecutableCommand {
	private static final String EQUAL = "equal?";

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(EQUAL)).execute(tortuga, args);
	} 
	
	@Override
	public int paramNumber() {
		return 2;
	} 
}
