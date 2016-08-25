package MineClearing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class Script {
  @SuppressWarnings("serial")
  private static final Map<String, Command> commandMap = new HashMap<String, Command>() {
    { 
      put("north", Command.north); 
      put("south", Command.south); 
      put("east", Command.east); 
      put("west", Command.west); 
      put("alpha", Command.alpha); 
      put("beta", Command.beta); 
      put("gamma", Command.gamma); 
      put("delta", Command.delta); 
    }
  };

  /**
   * Stores the commands that are part of the script.
   */
  private final LinkedList<Command> commands = new LinkedList<Command>();
  
  /**
   * The list of possible commands. 
   */
  public enum Command {
    north, south, east, west, alpha, beta, gamma, delta, drop
  }

  /**
   * Add a drop ship command.
   */
  public void addDropCommand() {
    commands.add(Command.drop);
  }
    
  /**
   * Checks if the command is valid and then adds it to the queue.
   * 
   * @param strCommand - string that represents the command
   * @return true if the command is a valid one
   */
  public boolean addCommand(String strCommand) {
    strCommand = strCommand.toLowerCase();
    if (commandMap.containsKey(strCommand)) {
      commands.add(commandMap.get(strCommand));
      return false;
    }

    return true;
  }

  /**
   * Tells the caller if there are still commands left to process.
   * 
   * @return true if there are command left
   */
  public boolean hasCommands() {
    return !commands.isEmpty();
  }
  
  /**
   * Removes the next command from the queue and returns it to the caller.
   * 
   * @return the next command
   */
  public Command next() {
    if (hasCommands()) {
      return commands.remove();
    }
    
    throw new NoSuchElementException("Script::next There are no more commands to process");
  }
  
  /**
   * Checks if the next command is a drop command.
   * 
   * @return true if the next command is a drop command. 
   */
  public boolean isNextDrop() {
    return hasCommands() && commands.peek() == Command.drop;
  }
  
  /**
   * Concerts the object to a string, this is mostly used for debugging. 
   */
  public String toString() {
    StringBuilder builder = new StringBuilder();
    
    ListIterator<Command> it = commands.listIterator();
    while (it.hasNext()) {
      builder.append(it.next().toString());
      builder.append("\n");
    }
    
    return builder.toString();
  }
}
