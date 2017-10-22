package commands;

import java.util.List;

import modelLogic.Turtle;

public class CosineCommand implements ExecutableCommand {

	public static final double DEFAULT_RETURN_VALUE = 0; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 1) return DEFAULT_RETURN_VALUE;
		return Math.cos(Math.toRadians(args.remove(0))); 
	}
	
	@Override
	public int paramNumber() {
		return 1;
	} 
}
