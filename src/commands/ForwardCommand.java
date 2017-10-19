package commands;

import modelLogic.Turtle;

/**
 *  Tells the turtle to move forward by the specified amount
 */

public class ForwardCommand implements ExecutableCommand {

/* Final Variables */
	private static final double DEFAULT_AMOUNT_TO_MOVE_FORWARD = 30; 
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length == 0) return tortuga.moveForward(DEFAULT_AMOUNT_TO_MOVE_FORWARD);
		return tortuga.moveForward(args[0]);
	}
}
