package control.Commands;

import logic.Game;

public class GarlicPushCommand extends Command {
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic ", "pushes back vampires");
	}

	@Override
	public boolean execute(Game game) {	
		return game.garlicPush();
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
