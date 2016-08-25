package MineClearing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Evaluator {
  private Field field;
  private Script script;
  
  /**
   * Constructor for the Evaluator class.
   * 
   * @param field_lines - as read from the file, the location of the mines
   * @param script_lines - as read from the file, the script to be executed and verified
   */
  public Evaluator(List<String> field_lines, List<String> script_lines) {
    field = new Field(field_lines);
    
    script = new Script();
    for (String line : script_lines) {
      line = line.trim();
      if (line.indexOf(' ') > 0) {
        String[] cmds = line.split(" ");
        
        script.addCommand(cmds[0]);
        script.addCommand(cmds[1]);
      } else {
        script.addCommand(line);
      }
      script.addDropCommand();
    }

    //System.out.print(script.toString());
    //System.out.println("------------------------");
  }
  
  /**
   * Executes the script.
   */
  public void executeScript() {

    int i = 1;
    
    while (script.hasCommands() && !field.failedAlready() && !field.passed()) {
      // System.out.println("------------------------");

      System.out.println(String.format("Step %d", i));
      System.out.println();
      System.out.println(field.printedField());
      
      // System.out.print(field);
      
      Script.Command cmd = script.next();
      
      boolean drop;
      
      do {
        if (field.failedAlready()) {
          break;
        }
        
        System.out.print(cmd.toString() + " ");

        drop = script.isNextDrop();

        switch (cmd) {
          case north:
            field.moveNorth(drop);
            break;
          case south:
            field.moveSouth(drop);
            break;     
          case east:
            field.moveEast(drop);
            break; 
          case west:
            field.moveWest(drop);
            break;
          case alpha:
            field.alpha(drop);
            break;
          case beta:
            field.beta(drop);
            break;
          case gamma:
            field.gamma(drop);
            break;
          case delta:
            field.delta(drop);
            break;
          default:
            break;
        } 
        
        if (drop) {
          script.next();
          break;
        }
        
        cmd = script.next();

      } while (!drop);

      System.out.println();
      System.out.println();
      System.out.println(field.printedField());

      i++;
    }
    
    if (field.passed()) {
      System.out.println(String.format("pass (%d)", field.score()));
    } else {
      System.out.println("fail (0)");
    }
  }
  
  /**
   * The main method called to execute this program.
   * 
   * @param args 
   *        args[0] -> the 'field' file name.
   *        args[1] -> the 'script' file name.
   *        
   * @throws IOException if any of the two files cannot be opened and read properly.
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Proper usage is: java MineClearing field_file script_file.");
      System.exit(0);
    }
    
    List<String> field_lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.ISO_8859_1);
    List<String> script_lines = Files.readAllLines(Paths.get(args[1]), StandardCharsets.ISO_8859_1);
    
    Evaluator evaluator = new Evaluator(field_lines, script_lines);
    evaluator.executeScript();
  }
}
