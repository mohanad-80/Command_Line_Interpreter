package interpreter.commands;

import interpreter.Command;
import interpreter.InterpreterContext;

import java.io.File;
import java.util.List;

public class LsCommand {
  public String execute(Command command, InterpreterContext context) {
    boolean showAll = false;
    boolean reverseOrder = false;
    File currentDir = new File(context.getCurrentDirectory());
    StringBuilder output = new StringBuilder();
    List<String> arguments = command.getArguments();

    for (String arg : arguments) {
      if (arg.equals("-a")) {
        showAll = true;
      } else if (arg.equals("-r")) {
        reverseOrder = true;
      } else {
        currentDir = new File(arg); // Assume it's a directory path if it's not a flag
      }
    }

    if (!currentDir.exists()) {
      return "Error: Directory does not exist.";
    }
    if (!currentDir.isDirectory()) {
      return "Error: Not a directory.";
    }

    String[] files = currentDir.list();

    if (files == null) {
      return "Error: Unable to access directory contents.";
    }
    if (files.length == 0) {
      return "Directory is empty.";
    }

    if (reverseOrder) {
      for (int i = files.length - 1; i >= 0; i--) {
        // skip hidden files
        if (!showAll && files[i].startsWith(".")) {
          continue;
        }
        output.append(files[i]).append("\n");
      }
    } else {
      for (String file : files) {
        // skip hidden files
        if (!showAll && file.startsWith(".")) {
          continue;
        }
        output.append(file).append("\n");
      }
    }
    return output.toString();
  }
}
