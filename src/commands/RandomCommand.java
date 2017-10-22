package commands;

import java.util.List;
import java.util.Random;

import modelLogic.Turtle;

/**
 * Executable Command that generates a random number 
 */
public class RandomCommand implements ExecutableCommand {

	private static final double DEFAULT_MAX_VALUE = 100; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		Random rn = new Random(); 
		if(args == null || args.size() < 1) return rn.nextDouble() * DEFAULT_MAX_VALUE; 
		return rn.nextDouble() * args.remove(0);
	}
	
	@Override
	public int paramNumber() {
		return 1;
	} 

}
