package modelLogic;

public class Pen {
	
	private static final double DEFAULT_STROKE_WIDTH = 1.0;
	private static final String DEFAULT_COLOR = "BLACK";
	
	private String myColor;
	private String myStyle;
	private double mySize;
	private boolean myStatus;

	public Pen() {
		myColor = DEFAULT_COLOR;
		mySize = DEFAULT_STROKE_WIDTH;
		myStatus = true;
	}
	
	public boolean getPenInfo() {
		return myStatus;
	}

	public double getPenSize() {
		return mySize;
	}

	public String getPenStyle() {
		return myStyle;
	}
	
	public String getPenColor() {
		return myColor;
	}
		
	public void setPenInfo(boolean newVal) {
		myStatus = newVal;
	}

	public void setPenSize(double newSize) {
		mySize = newSize;
	}

	public void setPenStyle(String newStyle) {
		myStyle = newStyle;
	}
	
	public void setPenColor(String newColor) {
		myColor = newColor;
	}
}
