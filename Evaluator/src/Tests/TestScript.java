package Tests;

import static org.junit.Assert.*;

import MineClearing.Script;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScript {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testAddDropCommand() {
    // Arrange
    Script script = new Script();
    
    // Act
    script.addDropCommand();
    
    // Assert
    assertTrue(script.isNextDrop());
  }

  @Test
  public void testAddCommand() {
    // Arrange
    Script script = new Script();
    
    // Assert
    script.addCommand("north");
    
    // Assert
    assertTrue(script.hasCommands());
  }

  @Test
  public void testHasCommands() {
    // Arrange
    Script script = new Script();
    
    // Assert
    assertFalse(script.hasCommands());
    
    // Act
    script.addCommand("north");
    
    // Assert
    assertTrue(script.hasCommands());
  }

  @Test
  public void testNext() {
    // Arrange
    Script script = new Script();
    
    // Act
    script.addCommand("north");
    Script.Command cmd = script.next();
    
    // Assert
    assertEquals(Script.Command.north, cmd);
  }

  @Test
  public void testIsNextDrop() {
    // Arrange
    Script script = new Script();
    
    // Assert
    assertFalse(script.isNextDrop());
    
    // Act
    script.addDropCommand();
    
    // Assert
    assertTrue(script.isNextDrop());
  }

  @Test
  public void testToString() {
    // Arrange
    Script script = new Script();
    
    // Act
    script.addCommand("north");
    script.addCommand("east");
    script.addCommand("south");
    script.addCommand("west");
    
    String str = script.toString();
    
    // Assert
    assertEquals("north\neast\nsouth\nwest\n", str);
  }
}
