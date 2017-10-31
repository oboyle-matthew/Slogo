package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

public class AskCommand extends ExecutableCommand {
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		ParsedBracketParameter p = (ParsedBracketParameter) params[0];
		ParsedBracketParameter commands = (ParsedBracketParameter) params[1];
		String[] ids = p.getStringValues();
		for(String s : ids) {
			try {
				double id = Double.parseDouble(s);
				if(writer.getId() == id) {
					writer.setActive(true);
					double ret = commands.executeCommands(writer, variables);
					writer.setActive(false);
					return ret;
				}
			} catch(Exception e) {
				continue;
			}
		}
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
