package commands;

import modelLogic.Turtle;

/**
 * ExecutableCommand for setting the position of the turtle
 */

public class SetPositionCommand implements ExecutableCommand {
	
	private final static double DEFAULT_X_POSITION = 0; 
	private final static double DEFAULT_Y_POSITION = 0; 
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length < 2) return tortuga.moveTo(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);
		return tortuga.moveTo(args[0], args[1]);
	}

}
