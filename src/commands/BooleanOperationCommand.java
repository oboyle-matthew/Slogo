package commands;

import modelLogic.Turtle;

public class BooleanOperationCommand implements ExecutableCommand {

/* Final Variables */
	private static final double[] DEFAULT_VALUES = {0, 0};
	private static final String LESS = "less?";
	private static final String GREATER = "greater?";
	private static final String EQUAL = "equal?";
	private static final String NOTEQUAL = "notequal?";
	private static final String AND = "and";
	private static final String OR = "or";

/* Instance Variables */ 
	private String myCommand; 
	
	BooleanOperationCommand(String command) {
		myCommand = command; 
	}

	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length < 2) {
			args = new double[] {DEFAULT_VALUES[0], DEFAULT_VALUES[1]}; 
		}
		if(myCommand.equals(LESS)) return (args[0] < args[1] ? 1 : 0);
		if(myCommand.equals(GREATER)) return (args[0] > args[1] ? 1 : 0);
		if(myCommand.equals(EQUAL)) return (args[0] == args[1] ? 1 : 0);
		if(myCommand.equals(NOTEQUAL)) return (args[0] != args[1] ? 1 : 0);
		if(myCommand.equals(AND)) return (args[0] > 0 && args[1] > 0 ? 1 : 0);
		if(myCommand.equals(OR)) return (args[0] > 0 || args[1] > 0 ? 1 : 0);
		return 0;
	}

}
