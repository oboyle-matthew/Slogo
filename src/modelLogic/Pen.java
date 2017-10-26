package modelLogic;

public class Pen {
	
	private int myColor;
	private double mySize;
	private double myStyle;
	private boolean myStatus;

	public Pen() {
		myColor = 0;
		mySize = 0;
		myStatus = true;
	}
	
	public boolean getPenInfo() {
		return myStatus;
	}

	public double getPenSize() {
		return 1.2;
	}

	public String getPenStyle() {
		return "SOLID";
	}
	
	public void setPenInfo() {
	}

	public void setPenSize() {
	}

	public void setPenStyle() {
	}
}
