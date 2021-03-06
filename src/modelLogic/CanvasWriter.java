package modelLogic;

import java.util.ArrayList;
import java.util.List;

import GUI.GUIDelegate;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * @author Walker
 *
 */
public abstract class CanvasWriter {

	/* Final Variables */
	private static final double LOWER_BOUNDARY_COORDINATE = 383.0;
	private static final double UPPER_BOUNDARY_COORDINATE = 43.0;
	private static final double RIGHT_BOUNDARY_COORDINATE = 550.0;
	private static final double LEFT_BOUNDARY_COORDINATE = 212.0;
	protected static final double INITIAL_X_POSITION = 385.0;
	protected static final double INITIAL_Y_POSITION = 213.0;
	protected static final String[] COLORS = {"white", "blue", "orange", "yellow", "green", "purple", "grey", "red"};
	
	/* Instance Variables */
	private static int writerCount = 0; 
	protected List<Node> drawnNodes;
	protected Pen myPen;
	protected GUIDelegate myApp;
	protected TransitionOperator transitionOperator;
	protected Node myNode;
	protected double myNodeSize;
	protected boolean deactivated; 
	protected boolean dragging;
	private int penColorIndex;
	private int id; 
	
	CanvasWriter(GUIDelegate app, double nodeSize) {
		myApp = app;
		myPen = new Pen();
		transitionOperator = new TransitionOperator();
		drawnNodes = new ArrayList<>();
		myNode = createNode(nodeSize);
		myApp.addNode(myNode);
		myNodeSize = nodeSize;
		setupMouseEventHandling();
		writerCount++; 
		id = writerCount; 
	}
	
