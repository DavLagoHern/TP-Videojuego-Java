package control.Commands;

import logic.Game;

public class LightFlashCommand extends Command{
	public LightFlashCommand() {
		super("light", "l", "[l]ight", "kills all the vampires");
	}

	@Override
	public boolean execute(Game game) {
		return game.lightFlash();
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
