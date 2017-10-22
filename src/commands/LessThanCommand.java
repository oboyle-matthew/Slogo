package commands;

import java.util.List;

import modelLogic.Turtle;

public class LessThanCommand implements ExecutableCommand {
	private static final String LESS = "less?";

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(LESS)).execute(tortuga, args);
	} 
	
	@Override
	public int paramNumber() {
		return 2;
	} 
	
}
