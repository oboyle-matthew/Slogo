package commands;

import modelLogic.Turtle;


/**
 * Executable Command for the not command 
 */
public class NotOperationCommand implements ExecutableCommand {

	private static final double DEFAULT_VALUE = 1;
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length < 1) return ( DEFAULT_VALUE == 0 ? 1 : 0);
		return ( args[0] == 0 ? 1 : 0);
	}

}
