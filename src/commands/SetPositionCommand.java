package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * ExecutableCommand for setting the position of the turtle
 */

public class SetPositionCommand implements ExecutableCommand {
	
	private final static double DEFAULT_X_POSITION = 0; 
	private final static double DEFAULT_Y_POSITION = 0; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 2) return tortuga.moveTo(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);
		return tortuga.moveTo(args.remove(0), args.remove(0));
	}
	
	@Override
	public int paramNumber() {
		return 2;
	} 

}
