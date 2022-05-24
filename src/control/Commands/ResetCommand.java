package control.Commands;

import logic.Game;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("reset", "r", "[r]eset", "reset game");
	}

	@Override
	public boolean execute(Game game) {
		game.reset(game.giveSeed(), game.giveLevel());
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

	@Override
	public boolean longArgs(String[] words) {
		if (words.length == 1)
			return true;
		return false;
	};

}
