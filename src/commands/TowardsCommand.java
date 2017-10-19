package commands;

import modelLogic.Turtle;
/**
 * Sets the turtle to face a particular point
 */
public class TowardsCommand implements ExecutableCommand {

	private static final double DEFAULT_X_COORDINATE = 0; 
	private static final double DEFAULT_Y_COORDINATE = 0; 
	private static final double ANGLE_OFFSET = 90;
	
	@Override
	public double execute(Turtle tortuga, double[] args) {
		if(args == null || args.length < 2) {
			
		}
	}
	
	private double getAngleForHeading(double x, double y, double[] coordinates) {
		double xVector = x - coordinates[0];
		double yVector = coordinates[1] - y;
		double angle = Math.atan(yVector / xVector);
		if(xVector > 0 && yVector > 0) return angle; 
		if(xVector < 0 && yVector > 0) return + ANGLE_OFFSET; 
	//	if(xVector < 0 && yVector < 0)
		
	}
	
}
