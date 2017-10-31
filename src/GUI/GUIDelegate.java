package GUI;

import java.util.Map;

import javafx.scene.Node;

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
	
	void addNode(Node n);
	
	void removeNode(Node n);
	
	void updateTurtleProperties();

	void changeFontWidth(Integer size);

	void clearCanvas();

	void changeFontColor(int index);
	
	void changeTurtle(int index);

	void addTurtleFile();

	void changePenStatus(boolean UpDown);

	//for left forward
	void leftForwardButtonPressed();

	//for right forward
	void rightForwardButtonPressed();
	
	void updateVarBox(Map<String, Double> map);

	void updateVarSet(Object variable, double newValue);

}
