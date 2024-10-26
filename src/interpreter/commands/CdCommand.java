package interpreter.commands;

import interpreter.Command;
import interpreter.InterpreterContext;
import java.io.File;

public class CdCommand {
  public String execute(Command command, InterpreterContext context) {
    // Get the path from command arguments
    String targetPath = command.getArguments().isEmpty() ? "" : command.getArguments().get(0);
    File dir;

    // Handle special case for home directory
    if (targetPath.equals("..")) {
      // Move to the parent directory
      dir = new File(context.getCurrentDirectory()).getParentFile();
    } else {
      // Create a File object for the target path
      dir = new File(context.getCurrentDirectory(), targetPath);
    }

    // Check if the directory exists and is a directory
    if (dir.exists() && dir.isDirectory()) {
      context.setCurrentDirectory(dir.getAbsolutePath()); // Update the context's current directory
      return "Changing directory to: '" + dir.getAbsolutePath() + "'";
    } else {
      return "No such directory: " + dir.getAbsolutePath(); 
    }
  }
}
