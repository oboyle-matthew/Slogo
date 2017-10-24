package commands;

import java.util.Map;

import modelLogic.ParsedItem;
import modelLogic.ParsedRegularParameter;
import modelLogic.Turtle;

public class BooleanOperationCommand extends ExecutableCommand {

/* Final Variables */
	private static final String LESS = "less?";
	private static final String GREATER = "greater?";
	private static final String EQUAL = "equal?";
	private static final String NOTEQUAL = "notequal?";
	private static final String AND = "and";
	private static final String OR = "or";

/* Instance Variables */ 
	private String myCommand; 
	
	BooleanOperationCommand(String command) {
		myCommand = command; 
	}

	@Override
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		
		double value1 = Double.parseDouble(((ParsedRegularParameter) params[0]).toString());
		double value2 = Double.parseDouble(((ParsedRegularParameter) params[1]).toString());

		if(myCommand.equals(LESS)) return (value1 < value2 ? 1 : 0);
		if(myCommand.equals(GREATER)) return (value1 > value2 ? 1 : 0);
		if(myCommand.equals(EQUAL)) return (value1 == value2 ? 1 : 0);
		if(myCommand.equals(NOTEQUAL)) return (value1 != value2 ? 1 : 0);
		if(myCommand.equals(AND)) return (value1 > 0 && value2 > 0 ? 1 : 0);
		if(myCommand.equals(OR)) return (value1 > 0 || value2 > 0 ? 1 : 0);
		return 0;
	}
}
