package control.Commands;

import logic.Game;

public class SuperCoinsCommand extends Command {

	public SuperCoinsCommand() {
		super("coins", "c", "[c]oins", "add 1000 coins");
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean execute(Game game) {
		game.giveCoinsCommand(1000);
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
	}
}
