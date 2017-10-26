package GUI;

public enum Property {
	Direction("Direction of turtle in degrees"),
	XPosition("XPos"), 
	penDown("PenDown"),
	penColor("penColor"),
	penSize("penSize"),
	penStyle("penStyle");
	
	//each class - human-readable name, command, 

	String myName;
	
	private Property(String name) {
		myName = name;
	}
	
	//all commands a sub-class
}
