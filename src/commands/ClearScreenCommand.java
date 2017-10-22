package commands;

import java.util.List;

import modelLogic.Turtle;

public class ClearScreenCommand implements ExecutableCommand {

	@Override
	public double execute(Turtle tortuga, List<Double> args) {
		return 0;
	}
	
	@Override
	public int paramNumber() {
		return 0;
	} 
}
