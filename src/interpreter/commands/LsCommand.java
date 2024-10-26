package interpreter.commands;

import interpreter.Command;
import interpreter.InterpreterContext;

import java.io.File;

public class LsCommand {
    public String execute(Command command, InterpreterContext context) {
        File currentDir = new File(context.getCurrentDirectory());
        String[] files = currentDir.list();
        StringBuilder output = new StringBuilder();

        if (files != null) {
            for (String file : files) {
                output.append(file).append("\n");
            }
        } else {
            return "Could not list directory contents.";
        }

        return output.toString();
    }
}
