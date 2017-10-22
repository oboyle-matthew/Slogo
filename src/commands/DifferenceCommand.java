package commands;

import java.util.List;

import modelLogic.Turtle;

public class DifferenceCommand implements ExecutableCommand {

	private static final double DEFAULT_DIFFERENCE = 0; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 2) return DEFAULT_DIFFERENCE;
		return args.remove(0) - args.remove(0); 
	}
	
	@Override
	public int paramNumber() {
		return 2;
	} 
}
