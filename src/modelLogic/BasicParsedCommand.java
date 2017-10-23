package modelLogic;

import commands.ExecutableCommand;

public class BasicParsedCommand extends ParsedCommand {

	private ExecutableCommand myExecutable; 
	
	BasicParsedCommand(String command) {
		super(command);
		myExecutable = createExecutableCommand(command);
	}

	private ExecutableCommand createExecutableCommand(String command) {
		try {
			Class<?> c = Class.forName("commands." + command + "Command");
			return (ExecutableCommand) c.newInstance();
		} catch (Exception e) {
			return null; 
		}
	}

	@Override
	public void executeCommands() {
		
	}

	@Override
	public int numParams() {
		return myExecutable.paramNumber();
	}
	
}
