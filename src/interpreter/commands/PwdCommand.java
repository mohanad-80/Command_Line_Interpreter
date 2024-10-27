package interpreter.commands;

import interpreter.Command;
import interpreter.InterpreterContext;

public class PwdCommand {
    public String execute(Command command, InterpreterContext context) {
        return context.getCurrentDirectory().toString();
    }
}
