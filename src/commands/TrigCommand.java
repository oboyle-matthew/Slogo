package commands;

import java.util.List;

import modelLogic.Turtle;

/** 
 * Executable Command representing all the possible trig functions 
 */
public class TrigCommand implements ExecutableCommand {

/* Final Variables */
	private static final double DEFAULT_DEGREES = 0;
	private static final String SINE = "sin"; 
	private static final String COSINE = "cos";
	private static final String TANGENT = "tan";
	private static final String ARCTANGENT = "atan";
/* Instance Variables */ 
	private String myCommand;
	
	TrigCommand(String commandType) {
		myCommand = commandType;  
	}
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		double angle; 
		if(args == null || args.size() < 1) { 
			angle = DEFAULT_DEGREES; 
		} else {
			angle = args.remove(0); 
		}
		if(myCommand.equals(SINE)) return Math.sin(angle);
		if(myCommand.equals(COSINE)) return Math.cos(angle);
		if(myCommand.equals(TANGENT)) return Math.tan(angle);
		if(myCommand.equals(ARCTANGENT)) return Math.atan(angle); 
		return 0; 
	}

}
