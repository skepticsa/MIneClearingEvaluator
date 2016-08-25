package Tests;

import static org.junit.Assert.*;

import MineClearing.Evaluator;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TestEvaluator {

  @Test
  public void testCase1() {
    List<String> field_lines = new ArrayList<String>();
    field_lines.add("z");

    List<String> script_lines = new ArrayList<String>();
    script_lines.add("gamma");
    
    Evaluator evaluator = new Evaluator(field_lines, script_lines);
    
    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myOut));
    
    evaluator.executeScript();
    
    final String standardOutput = myOut.toString();
    
    String[] lines = standardOutput.split("\\r?\\n");
    
    assertEquals("Step 1", lines[0]);
    assertEquals("", lines[1]);
    assertEquals("z", lines[2]);
    assertEquals("", lines[3]);
    assertEquals("gamma", lines[4].trim());
    assertEquals("", lines[5]);
    assertEquals(".", lines[6]);
    assertEquals("", lines[7]);
    assertEquals("pass (5)", lines[8]);
  }
}
