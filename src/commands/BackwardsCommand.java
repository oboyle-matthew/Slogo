package commands;

import modelLogic.Turtle;

/**
 *  Tells the turtle to move backwards by the specified amount
 */
public class BackwardsCommand implements ExecutableCommand {

/* Final Variables */
	private static final double DEFAULT_AMOUNT_TO_MOVE_BACKWARDS = 30; 
		
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length == 0) return tortuga.moveBackwards(DEFAULT_AMOUNT_TO_MOVE_BACKWARDS);
		return tortuga.moveBackwards(args[0]);
	}
}
