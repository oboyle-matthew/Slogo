package commands;

import java.util.Arrays;
import java.util.HashSet;

import modelLogic.Turtle;

public class CommandCreator {
	/**
	 * In order to optimize the speed of this class, the commands are broken down into 
	 * their separate categories, which allows them to get routed initially to methods 
	 * that will have less strings to compare the original command with. 
	 */
	/* Turtle Command Strings */ 
	private static final String[] TURTLE_COMMANDS = {
			"forward", "fd", "back", "bk", "left", "lt", "right", "rt", "setheading", "seth",
			"towards","setxy", "goto", "pendown", "pd", "penup", "pu", "showturtle", "st", 
			"hideturtle", "ht", "home", "clearscreen"
	};
	private static final HashSet<String> TURTLE_COMMANDS_SET = new HashSet<>(Arrays.asList(TURTLE_COMMANDS));
	
	/* Turtle Query Command Strings */
	private static final String[] TURTLE_QUERY_COMMANDS = {
			"xcor", "ycor", "heading", "pendown?","pendownp","showing?","showingp"
	};
	private static final HashSet<String> TURTLE_QUERY_COMMANDS_SET = new HashSet<>(Arrays.asList(TURTLE_QUERY_COMMANDS));
	
	/* Math Command Strings */ 
	private static final String[] MATH_COMMANDS = {
			"sum", "+", "difference", "-", "product","*", "quotient", "/", "remainder", "%", 
			"minus", "~", "random", "sin", "cos", "tan","atan", "log", "pow", "pi"
	}; 
	private static final HashSet<String> MATH_COMMANDS_SET = new HashSet<>(Arrays.asList(MATH_COMMANDS));
	
	/* Boolean Command Strings */
	private static final String[] BOOLEAN_COMMANDS = {
			"less?", "lessp", "greater?", "greaterp", "equal?","equalp","notequal?","notequalp",
			"and","or","not"
	}; 
	private static final HashSet<String> BOOLEAN_COMMANDS_SET = new HashSet<>(Arrays.asList(BOOLEAN_COMMANDS));
	

	/**
	 * Creates an ExecutableCommand that corresponds to the given command. 
	 * @param command is a {@code String} that is the string of the command you want to execute
	 * @return A {@code ExecutableCommand} representing the command for the given string
	 */
	public ExecutableCommand getExecutableCommand(String command) {
		command = command.toLowerCase(); 
		if(TURTLE_COMMANDS_SET.contains(command)) {
			return createTurtleCommand(command); 
		} else if(TURTLE_QUERY_COMMANDS_SET.contains(command)) {
			return createQueryCommand(command); 
		} else if(MATH_COMMANDS_SET.contains(command)) {
			return createMathCommand(command); 
		} else if(BOOLEAN_COMMANDS_SET.contains(command)){
			return createBooleanCommand(command);
		}
		return null; 
	}	
	
	private ExecutableCommand createTurtleCommand(String command) {
		switch (command) {
			case "forward": return new ForwardCommand();
			case "fd": return new ForwardCommand();
			case "back" : return new BackwardsCommand();
			case "bk" : return new BackwardsCommand();
			case "left" : return new LeftRotateCommand();
			case "lt" : return new LeftRotateCommand();
			case "right" : return new RightRotateCommand();
			case "rt" : return new RightRotateCommand();
			case "setheading" : return new SetHeadingCommand();
			case "seth" : return new SetHeadingCommand();
			case "towards" : return new TowardsCommand();
			case "setxy" : return new SetPositionCommand();
			case "goto" : return new SetPositionCommand();
			case "pendown" : return new PenDownCommand();
			case "pd" : return new PenDownCommand();
			case "penup" : return new PenUpCommand();
			case "pu" : return new PenUpCommand();
			case "showturtle" : return new ShowTurtleCommand();
			case "st" : return new ShowTurtleCommand();
			case "hideturtle" : return new HideTurtleCommand();
			case "ht" : return new HideTurtleCommand();
			case "home" : return new HomeCommand();
			case "clearscreen" : return new ClearScreenCommand();
			case "cs" : return new ClearScreenCommand();
		}
		return null;
	}
	private ExecutableCommand createQueryCommand(String command) {
		switch (command) {
			case "xcor": return new GetXCoordinateCommand();
			case "ycor": return new GetYCoordinateCommand();
			case "heading": return new GetHeadingCommand();
			case "pendown?": return new GetPenDownCommand();
			case "pendownp": return new GetPenDownCommand();
			case "showing?": return new GetVisibilityCommand();
			case "showingp": return new GetVisibilityCommand();
		}
		return null;
	}
	private ExecutableCommand createMathCommand(String command) {
		return null;
	}
	
	private ExecutableCommand createBooleanCommand(String command) {
		return null;
	}
	
}
