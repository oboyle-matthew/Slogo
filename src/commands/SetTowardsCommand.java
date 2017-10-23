package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.Turtle;
/**
 * Sets the turtle to face a particular point
 */
public class SetTowardsCommand extends ExecutableCommand {

	
	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		return 0;
	}
	
	private double getAngleForHeading(double x, double y, double[] coordinates) {
		double xVector = x - coordinates[0];
		double yVector = coordinates[1] - y;
		double angle = Math.atan(yVector / xVector);
		if(xVector > 0 && yVector > 0) return angle; 
		if(xVector < 0 && yVector > 0) return angle + ANGLE_OFFSET; 
		if(xVector < 0 && yVector < 0) return angle + 2 * ANGLE_OFFSET; 
		return angle + 3 * ANGLE_OFFSET; 
	//	if(xVector < 0 && yVector < 0)
		
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {REGULAR_PARAM, REGULAR_PARAM};
	} 
	
}
