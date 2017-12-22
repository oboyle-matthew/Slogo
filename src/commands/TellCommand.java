package commands;
import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedBracketParameter;
import modelLogic.ParsedItem;

public class TellCommand extends ExecutableCommand {

	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		ParsedBracketParameter p = (ParsedBracketParameter) params[0];
		String[] ids = p.getStringValues();
		double last = 0;
		for(String s : ids) {
			try {
				double id = Double.parseDouble(s);
				if(writer.getId() == id) { 
					writer.setActive(true);
					last = id;
				}
			} catch(Exception e) {
				continue;
			}
		}
		return last;
		
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] { BRACKET_PARAM };
	}
	
	@Override 
	public boolean mustBeActiveToExecute() {
		return false; 
	}
}
