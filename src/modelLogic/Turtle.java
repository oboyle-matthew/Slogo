package modelLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import GUI.GUIDelegate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * This class represents the model for the turtle that can be interacted with
 * through both the controller, and the command classes
 * 
 * @author Walker and Simran
 */
public class Turtle {

	private static final double LOWER_BOUNDARY_COORDINATE = 368.0;
	private static final double UPPER_BOUNDARY_COORDINATE = 18.0;
	private static final double RIGHT_BOUNDARY_COORDINATE = 540.0;
	private static final double LEFT_BOUNDARY_COORDINATE = 190.0;
	private static final double INITIAL_X_POSITION = 385.0;
	private static final double INITIAL_Y_POSITION = 213.0;
	/* Finals */
	private static final String ACTIVATED_TURTLE_PATH = "src/Activated.png";
	private static final String DEACTIVATED_TURTLE_PATH = "src/Deactivated.png";
	private final double TURTLE_SIZE = 40.0;

	/* Instance Variables */
	private ImageView myTurtle;
	private List<Path> myPaths;
	private Pen myPen;
	private boolean deactivated;
	private boolean dragging;
	private TransitionOperator transitionOperator;
	private double currentHeading; 
	private double currentX; 
	private double currentY; 
	private GUIDelegate myApp;
	

	/**
	 * Basic constructor that just initializes the myTurtle variable. Returns a new
	 * {@code Turtle} object
	 */
	public Turtle(GUIDelegate app) {
		myTurtle = createTurtle();
		myApp = app;
		currentHeading = myTurtle.getRotate(); 
		myPaths = new ArrayList<Path>();
		myPen = new Pen();
		currentX = myTurtle.getX();
		currentY = myTurtle.getY();
		transitionOperator = new TransitionOperator(); 
		setupMouseEventHandling(); 
	}

