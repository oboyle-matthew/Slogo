package commands;

import java.util.List;

import modelLogic.Turtle;

public class OrCommand implements ExecutableCommand {
	private static final String OR = "or";

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(OR)).execute(tortuga, args);
	} 
	
	@Override
	public int paramNumber() {
		return 2;
	} 
	
}
