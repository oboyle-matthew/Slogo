package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;

/**
 * Sets the turtle to face a particular point
 */
public class SetTowardsCommand extends ExecutableCommand {

	private static final int ANGLE_ADJUSTMENT = 90;

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables) {
		double xToFace = Double.parseDouble(((ParsedRegularParameter) params[0]).toString()); 
		double yToFace = Double.parseDouble(((ParsedRegularParameter) params[1]).toString());
		double x = writer.getXPos();
		double y = writer.getYPos();
		double angle = calculateAngle(x, y, xToFace, yToFace);
		return writer.setHeading(angle);
	}
	
	public double calculateAngle(double x, double y, double newx, double newy) {
		double xDiff = x - newx; 
		double yDiff = y - newy; 
		double angle = Math.toDegrees(Math.atan(yDiff/xDiff));
		if(xDiff < 0 && yDiff > 0)  angle = 2*ANGLE_ADJUSTMENT + angle;
		if(xDiff < 0 && yDiff < 0) angle = 3 * ANGLE_ADJUSTMENT - angle;
		if(xDiff > 0 && yDiff < 0) angle = 4*ANGLE_ADJUSTMENT + angle; 
		return angle - ANGLE_ADJUSTMENT;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM, REGULAR_PARAM };
	}
}
