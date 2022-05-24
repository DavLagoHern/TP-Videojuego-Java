package control;

import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import logic.Game;

public class Controller {

	public final String prompt = "Command > ";
	public static final String unknownCommandMsg = "[ERROR]: Unknown command";

	private Game game;
	private Scanner scanner;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	public void run() {
		boolean refreshDisplay = true;

		while (!game.isFinished()) {

			if (refreshDisplay)game.printGame();
			refreshDisplay = false;

			System.out.println(prompt);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			Command command = CommandGenerator.parse(parameters);
			if (command != null) {
				refreshDisplay = command.execute(game);
			}
			else {
				System.out.println(unknownCommandMsg);
			}

		}

		if (refreshDisplay)game.printGame();
		System.out.println("[Game over] " + game.getGameOverInfo());

	}

}
