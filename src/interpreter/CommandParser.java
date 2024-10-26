package interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
  public Command parse(String input) {
    // System.out.println(input);
    if (input == null || input.isEmpty()) {
      return null;
    }
    // Split by pipe operator first for command chaining
    String[] pipeSegments = input.split("\\|");
    Command previousCommand = null;

    for (String segment : pipeSegments) {
      // Check for output redirection operators `>` and `>>`
      String[] redirectionSegments = { segment.trim() };
      boolean append = false;

      if (segment.contains(">>")) {
        redirectionSegments = segment.split(">>", 2);
        append = true;
      } else if (segment.contains(">")) {
        redirectionSegments = segment.split(">", 2);
      }
      // for (String seg : redirectionSegments) {
      //   System.out.println(seg);
      // }
      // System.out.println(append);

      String commandPart = redirectionSegments[0].trim();
      List<String> parts = new ArrayList<>(Arrays.asList(commandPart.split("\\s+")));
      String commandName = parts.get(0);
      // System.out.println(commandName);
      List<String> arguments;

      // Safely handle arguments extraction
      if (parts.size() > 1) {
        arguments = parts.subList(1, parts.size());
      } else {
        arguments = new ArrayList<>();
      }
      // for (String arg : arguments) {
      //   System.out.println(arg);
      // }

      Command command = new Command(commandName, arguments);

      // Handle redirection if present
      if (redirectionSegments.length > 1) {
        // Remove any leading `>` symbols
        String outputFile = redirectionSegments[1].replaceAll("^>+", "").trim();
        command.setOutputFile(outputFile, append);
      }

      // Link the previous command for piping
      if (previousCommand != null) {
        previousCommand.setNextCommand(command);
      }

      previousCommand = command;
    }

    // System.out.println(previousCommand.getName());
    // System.out.println(previousCommand.getArguments());
    // System.out.println(previousCommand.getOutputFile());
    // System.out.println(previousCommand.getNextCommand());

    // Return the first command in the pipeline
    return previousCommand;
  }
}
