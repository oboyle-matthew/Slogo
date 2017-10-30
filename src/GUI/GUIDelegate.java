package GUI;

import javafx.scene.shape.Path;

public interface GUIDelegate {
	void runButtonPressed();
	
	void clearButtonPressed();
	
	String getText();
	
	void runCommand(String text);

	void createInstructionsWindow();

	void createNewProject();

	void createCustomizeWindow();

	void moveX(Double newValue);
		
	void setDirection(Double angle);
	
	void changeBackground(String color);
	
	void createTurtle();
	
	void startProject();

	void forwardButtonPressed();

	void backwardButtonPressed();

	void rotateLeftButtonPressed();
	
	void rotateRightButtonPressed();

	String[] getInfo();
	
	void addLine(Path p);
	
	void removeLine(Path p);
	
	void updateTurtleProperties();
}