	/**
	 * Sets up the event handling for mouse interaction with the model's node
	 */
	private void setupMouseEventHandling() {
		myNode.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> dragging = false);
		myNode.addEventHandler(MouseEvent.DRAG_DETECTED, e -> dragging = true);
		myNode.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			moveTo(e.getSceneX(), e.getSceneY(), false);
			myApp.updateTurtleProperties();
		});
		myNode.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> nodeClicked());
	}
	
	/**
	 * The method that is called when a node is clicked
	 */
	abstract protected void nodeClicked();
	
	/**
	 * Creates the node used by the model
	 * 
	 * @param size
	 *            is a {@code double} representing how big to make the node
	 * @return A {@code Node} to add to the scene
	 */
	abstract protected Node createNode(double size);

	/* Movement Methods */

	/**
	 * Moves the model node forward in whatever fashion the CanvasWriter chooses
	 * 
	 * @param pixels
	 *            is a {@code Double} that represents the units of distance to move
	 *            the node forward
	 * @return A {@code double} that represents the distance traveled by the model's
	 *         Node
	 */
	abstract public double moveForward(Double distance);

	/**
	 * Moves the model node backwards in whatever fashion the CanvasWriter chooses
	 * 
	 * @param pixels
	 *            is a {@code Double} that represents the units of distance to move
	 *            the node backwards
	 * @return A {@code double} that represents the distance traveled by the model's
	 *         Node
	 */
	abstract public double moveBackwards(Double distance);

	/**
	 * Moves the model's node to the new position
	 * 
	 * @param newXPosition
	 *            is an {@code double} specifying the new absolute x-coordinate of
	 *            the turtle
	 * @param newYPosition
	 *            is an {@code double} specifying the new absolute y-coordinate of
	 *            the turtle
	 * @param animated
	 *            is a {@code boolean} that indicates whether or not to animate the
	 *            movement
	 * @return A {@code double} that reflects the distance moved by the turtle
	 */
	abstract protected double moveTo(Double newXPosition, Double newYPosition, boolean animated);

	/**
	 * Moves the model's node to the specified position relative to the canvas
	 * 
	 * @param newXPositon
	 *            is an {@code Double} representing the model node's new x
	 *            coordinate
	 * @param newYPosition
	 *            is an {@code Double} representing the model node's new y
	 *            coordinate
	 * @return A {@code double} corresponding to the distance the model's node moved
	 */
	public double goToRelativePosition(Double newXPosition, Double newYPosition) {
		return moveTo(newXPosition + INITIAL_X_POSITION, INITIAL_Y_POSITION - newYPosition, true);
	}

	/**
	 * Adjusts the coordinates in the passed in array so that they are within the
	 * canvas bounds
	 * 
	 * @param attemptedPosition
	 *            is a {@code double[]} that contains the coordinates the
	 *            CanvasWriter is trying to move to
	 */
	protected void adjustCoordinates(double[] attemptedPosition) {
		if (attemptedPosition[0] <= LEFT_BOUNDARY_COORDINATE)
			attemptedPosition[0] = LEFT_BOUNDARY_COORDINATE;
		if (attemptedPosition[0] >= RIGHT_BOUNDARY_COORDINATE)
			attemptedPosition[0] = RIGHT_BOUNDARY_COORDINATE;
		if (attemptedPosition[1] <= UPPER_BOUNDARY_COORDINATE)
			attemptedPosition[1] = UPPER_BOUNDARY_COORDINATE;
		if (attemptedPosition[1] >= LOWER_BOUNDARY_COORDINATE)
			attemptedPosition[1] = LOWER_BOUNDARY_COORDINATE;
	}

	/**
	 * Checks to see if a movement being made by a node is valid or not
	 * 
	 * @param newXPosition
	 *            is a {@code double} representing the x coordinate the node is
	 *            trying to move to
	 * @param newYPosition
	 *            is a {@code double} representing the y coordinate the node is
	 *            trying to move to
	 * @return {@code true} if the coordinates represent an allowed move, and
	 *         returns {@code falses} otherwise
	 */
	protected boolean movementIsValid(double newXPosition, double newYPosition) {
		return (newXPosition >= LEFT_BOUNDARY_COORDINATE && newXPosition <= RIGHT_BOUNDARY_COORDINATE
				&& newYPosition >= UPPER_BOUNDARY_COORDINATE && newYPosition <= LOWER_BOUNDARY_COORDINATE);
	}

	/* Rotation Methods */

	/**
	 * Sets the heading of the model's node to the specified angle
	 * 
	 * @param angle
	 *            is an {@code double} representing the angle to set the node to
	 *            face
	 * @return A {@code double} representing the number of degrees rotated
	 */
	abstract public double setHeading(Double angle);

	/**
	 * Rotates the model's node to the left by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the left by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	abstract public double rotateLeft(Double angle);

	/**
	 * Rotates the model's node to the right by a specified amount.
	 * 
	 * @param angle
	 *            is an {@code double} representing the number of angles to rotate
	 *            to the right by
	 * @return A {@code double} that is the number of degrees just rotated
	 */
	abstract public double rotateRight(Double angle);

	/* Node Addition and Removal Methods */

	/**
	 * Adds the node to the main scene's root as well as the list of the model's
	 * current drawn nodes
	 * 
	 * @param n
	 *            is a {@code Node} that is the node to add
	 */
	protected void addToDrawnNodes(Node n) {
		drawnNodes.add(n);
		myApp.addNode(n);
	}

	public void removeDrawnNodes() {
		for (Node n : drawnNodes)
			myApp.removeNode(n);
		drawnNodes = new ArrayList<>();
	}

	/* Visibility Methods */

	/**
	 * Shows the model's node with a transition effect
	 * 
	 * @return Always returns 1
	 */
	public double showNode() {
		transitionOperator.createFadeIn(myNode);
		return 1;
	}

	/**
	 * Hides the model's node with a transition effect
	 * 
	 * @return Always returns 0
	 */
	public double hideNode() {
		transitionOperator.createFadeOut(myNode);
		return 0;
	}
	
	/* Setter Methods */
	
	/**
	 * @param index is an {@code int} that represents the index of the color you
	 * want to set the background to 
	 */
	public void setBackgroundColor(int index) {
		myApp.changeBackground(COLORS[index % (COLORS.length - 1)]);
	}
	
	/**
	 * @param index is an {@code int} that represents the index of the color you
	 * want to set the pen to 
	 */
	public void setPenColor(Integer index) {
		penColorIndex = index; 
		myPen.setPenColor(COLORS[index % (COLORS.length - 1)]);
	}
	
	/**
	 * @param status is {@code boolean} representing whether the canvas writer is active or not
	 */
	public void setActive(boolean status) {
		deactivated = !status;
		toggleNodeActivated();
	}
	
	/**
	 * Updates the node whenever it has been activated
	 */
	protected abstract void toggleNodeActivated();

	/* Getter Methods */

	/**
	 * @return A {@code double} representing the model Node's current X position
	 *         however it is represented
	 */
	abstract public double getXPos();

	/**
	 * @return A {@code double} representing the model Node's current Y position
	 *         however it is represented
	 */
	abstract public double getYPos();

	/**
	 * @return A {@code double} representing the model Node's current heading
	 *         however it is represented
	 */
	abstract public double getHeading();

	/**
	 * @return A {@code Node} representing the Node held by the current instance of
	 *         the {@code CanvasWriter}
	 */
	public Node getNodeForScreen() {
		return myNode;
	}

	/**
	 * @return {@code int} representing the CanvasWriter's ID
	 */
	public int getId() {
		return id; 
	}
	
	/**
	 * @return {@code int} representing the number of CanvasWriters
	 */
	public static int getWriters() {
		return writerCount;
	}
	
	/**
	 * @return {@code true} if the model's node is visible, and {@code false}
	 *         otherwise
	 */
	public boolean isVisible() {
		return myNode.isVisible();
	}
	
	/**
	 * @return {@code true} if the model's node is activated, and {@code false} otherwise
	 */
	public boolean isActivated() {
		return !deactivated;
	}

	/**
	 * @return The {@code Pen} held by the model
	 */
	public Pen getMyPen() {
		return myPen;
	}
	
	
	/**
	 * @return An {@code int} representing the current color index for the pen 
	 */
	public double getPenIndex() {
		return penColorIndex;
	}
}
