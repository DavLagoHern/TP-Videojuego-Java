package control.Commands;

import logic.Game;

public class AddVampireCommand extends Command {

	private int x;
	private int y;
	private String type;

	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}",
				"add a vampire in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		return game.createVampireCommand(this.type, this.x, this.y);
	}

	@Override
	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 3) {
				this.x = Integer.parseInt(commandWords[1]);
				this.y = Integer.parseInt(commandWords[2]);
				return this;
				
			} else if (commandWords.length == 4) {
				this.type = commandWords[1];
				this.x = Integer.parseInt(commandWords[2]);
				this.y = Integer.parseInt(commandWords[3]);
				return this;
			}
			else return null;
			
		} else return null;
	}

	@Override
	public boolean longArgs(String[] words) {
		if (words.length == 3 || words.length == 4)
			return true;
		return false;
	};
}
