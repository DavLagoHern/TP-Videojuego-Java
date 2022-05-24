package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("none", "n", "[n]one | []", "update");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.updateBoard();
		return true;
	}

	@Override
	public Command parse(String[] words) {
		if (words[0].equals(""))
			words[0] = "n";
		return parseNoParamsCommand(words);
	}

	@Override
	public boolean longArgs(String[] words) {
		if (words.length == 1)
			return true;
		return false;
	};

}
