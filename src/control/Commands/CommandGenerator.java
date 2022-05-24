package control.Commands;

public class CommandGenerator{

    private static Command[] availableCommands = {
           new AddSlayerCommand(),
            new HelpCommand(),
            new ResetCommand(),
            new ExitCommand(),
            new UpdateCommand(),
            new GarlicPushCommand(),
            new LightFlashCommand(),
            new AddBloodBankCommand(),
            new SuperCoinsCommand(),
            new AddVampireCommand()

            };

    public static Command parse(String[] commandWords) {
        for (Command c: availableCommands) {
            Command parsedCommand = c.parse(commandWords);
            if (parsedCommand != null) return parsedCommand;
        }
        return null;
    }
    
  
    public static void HelpText() {
    	System.out.println("Available commands:");
		for (Command c : availableCommands)
			System.out.print(c.helpText());
	}

}