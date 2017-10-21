package commands;

import java.util.List;

import modelLogic.Turtle;

/** 
 * Executable Command representing all the possible basic math functions 
 */
public class MathOperationsCommand implements ExecutableCommand {
/* Final Variables */
	private static final double[] DEFAULT_ARGUMENTS = {0, 0}; 
	private static final String SUM = "sum";
	private static final String DIFFERENCE = "difference";
	private static final String PRODUCT = "product";
	private static final String QUOTIENT = "quotient";
	private static final String REMAINDER = "remainder";
	private static final String MINUS = "minus";

/* Instance Variables */ 
	private String myCommand; 
	
	MathOperationsCommand(String command) {
		myCommand = command; 
	}
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		double[] nums; 
		if(args == null || args.size() < 2) {
			nums = new double[] {DEFAULT_ARGUMENTS[0], DEFAULT_ARGUMENTS[1]}; 
		} else {
			nums = new double[] {args.remove(0), args.remove(0)};
		}
		if(myCommand.equals(SUM)) return nums[0] + nums[1]; 
		if(myCommand.equals(DIFFERENCE)) return nums[0] - nums[1]; 
		if(myCommand.equals(PRODUCT)) return nums[0] * nums[1]; 
		if(myCommand.equals(QUOTIENT)) return nums[0] / nums[1]; 
		if(myCommand.equals(REMAINDER)) return nums[0] % nums[1]; 
		if(myCommand.equals(MINUS)) return -1 * nums[0] ; 
		return 0;
	}

}
