package Tests;

import static org.junit.Assert.*;

import MineClearing.Mine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMine {

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

  /**
   * Test method for {@link MineClearing.Mine#Mine(int, int, char)}.
   */
  @Test
  public void testMine() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    int dist = mine.getDist();
    
    // Assert
    assertEquals(4, dist);
  }

  /**
   * Test method for {@link MineClearing.Mine#getX()}.
   */
  @Test
  public void testGetX() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    int x = mine.getX();
    
    // Assert
    assertEquals(2, x);
  }

  /**
   * Test method for {@link MineClearing.Mine#setX(int)}.
   */
  @Test
  public void testSetX() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    mine.setX(11);
    int x = mine.getX();
    
    // Assert
    assertEquals(11, x);
  }

  /**
   * Test method for {@link MineClearing.Mine#getY()}.
   */
  @Test
  public void testGetY() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    int y = mine.getY();
    
    // Assert
    assertEquals(3, y);
  }

  /**
   * Test method for {@link MineClearing.Mine#setY(int)}.
   */
  @Test
  public void testSetY() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    mine.setY(22);
    int y = mine.getY();
    
    // Assert
    assertEquals(22, y);
  }

  /**
   * Test method for {@link MineClearing.Mine#getDist()}.
   */
  @Test
  public void testGetDist() {
    // Arrange
    Mine mine = new Mine(2, 3, 'd');
    
    // Act
    int dist = mine.getDist();
    
    // Assert
    assertEquals(4, dist);
  }

  /**
   * Test method for {@link MineClearing.Mine#goUp()}.
   */
  @Test
  public void testGoUp() {
    // Arrange
    Mine mine = new Mine(1, 2, 'b');
    
    // Act
    mine.goUp();
    int dist = mine.getDist();
    
    // Assert
    assertEquals(1, dist);
  }

  /**
   * Test method for {@link MineClearing.Mine#dist2char()}.
   */
  @Test
  public void testDist2char() {
    // Arrange
    Mine mine = new Mine(1, 2, 'b');
    
    // Act
    char dist = mine.dist2char();
    
    // Assert
    assertEquals('b', dist);
  }

  /**
   * Test method for {@link MineClearing.Mine#toString()}.
   */
  @Test
  public void testToString() {
    // Arrange
    Mine mine = new Mine(1, 2, 'c');
    
    // Act
    String str = mine.toString();
    
    // Assert
    assertEquals("x=1 y=2 dist=3", str);
  }
}
