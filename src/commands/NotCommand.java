package commands;

import java.util.List;

import modelLogic.Turtle;


/**
 * Executable Command for the not command 
 */
public class NotCommand implements ExecutableCommand {

	private static final double DEFAULT_VALUE = 15;
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 1) return ( DEFAULT_VALUE == 0 ? 1 : 0);
		return ( args.remove(0) == 0 ? 1 : 0);
	}
	
}
