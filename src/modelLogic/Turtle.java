package modelLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * This class represents the model for the turtle that can be interacted with
 * through both the controller, and the command classes
 * 
 * @author Walker and Simran
 */
public class Turtle {

	/* Finals */
	private static final String ACTIVATED_TURTLE_PATH = "src/Activated.png";
	private static final String DEACTIVATED_TURTLE_PATH = "src/Deactivated.png";
	private static final double ROTATION_SPEED = 2 * 1000;
	private static final double MOVEMENT_SPEED = 1 * 1000;
	private final double TURTLE_SIZE = 40.0;

	/* Instance Variables */
	private ImageView myTurtle;
	private List<Path> myPaths;

	private Pen myPen;
	private boolean animationRunning;
	private boolean deactivated;
	private boolean dragging;
	private SequentialTransition st;

	/**
	 * Basic constructor that just initializes the myTurtle variable. Returns a new
	 * {@code Turtle} object
	 */
	public Turtle() {
		myTurtle = createTurtle();
		animationRunning = false;
		myPaths = new ArrayList<Path>();
		st = new SequentialTransition();
		myTurtle.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> dragging = false);
		myTurtle.addEventHandler(MouseEvent.DRAG_DETECTED, e -> dragging = true);
		myTurtle.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> moveTo(e.getSceneX(), e.getSceneY()));
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

	// EventHandler<MouseEvent> moveTurtleDragged = new EventHandler<MouseEvent>() {
	// public void handle(MouseEvent t) {
	// moveToSimple(t.getSceneX(), t.getSceneY());
	// }
	// };

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
		double degreeDiff = angle - myTurtle.getRotate();
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(ROTATION_SPEED));
		myTurtle.setRotate(angle);
		rotateTransition.setToAngle(angle);
		rotateTransition.setCycleCount(1);
		rotateTransition.setDuration(Duration.millis(ROTATION_SPEED));
		rotateTransition.setNode(myTurtle);
		runAnimation(rotateTransition);
		//rotateTransition.play();
		return Math.abs(degreeDiff);
	}
	
	private void runAnimation(Transition a) {
		if(st.getChildren().size() == 0) {
			st.getChildren().add(a);
		//st.play();
			System.out.println("animation run");
		} else {
			st.getChildren().add(a);
		//	st.play();

		}
	}
	

	/**
	 * Rotates the turtle to the left by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the left by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	public double rotateLeft(double angle) {
		return setHeading(myTurtle.getRotate() - angle);
	}

	/**
	 * Rotates the turtle to the right by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the right by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	public double rotateRight(double angle) {
		return setHeading(myTurtle.getRotate() + angle);
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

	// 10, 38 top left
	// 360,38 top right
	// 10, 388 bottom left
	// 360, 388 bottom right

	private boolean movementIsValid(double newXPosition, double newYPosition) {
		return (newXPosition >= 190.0 && newXPosition <= 540.0 && 
				newYPosition >= 18.0 && newYPosition <= 368.0);
	}
	
	public double moveTo(double newXPosition, double newYPosition) {
		if(movementIsValid(newXPosition, newYPosition)) {
			double xDiff = newXPosition - myTurtle.getX();
			double yDiff = newYPosition - myTurtle.getY();
//			Path p = createMovementPath(newXPosition, newYPosition);
//			PathTransition pt = new PathTransition();
			
			myTurtle.setX(newXPosition);
			myTurtle.setY(newYPosition);
			
//			pt.setNode(myTurtle);
//			pt.setPath(p);
//			pt.setDuration(Duration.millis(MOVEMENT_SPEED));
//			pt.setCycleCount(1);
//			runAnimation(pt);
//		
//			myPaths.add(p);
			return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
		} 
		return 0; 
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
		Path p = new Path();
		MoveTo moveTo = new MoveTo(myTurtle.getX(), myTurtle.getY());
		p.getElements().add(moveTo);
		LineTo lineTo = new LineTo(newXPosition, newYPosition);
		p.getElements().add(lineTo);
		return p;
	}

	public double jumpTo(double newXPosition, double newYPosition) {
		System.out.println("Move to called with:");
		System.out.println(newXPosition);
		System.out.println(newYPosition);
		myTurtle.setX(newXPosition);
		myTurtle.setY(newYPosition);
		return 0.;
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
		double x = myTurtle.getX() + pixels * Math.sin(myTurtle.getRotate() * Math.PI / 180);
		double y = myTurtle.getY() - pixels * Math.cos(myTurtle.getRotate() * Math.PI / 180);
		return moveTo(x, y);
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
		double x = myTurtle.getX() - pixels * Math.sin(myTurtle.getRotate() * Math.PI / 180);
		double y = myTurtle.getY() + pixels * Math.cos(myTurtle.getRotate() * Math.PI / 180);
		return moveTo(x, y);
	}

	/* Visbility Settings */

	/**
	 * Makes sure the turtle is visible
	 * 
	 * @return Always returns 0
	 */
	public double showTurtle() {
		myTurtle.setVisible(true);
		return 1;
	}

	/**
	 * Hides the turtle
	 * 
	 * @return Always returns 1
	 */
	public double hideTurtle() {
		myTurtle.setVisible(false);
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
		return 1;
	}

	/**
	 * Sets the turtle's pen to be up
	 * 
	 * @return 0 always
	 */
	public double penUp() {
		return 0;
	}

	/* Turtle Query Methods */

	/**
	 * @return A {@code double[]} of length 2, where the first item is the turtle's
	 *         x-coordinate, and the second item is the turtle's y-coordinate
	 */
	public double[] getCoordinates() {
		return new double[] { myTurtle.getX(), myTurtle.getY() };
	}

	/**
	 * @return A {@code double} that specifies the Turtle's current heading. Will
	 *         always be between 0 and 360 degrees.
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

	public double getDirection() {
		return myTurtle.getRotate();
	}

	public double getXPos() {
		return myTurtle.getX();
	}

	public double getYPos() {
		return myTurtle.getY();
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
		return myPen.getPenColor();
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

	private ImageView createTurtle() {
		File file = new File(ACTIVATED_TURTLE_PATH);
		Image turtleImage = new Image(file.toURI().toString(), TURTLE_SIZE, TURTLE_SIZE, false, false);
		return new ImageView(turtleImage);
	}
}