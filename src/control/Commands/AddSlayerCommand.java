package control.Commands;

import logic.Game;

public class AddSlayerCommand extends Command {
	private int x;
	private int y;

	public AddSlayerCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		return game.addSlayerIfYouCan(this.x, this.y);
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			if (commandWords.length == 3) {
			this.x = Integer.parseInt(commandWords[1]);
			this.y = Integer.parseInt(commandWords[2]);
			return this;
			}
			else return null;
		}
		else return null;
	}

	@Override
	public boolean longArgs(String[] words) {
		if (words.length == 3)
			return true;
		return false;
	};

}
