package control.Commands;

import logic.Game;

public class AddBloodBankCommand extends Command{
	private int x;
	private int y;
	private int z;
	

	public AddBloodBankCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank with cost z in position x, y.");
	}

	@Override
	public boolean execute(Game game) {
		return game.addBloodBank(this.x, this.y, this.z);
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
		if (commandWords.length == 4) {
			this.x = Integer.parseInt(commandWords[1]);
			this.y = Integer.parseInt(commandWords[2]);
			this.z = Integer.parseInt(commandWords[3]);
			return this;
		}
		else return null;
		}
		else return null;
	}

	@Override
	public boolean longArgs(String[] words) {
		if (words.length == 4)
			return true;
		return false;
	};
}
