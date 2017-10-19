package commands;

import modelLogic.Turtle;

/** 
 * Executable Command representing the pow command
 */
public class PowCommand implements ExecutableCommand {
	
	private static double DEFAULT_BASE = 2; 
	private static double DEFAULT_EXPONENT = 4; 
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length < 2) return Math.pow(DEFAULT_BASE, DEFAULT_EXPONENT);
		return Math.pow(args[0], args[1]);
	}

}
