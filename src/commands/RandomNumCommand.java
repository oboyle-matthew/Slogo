package commands;

import java.util.Random;

import modelLogic.Turtle;

/**
 * Executable Command that generates a random number 
 */
public class RandomNumCommand implements ExecutableCommand {

	private static final double DEFAULT_MAX_VALUE = 100; 
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		Random rn = new Random(); 
		if(args == null || args.length < 1) return rn.nextDouble() * DEFAULT_MAX_VALUE; 
		return rn.nextDouble() * args[0];
	}

}
