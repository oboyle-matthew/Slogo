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
		double[] coordinates = new double[] { 1, 2 };
		double x = tortuga.getXPos();
		double y = tortuga.getYPos();
		double xVector = coordinates[0] - x;
		double yVector = coordinates[1] - y;
		double angle = Math.atan(Math.abs(yVector / xVector));
		if (xVector < 0 && yVector > 0) {
			angle = 180 - angle;
		}
		if (xVector < 0 && yVector < 0) {
			angle *= -1;
		}
		tortuga.setHeading(angle);
		return angle;
	}

	@Override
	public String[] paramNumber() {
		return new String[] { REGULAR_PARAM, REGULAR_PARAM };
	}

}
