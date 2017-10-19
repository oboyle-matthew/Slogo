package commands;

import modelLogic.Turtle;

public class ExecutableCommandCreator {
	
	public ExecutableCommand getExecutableCommand(String command, Turtle tortuga, double[] args) {
		
		return new ForwardCommand(); 
	}
	
}
