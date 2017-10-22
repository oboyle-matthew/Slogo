package commands;

import java.util.List;

import modelLogic.Turtle;

public class AndCommand implements ExecutableCommand {
	
	private static final String AND = "and";
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return (new BooleanOperationCommand(AND)).execute(tortuga, args);
	}

	@Override
	public int paramNumber() {
		return 2;
	} 
}
