package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 *  Tells the turtle to move backwards by the specified amount
 */
public class BackwardCommand implements ExecutableCommand {

/* Final Variables */
	private static final double DEFAULT_AMOUNT_TO_MOVE_BACKWARDS = 30; 
		
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() == 0) return tortuga.moveBackwards(DEFAULT_AMOUNT_TO_MOVE_BACKWARDS); 
		return tortuga.moveBackwards(args.remove(0));
	}
}
