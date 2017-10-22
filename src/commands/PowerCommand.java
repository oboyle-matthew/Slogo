package commands;

import java.util.List;

import modelLogic.Turtle;

/** 
 * Executable Command representing the pow command
 */
public class PowerCommand implements ExecutableCommand {
	
	private static double DEFAULT_BASE = 2; 
	private static double DEFAULT_EXPONENT = 4; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 2) return Math.pow(DEFAULT_BASE, DEFAULT_EXPONENT);
		return Math.pow(args.remove(0), args.remove(0));
	}

}
