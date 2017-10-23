package modelLogic;

import java.util.Map;

import commands.ExecutableCommand;

public class ParsedCommand extends ParsedItem {
	
	private static final double DEFAULT_VARIABLE_VALUE = 0;
	private ExecutableCommand myCommand; 
	
	ParsedCommand(ExecutableCommand command) {
		myCommand = command; 
	}

	@Override
	public String getItemType() {
		return COMMAND;
	}
	
	public double execute(ParsedItem[] params, Turtle tortuga, Map<String, Double> variables) {
		updateParams(params, variables);
		return myCommand.execute(params, tortuga, variables);
	}
	
	private void updateParams(ParsedItem[] params, Map<String, Double> variables) {
		for(int i = 0; i < params.length; i++) {
			if(!params[i].getItemType().equals(COMMAND) && !params[i].getItemType().equals(BRACKET_PARAM)) {
				ParsedRegularParameter p = (ParsedRegularParameter) params[i];
				if(p.isVariable()) {
					String val = "" + variables.getOrDefault(p.getValue(), DEFAULT_VARIABLE_VALUE);
					params[i] = new ParsedRegularParameter(val, false);
				}
			}
		}
	}
	
	public String[] getParameterOrder() {
		return myCommand.paramNumber();
	}

}
