package commands;

import java.util.List;

import modelLogic.Turtle;

/**
 * This interface serves as the single interface implemented by each command 
 * class. This interface allows for our object creator to return back any type
 * of command to the controller who executes it blindly, with no knowledge of 
 * what the command is 
 */

public interface ExecutableCommand {

	/**
	 * This is the main functionality of this interface. This method describes what
	 * an executable command actually will be able to do in the application
	 * 
	 * @param tortuga is a {@code Turtle} object representing the turtle on the screen you want to call the command with 
	 * @param args is a {@code double[]} of values to be passed on to the command for its use  
	 * @return A {@code double} that represents the result of the command after execution 
	 */
	public double execute(Turtle tortuga, List<Double> args); 
	
}