	private void setupMouseEventHandling() {
		myTurtle.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> dragging = false);
		myTurtle.addEventHandler(MouseEvent.DRAG_DETECTED, e -> dragging = true);
		myTurtle.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			jumpTo(e.getSceneX(), e.getSceneY());
			myApp.updateTurtleProperties();
		});
		myTurtle.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> turtleClicked());
	}

	private void turtleClicked() {
		if (!dragging) {
			if (deactivated) {
				myTurtle.setImage(new Image((new File(ACTIVATED_TURTLE_PATH)).toURI().toString(), TURTLE_SIZE,
						TURTLE_SIZE, false, false));
			} else {
				myTurtle.setImage(new Image((new File(DEACTIVATED_TURTLE_PATH)).toURI().toString(), TURTLE_SIZE,
						TURTLE_SIZE, false, false));
			}
			deactivated = !deactivated;
		}
	}
	
	

	private ImageView createTurtle() {
		File file = new File(ACTIVATED_TURTLE_PATH);
		Image turtleImage = new Image(file.toURI().toString(), TURTLE_SIZE, TURTLE_SIZE, false, false);
		ImageView turtleImageView = new ImageView(turtleImage);
		return turtleImageView;
	}
	
	/* Rotation Methods */

	/**
	 * Sets the heading of my turtle to the specified angle
	 * 
	 * @param angle
	 *            is an {@code double} representing the angle to set the turtle to
	 *            face. The function expects an angle in degrees, and the angle is
	 *            measured counter-clockwise from an x-axis centered on the turtle.
	 * @return A {@code double} representing the number of degrees rotated
	 */
	public double setHeading(double angle) {
		double degreeDiff = angle - currentHeading;
		transitionOperator.createRotation(myTurtle, degreeDiff);
		currentHeading += degreeDiff; 
		currentHeading = currentHeading % 360; 
		return Math.abs(degreeDiff);
	}
	
	/**
	 * Rotates the turtle to the left by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the left by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	public double rotateLeft(Double angle) {
		return setHeading(currentHeading - angle);
	}

	/**
	 * Rotates the turtle to the right by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the right by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	public double rotateRight(Double angle) {
		return setHeading(currentHeading + angle);
	}

	/* Movement Methods */

	/**
	 * Moves the turtle to the new position
	 * 
	 * @param newXPosition
	 *            is an {@code double} specifying the new x-coordinate of the turtle
	 * @param newYPosition
	 *            is an {@code double} specifying the new y-coordinate of the turtle
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveTo(Double newXPosition, Double newYPosition) {
		if(movementIsValid(newXPosition, newYPosition)) {
			double xDiff = newXPosition - currentX;
			double yDiff = newYPosition - currentY;
			Path p = createMovementPath(newXPosition, newYPosition);
			currentX = newXPosition;
			currentY = newYPosition;
			transitionOperator.createMovement(myTurtle, p, newXPosition, newYPosition);
			transitionOperator.createFadeIn(p);
			return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		} 
		return 0; 
	}
	
	private boolean movementIsValid(double newXPosition, double newYPosition) {
		return (newXPosition >= LEFT_BOUNDARY_COORDINATE && newXPosition <= RIGHT_BOUNDARY_COORDINATE && 
				newYPosition >= UPPER_BOUNDARY_COORDINATE && newYPosition <= LOWER_BOUNDARY_COORDINATE);
	}
	
	public double goTo(Double newXPosition, Double newYPosition) {
		return moveTo(newXPosition + INITIAL_X_POSITION, INITIAL_Y_POSITION - newYPosition);
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
		if(myPen.getPenInfo()) {
			p.setStrokeWidth(myPen.getPenSize());
			p.setStroke(myPen.getPenColor());
			p.setOpacity(0);
			myPaths.add(p);
			myApp.addLine(p);
		}
		return p;
	}

	public double jumpTo(double newXPosition, double newYPosition) {
		if(movementIsValid(newXPosition, newYPosition)) {
			myTurtle.setX(newXPosition);
			myTurtle.setY(newYPosition);
			currentX = newXPosition;
			currentY = newYPosition;
		}
		return 0.0;
	}

	/**
	 * Move the turtle forward by a certain amount
	 * 
	 * @param pixels
	 *            is a {@code double} specifying how many pixels to move the turtle
	 *            forward by
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveForward(Double pixels) {
		double newX = currentX + pixels * Math.sin(currentHeading * Math.PI / 180);
		double newY = currentY - pixels * Math.cos(currentHeading * Math.PI / 180);
		return moveTo(newX, newY);
	}

	/**
	 * Move the turtle backwards by a certain amount
	 * 
	 * @param pixels
	 *            is a {@code double} specifying how many pixels to move the turtle
	 *            backwards by
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveBackwards(Double pixels) {
		double newX = currentX - pixels * Math.sin(currentHeading * Math.PI / 180);
		double newY = currentY + pixels * Math.cos(currentHeading * Math.PI / 180);
		return moveTo(newX, newY);
	}

	public double moveLeft(Double pixels) {
		double newX = currentX - pixels * Math.sin(currentHeading * Math.PI / 180);
		double newY = currentY + pixels * Math.cos(currentHeading * Math.PI / 180);
		return moveTo(newX, newY);
	}
	
	/* Visbility Settings */

	/**
	 * Makes sure the turtle is visible
	 * 
	 * @return Always returns 0
	 */
	public double showTurtle() {
		transitionOperator.createFadeIn(myTurtle);
		return 1;
	}

	/**
	 * Hides the turtle
	 * 
	 * @return Always returns 1
	 */
	public double hideTurtle() {
		transitionOperator.createFadeOut(myTurtle);
		return 0;
	}

	/**
	 * Provides the {@code ImageView} for the controller to add to the view of the
	 * application
	 * 
	 * @return An {@code ImageView} that represents the turtle
	 */
	public ImageView getImageViewForScreen() {
		return myTurtle;
	}

	/* Turtle Pen Commands */

	/**
	 * Sets the turtle's pen to be down
	 * 
	 * @return 1 always
	 */
	public double penDown() {
		myPen.setPenInfo(true);
		return 1;
	}

	/**
	 * Sets the turtle's pen to be up
	 * 
	 * @return 0 always
	 */
	public double penUp() {
		myPen.setPenInfo(false);
		return 0;
	}

	/* Turtle Query Methods */

	/**
	 * @return A {@code double[]} of length 2, where the first item is the turtle's
	 *         x-coordinate, and the second item is the turtle's y-coordinate
	 */
	public double[] getCoordinates() {
		return new double[] { currentX, currentY};
	}

	/**
	 * @return A {@code double} that specifies the Turtle's current heading. Will
	 *         always be between 0 and 360 degrees.
	 */
	public double getHeading() {
		return currentHeading;
	}

	/**
	 * @return {@code True} if the turtle is currently being shown on the screen
	 */
	public boolean isTurtleVisible() {
		return myTurtle.isVisible();
	}


	public double getXPos() {
		return currentX - INITIAL_X_POSITION;
	}

	public double getYPos() {
		return INITIAL_Y_POSITION - currentY;
	}
	
	public void removeLines() {
		for(Path p : myPaths) {
			myApp.removeLine(p);
		}
		myPaths = new ArrayList<Path>();
	}

	/**
	 * @return A {@code boolean} representing the current state of the turtle
	 */
	public boolean getPenInfo() {
		return myPen.getPenInfo();
	}

	public double getPenSize() {
		return myPen.getPenSize();
	}

	public String getPenStyle() {
		return myPen.getPenStyle();
	}
	
	public String getPenColor() {
		return myPen.getPenColor().toString();
	}

	public void setPenInfo(boolean newVal) {
		myPen.setPenInfo(newVal);
	}

	public void setPenSize(double newSize) {
		myPen.setPenSize(newSize);
	}

	public void setPenStyle(String newStyle) {
		myPen.setPenStyle(newStyle);
	}
	
	public void setPenColor(String newColor) {
		myPen.setPenColor(newColor);
	}
}