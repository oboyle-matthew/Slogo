package commands;

import java.util.List;

import modelLogic.Turtle;

public class NotEqualCommand implements ExecutableCommand {
	private static final String NOTEQUAL = "notequal?";

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(NOTEQUAL)).execute(tortuga, args);
	} 
	
	@Override
	public int paramNumber() {
		return 2;
	} 
}
