package modelLogic;

import javafx.scene.paint.Color;

public class Pen {
	
	private static final double DEFAULT_STROKE_WIDTH = 1.0;
	private static final String DEFAULT_COLOR = "BLACK";
	
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
	
	public Color getPenColor() {
		try {
			return (Color) Color.class.getField(myColor).get(new Color(0,0,0,0));
		} catch (Exception e) {
			return Color.BLACK;
		}
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
	
	public void setPenColor(String newColor) {
		myColor = newColor;
	}
}
