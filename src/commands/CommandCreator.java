package commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * The class that is meant to mimic the object factory design pattern. This class takes in
 * a given string and returns the appropriate ExecutableCommand for that string
 */

public class CommandCreator {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public String language = "English";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	
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
	
	/**
	 * Creates the correct turtle command executable for the given command
	 * @param command is a {@code String} that contains the command to create
	 * @return A {@code ExecutableCommand} that is representative of the string passed in
	 */
	private ExecutableCommand createTurtleCommand(String command) {
		switch (command) {
			case "forward": return new ForwardCommand();
			case "fd": return new ForwardCommand();
			case "back" : return new BackwardCommand();
			case "bk" : return new BackwardCommand();
			case "left" : return new LeftCommand();
			case "lt" : return new LeftCommand();
			case "right" : return new RightCommand();
			case "rt" : return new RightCommand();
			case "setheading" : return new SetHeadingCommand();
			case "seth" : return new SetHeadingCommand();
			case "towards" : return new SetTowardsCommand();
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
	
	/**
	 * Creates the correct query command executable for the given command
	 * @param command is a {@code String} that contains the command to create
	 * @return A {@code ExecutableCommand} that is representative of the string passed in
	 */
	private ExecutableCommand createQueryCommand(String command) {
		switch (command) {
			case "xcor": return new XCoordinateCommand();
			case "ycor": return new YCoordinateCommand();
			case "heading": return new HeadingCommand();
			case "pendown?": return new IsPenDownCommand();
			case "pendownp": return new IsPenDownCommand();
			case "showing?": return new IsShowingCommand();
			case "showingp": return new IsShowingCommand();
		}
		return null;
	}
	
	/**
	 * Creates the correct math command executable for the given command
	 * @param command is a {@code String} that contains the command to create
	 * @return A {@code ExecutableCommand} that is representative of the string passed in
	 */
	private ExecutableCommand createMathCommand(String command) {
		switch(command) {
			case "sum": return new MathOperationsCommand(command);
			case "+": return new MathOperationsCommand("sum");
			case "difference": return new MathOperationsCommand(command);
			case "-": return new MathOperationsCommand("difference");
			case "product": return new MathOperationsCommand(command);
			case "*": return new MathOperationsCommand("product");
			case "quotient": return new MathOperationsCommand(command);
			case "/": return new MathOperationsCommand("quotient");
			case "remainder": return new MathOperationsCommand(command);
			case "%": return new MathOperationsCommand("remainder");
			case "minus": return new MathOperationsCommand(command);
			case "~": return new MathOperationsCommand("minus");
			case "random": return new RandomCommand();
			case "sin": return new TrigCommand(command);
			case "cos": return new TrigCommand(command);
			case "tan": return new TrigCommand(command);
			case "atan": return new TrigCommand(command);
			case "log": return new NaturalLogCommand();
			case "pow": return new PowerCommand();
			case "pi": return new PiCommand();
		}
		return null;
	}
	
	/**
	 * Creates the correct boolean command executable for the given command
	 * @param command is a {@code String} that contains the command to create
	 * @return A {@code ExecutableCommand} that is representative of the string passed in
	 */
	private ExecutableCommand createBooleanCommand(String command) {
		switch(command) {
			case "less?": return new BooleanOperationCommand(command);  
			case "lessp": return new BooleanOperationCommand("less?"); 
			case "greater?": return new BooleanOperationCommand(command);  
			case "greaterp": return new BooleanOperationCommand("greater?"); 
			case "equal?": return new BooleanOperationCommand(command);  
			case "equalp": return new BooleanOperationCommand("equal?"); 
			case "notequal?": return new BooleanOperationCommand(command);  
			case "notequalp": return new BooleanOperationCommand("notequal?"); 
			case "and": return new BooleanOperationCommand(command);  
			case "or": return new BooleanOperationCommand(command);  
			case "not": return new NotCommand();  
		}
		return null; 
	}
	
}
