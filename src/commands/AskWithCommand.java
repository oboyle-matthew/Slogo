package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

public class AskWithCommand extends ExecutableCommand {
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		ParsedBracketParameter conditionCommands = (ParsedBracketParameter) params[0];
		ParsedBracketParameter commands = (ParsedBracketParameter) params[1];
		boolean priorStatus = writer.isActivated();
		if(!priorStatus) writer.setActive(true);
		double condition = conditionCommands.executeCommands(writer, variables, userFunctions);
		if(condition > 0) {
			return commands.executeCommands(writer, variables, userFunctions);
		}
		if(!priorStatus) writer.setActive(false);
		return 0;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] { BRACKET_PARAM, BRACKET_PARAM };
	}
	
	@Override 
	public boolean mustBeActiveToExecute() {
		return false; 
	}
}
