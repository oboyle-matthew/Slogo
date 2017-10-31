package modelLogic;

import java.io.File;

import GUI.GUIDelegate;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * This class represents the model for the turtle that can be interacted with
 * through both the controller, and the command classes
 * 
 * @author Walker and Simran
 */
public class Turtle extends CanvasWriter {

	/* Final Variables */
	private static final String ACTIVATED_TURTLE_PATH = "src/Activated.png";
	private static final String DEACTIVATED_TURTLE_PATH = "src/Deactivated.png";
	private static final double TURTLE_SIZE = 40.0;

	/* Instance Variables */
	private double currentHeading;
	private double currentX;
	private double currentY;

	/**
	 * Basic constructor that just initializes the myTurtle variable. Returns a new
	 * {@code Turtle} object
	 */
	public Turtle(GUIDelegate app, int id) {
		super(app, TURTLE_SIZE, id);
		currentHeading = myNode.getRotate();
		currentX = ((ImageView) myNode).getX();
		currentY = ((ImageView) myNode).getY();
	}

	@Override
	protected Node createNode(double size) {
		File file = new File(ACTIVATED_TURTLE_PATH);
		Image turtleImage = new Image(file.toURI().toString(), TURTLE_SIZE, TURTLE_SIZE, false, false);
		return new ImageView(turtleImage);
	}

	/* Event Handling */

	@Override 
	protected void nodeClicked() {
		if (!dragging) {
			if (deactivated) {
				((ImageView) myNode).setImage(new Image((new File(ACTIVATED_TURTLE_PATH)).toURI().toString(),
						TURTLE_SIZE, TURTLE_SIZE, false, false));
			} else {
				((ImageView) myNode).setImage(new Image((new File(DEACTIVATED_TURTLE_PATH)).toURI().toString(),
						TURTLE_SIZE, TURTLE_SIZE, false, false));
			}
			deactivated = !deactivated;
		}
	}

	/* Movement Methods */

	@Override
	public double moveForward(Double distance) {
		// Moves opposite of the direction the turtle is facing
		double newX = currentX + distance * Math.sin(currentHeading * Math.PI / 180);
		double newY = currentY - distance * Math.cos(currentHeading * Math.PI / 180);
		return moveTo(newX, newY, true);
	}

	@Override
	public double moveBackwards(Double distance) {
		// Moves in the direction the turtle is facing
		double newX = currentX - distance * Math.sin(currentHeading * Math.PI / 180);
		double newY = currentY + distance * Math.cos(currentHeading * Math.PI / 180);
		return moveTo(newX, newY, true);
	}

	@Override
	public double moveTo(Double newXPosition, Double newYPosition, boolean animated) {
		double[] currCoordinates = new double[] { newXPosition, newYPosition };
		if (!movementIsValid(newXPosition, newYPosition))
			adjustCoordinates(currCoordinates);
		double xDiff = currCoordinates[0] - currentX;
		double yDiff = currCoordinates[1] - currentY;
		if (animated) {
			Path p = createMovementPath(currCoordinates[0], currCoordinates[1]);
			transitionOperator.createMovement(myNode, p, currCoordinates[0], currCoordinates[1]);
			transitionOperator.createFadeIn(p);
		} else {
			((ImageView) myNode).setX(currCoordinates[0]);
			((ImageView) myNode).setY(currCoordinates[1]);
		}
		currentX = currCoordinates[0];
		currentY = currCoordinates[1];
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	/**
	 * Creates the new path for use in the turtle's movement animation
	 * 
	 * @param newXPosition
	 *            is a {@code double} representing the new x-coordinate of the
	 *            turtle
	 * @param newYPosition
	 *            is a {@code double} representing the new y-coordinate of the
	 *            turtle
	 * @return A {@code Path} that corresponds to the line the turtle is moving over
	 */
	private Path createMovementPath(double newXPosition, double newYPosition) {
		Path p = new Path(new MoveTo(currentX, currentY), new LineTo(newXPosition, newYPosition));
		if (myPen.getPenInfo()) {
			p.setStrokeWidth(myPen.getPenSize());
			p.setStyle("-fx-stroke:" + myPen.getColor() + ";");
			p.setOpacity(0);
			addToDrawnNodes(p);
		}
		return p;
	}

	/* Rotation Methods */

	@Override
	public double rotateLeft(Double angle) {
		return setHeading(currentHeading - angle);
	}

	@Override
	public double rotateRight(Double angle) {
		return setHeading(currentHeading + angle);
	}

	@Override
	public double setHeading(Double angle) {
		double degreeDiff = angle - currentHeading;
		transitionOperator.createRotation(myNode, degreeDiff);
		currentHeading += degreeDiff;
		currentHeading = currentHeading % 360;
		return Math.abs(degreeDiff);
	}

	/* Turtle Query Methods */

	@Override
	public double getHeading() {
		return currentHeading;
	}

	@Override
	public double getXPos() {
		return currentX - INITIAL_X_POSITION;
	}

	@Override
	public double getYPos() {
		return INITIAL_Y_POSITION - currentY;
	}
}