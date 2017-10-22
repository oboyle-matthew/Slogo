package commands;

import java.util.List;

import modelLogic.Turtle;

public class MinusCommand implements ExecutableCommand {
	private static final double DEFAULT_RETURN = 0; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 1) return DEFAULT_RETURN;
		return -1 * args.remove(0);
	}
	
	@Override
	public int paramNumber() {
		return 1;
	} 
	
}
