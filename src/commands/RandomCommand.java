package commands;

import java.util.Map;
import java.util.Random;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

/**
 * Executable Command that generates a random number 
 */
public class RandomCommand extends ExecutableCommand {

	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		double value = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		Random rn = new Random(); 
		return rn.nextDouble() * value;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM};
	} 

}
