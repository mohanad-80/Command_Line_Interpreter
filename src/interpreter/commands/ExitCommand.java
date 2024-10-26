package interpreter.commands;

import interpreter.Command;

public class ExitCommand {
    public void execute(Command command) {
        System.out.println("Exiting CLI...");
        System.exit(0);
    }
}
