package modelLogic;

public class Pen {
	
	private String myColor;
	private String myStyle;
	private double mySize;
	private boolean myStatus;

	public Pen() {
		myColor = "BLACK";
		mySize = 1.;
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
