package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;

/**
 * This abstract class serves as the class extended by each command 
 * class. This class allows for our object creator to return back any type
 * of command to the CommandParser who executes it blindly, with no knowledge of 
 * what the command is 
 */

public abstract class ExecutableCommand {
	
	protected static final String COMMAND = "command";
	protected static final String BRACKET_PARAM = "bracket parameter";
	protected static final String REGULAR_PARAM = "regular parameter";
	

	/**
	 * This is the main functionality of this interface. This method describes what
	 * an executable command actually will be able to do in the application
	 * @param writer is a {@code Turtle} object representing the turtle on the screen you want to call the command with 
	 * @param userFunctions TODO
	 * @param args is a {@code double[]} of values to be passed on to the command for its use  
	 * 
	 * @return A {@code double} that represents the result of the command after execution 
	 */
	public abstract double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables, Map<String, CommandNameInfo> userFunctions); 
	
	public String[] paramNumber() {
		return new String[] {};
	}
	
	public ExecutableCommand getCopy() {
		return this;
	}
	
	public boolean mustBeActiveToExecute() {
		return true;
	}
	
}
