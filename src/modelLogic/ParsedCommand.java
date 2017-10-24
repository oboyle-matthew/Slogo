package modelLogic;

import java.util.Map;

import commands.ExecutableCommand;

public class ParsedCommand extends ParsedItem {
	
	private static final double DEFAULT_VARIABLE_VALUE = 0;
	private String command; 
	
	ParsedCommand(String commandName) {
		command = commandName; 
	}
	
	/**
	 * Creates an ExecutableCommand object from the given Command 
	 * @param command
	 * @return
	 */
	private ExecutableCommand createExecutableCommand(String command) {
		try {
			Class<?> c = Class.forName("commands." + command + "Command");
			return (ExecutableCommand) c.newInstance();
		} catch (Exception e) {
			return null; 
		}
	}

	
	@Override
	public String getItemType() {
		return COMMAND;
	}
	
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		if(!myString.equals("MakeVariable")) updateParams(params, variables);
		return createExecutableCommand(command).execute(params, tortuga, variables);
	}
	
	private void updateParams(ParsedItem[] params, Map<String, Double> variables) {
		for(int i = 0; i < params.length; i++) {
			if(!params[i].getItemType().equals(COMMAND) && !params[i].getItemType().equals(BRACKET_PARAM)) {
				ParsedRegularParameter p = (ParsedRegularParameter) params[i];
				if(p.isVariable()) {
					
					for(String key : variables.keySet())
						System.out.println(key + " : " + variables.get(key));
					String val = "" + variables.getOrDefault(p.getValue(), DEFAULT_VARIABLE_VALUE);
					
					params[i] = new ParsedRegularParameter(val, false);
				}
			}
		}
	}
	
	public String[] getParameterOrder() {
		return createExecutableCommand(command).paramNumber();
	}


	@Override
	public ParsedItem getCopy() {
		return new ParsedCommand(command);
	}

}
