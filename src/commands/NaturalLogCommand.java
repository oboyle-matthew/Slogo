package commands;

import java.util.List;

import modelLogic.Turtle;
/** 
 * Executable Command representing the Log command
 */
public class NaturalLogCommand implements ExecutableCommand {

	private static double DEFAULT_VALUE = 100; 
	
	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		if(args == null || args.size() < 1) return Math.log(DEFAULT_VALUE); 
		return Math.log(args.remove(0)); 
	}
	
	@Override
	public int paramNumber() {
		return 1;
	} 
}
