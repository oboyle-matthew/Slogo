package GUI;

public interface GUIDelegate {
	void runButtonPressed();
	
	void clearButtonPressed();
	
	String getText();
	
	void runCommand(String text);

	void createInstructionsWindow();
	
	
}
