package commands;

import java.util.ArrayList;
import java.util.List;

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
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 2) {
			args = new ArrayList<Double>(); 
			args.add(DEFAULT_VALUES[0]);
			args.add(DEFAULT_VALUES[1]); 
		}
		if(myCommand.equals(LESS)) return (args.remove(0) < args.remove(0) ? 1 : 0);
		if(myCommand.equals(GREATER)) return (args.remove(0) > args.remove(0) ? 1 : 0);
		if(myCommand.equals(EQUAL)) return (args.remove(0) == args.remove(0) ? 1 : 0);
		if(myCommand.equals(NOTEQUAL)) return (args.remove(0) != args.remove(0) ? 1 : 0);
		if(myCommand.equals(AND)) return (args.remove(0) > 0 && args.remove(0) > 0 ? 1 : 0);
		if(myCommand.equals(OR)) return (args.remove(0) > 0 || args.remove(0) > 0 ? 1 : 0);
		return 0;
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 

}
