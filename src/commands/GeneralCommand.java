package commands;

import java.util.Map;

import modelLogic.CanvasWriter;
import modelLogic.CommandNameInfo;
import modelLogic.ParsedItem;

public class GeneralCommand extends ExecutableCommand {

	public GeneralCommand() {
		
	}
	
	@Override
	public double execute(ParsedItem[] params, CanvasWriter writer, Map<String, Double> variables,
			Map<String, CommandNameInfo> userFunctions) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String[] paramNumber() {
		return new String[] {};
	}

}
