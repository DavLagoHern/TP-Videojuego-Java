package control.Commands;

import logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("exit", "e", "[e]xit", "exit game");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.doExit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}
	
	@Override
	public boolean longArgs(String[] words) {
		if(words.length == 1)return true;
		return false;
	};
	

}
