package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");

	}

	@Override
	public boolean execute(Game game) {
		CommandGenerator.HelpText();
		return false;
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
