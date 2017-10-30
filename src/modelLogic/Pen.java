package modelLogic;


public class Pen {
	
	private static final double DEFAULT_STROKE_WIDTH = 4.0;
	private static final String DEFAULT_COLOR = "black";
	
	private String myColor;
	private String myStyle;
	private double mySize;
	private boolean isDown;

	public Pen() {
		myColor = DEFAULT_COLOR;
		mySize = DEFAULT_STROKE_WIDTH;
		isDown = false;
	}
	
	public boolean getPenInfo() {
		return isDown;
	}

	public double getPenSize() {
		return mySize;
	}

	public String getPenStyle() {
		return myStyle;
	}
	
	public String getColor() {
		return myColor; 
	}
	
	public double setPenStatus(boolean newVal) {
		isDown = newVal;
		if(isDown) return 1;
		return 0;
	}

	public void setPenSize(double newSize) {
		mySize = newSize;
	}

	public void setPenStyle(String newStyle) {
		myStyle = newStyle;
	}
	
	protected void setPenColor(String newColor) {
		myColor = newColor;
	}
}
