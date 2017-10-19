package modelLogic;

import javafx.scene.image.ImageView;

/**
 * This class represents the model for the turtle that can
 * be interacted with through both the controller, and the 
 * command classes 
 */

public class Turtle {
	
	/* Finals */
	private static final String TURTLE_IMAGE_PATH = ""; 
	
	/* Instance Variables */ 
	private ImageView myTurtle; 
	
	/**
	 * Basic constructor that just initializes the myTurtle variable.
	 * Returns a new {@code Turtle} object
	 */
	Turtle() {
		myTurtle = new ImageView(TURTLE_IMAGE_PATH);
	}
	
/* Rotation Methods */ 
	
	/**
	 * Sets the heading of my turtle to the specified angle
	 * @param angle is an {@code double} representing the angle to set the turtle to face.
	 * The function expects an angle in degrees, and the angle is measured counter-clockwise
	 * from an x-axis centered on the turtle.
	 * @return A {@code double} representing the number of degrees rotated
	 */
	public double setHeading(double angle) {
		double degreeDiff = angle - myTurtle.getRotate();
		myTurtle.setRotate(angle);
		return degreeDiff; 
	}
	
	/**
	 * Rotates the turtle to the left by a specified amount.
	 * @param angle is an {@code double} representing the number of angles to rotate to the left by
	 * @return A {@code double} that is the number of degrees just rotated 
	 */
	public double rotateLeft(double angle) {
		return setHeading(myTurtle.getRotate() + angle);
	}
	
	/**
	 * Rotates the turtle to the right by a specified amount.
	 * @param angle is an {@code double} representing the number of angles to rotate to the right by
	 * @return A {@code double} that is the number of degrees just rotated 
	 */
	public double rotateRight(double angle) {
		return setHeading(myTurtle.getRotate() - angle); 
	}
	
/* Movement Methods */
	
	/**
	 * Moves the turtle to the new position
	 * @param newXPosition is an {@code double} specifying the new x-coordinate of the turtle
	 * @param newYPosition is an {@code double} specifying the new y-coordinate of the turtle
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveTo(double newXPosition, double newYPosition) {
		double xDiff = newXPosition - myTurtle.getX();
		double yDiff = newYPosition - myTurtle.getY(); 
		myTurtle.setX(newXPosition);
		myTurtle.setY(newYPosition);
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff); 
	}
	
	/**
	 *  Move the turtle forward by a certain amount 
	 * @param pixels is a {@code double} specifying how many pixels to move the turtle forward by
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveForward(double pixels) {
		return moveTo(myTurtle.getX(), myTurtle.getY() + pixels);
	}
	
	/**
	 *  Move the turtle backwards by a certain amount 
	 * @param pixels is a {@code double} specifying how many pixels to move the turtle backwards by
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	public double moveBackwards(double pixels) {
		return moveTo(myTurtle.getX(), myTurtle.getY() - pixels);
	}
	
/* Visbility Settings */ 
	
	/**
	 * Makes sure the turtle is visible
	 * @return Always returns 0
	 */
	public double showTurtle() {
		myTurtle.setVisible(true);
		return 1;
	}
	
	/**
	 * Hides the turtle
	 * @return Always returns 1 
	 */
	public double hideTurtle() {
		myTurtle.setVisible(false);
		return 0; 
	}

	/**
	 * Provides the {@code ImageView} for the controller to add
	 * to the view of the application
	 * @return An {@code ImageView} that represents the turtle
	 */
	public ImageView getImageViewForScreen() {
		return myTurtle; 
	}

/* Turtle Query Methods */
	
	/**
	 * @return A {@code double[]} of length 2, where the first item is the turtle's
	 * x-coordinate, and the second item is the turtle's y-coordinate
	 */
	public double[] getCoordinates() {
		return new double[] {myTurtle.getX(), myTurtle.getY()} ;
	}
	
	/**
	 * @return A {@code double} that specifies the Turtle's current heading. 
	 * Will always be between 0 and 360 degrees. 
	 */
	public double getHeading() {
		return myTurtle.getRotate();
	}
	
	/**
	 * @return {@code True} if the turtle is currently being shown on the screen
	 */
	public boolean isTurtleVisible() {
		return myTurtle.isVisible();
	}
	
}




