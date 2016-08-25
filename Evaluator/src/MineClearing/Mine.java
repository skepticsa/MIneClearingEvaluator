package MineClearing;

public class Mine {
  private int x; 
  private int y;
  private int dist;
  
  /**
   * The Mine constructor.
   * 
   * @param x - the x coordinate
   * @param y - the y coordinate
   * @param dist - the distance represented as a char
   *        'a'  1 km away to 'z' 26 km away
   *        'A' 27 km away to 'Z' 52 km away
   */
  public Mine(int x, int y, char dist) {
    this.x = x;
    this.y = y;
    
    if (dist >= 'a' && dist <= 'z') { 
      this.dist = (int)dist - 'a' + 1;
    } else if (dist >= 'A' && dist <= 'Z') { 
      this.dist = (int)dist - 'A' + 27;
    } else {
      throw new java.lang.IllegalArgumentException(
          "Valid values for distance are 'a' to 'z' and 'A' to 'Z'");
    }
  }
  
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getDist() {
    return dist;
  }
  
  public void goUp() {
    dist -= 1;
  }
  
  /**
   * Converts the distance from km into a character.
   * 
   * @return character representation 
   */
  public char dist2char() {
    if (dist >= 1 && dist <= 26) {
      return (char)('a' + dist - 1);
    } else if (dist >= 27 && dist <= 52) {
      return (char)('A' + dist - 27);
    }
    
    return '*';
        
    // Execution should never reach this point!
    // The Mine constructor above should have caught the invalid distance.
    // throw new AssertionError(String.format("Mine.dist2char: Invalid distance %d", dist));
  }
  
  /**
   * Returns a string representation of the Mine.
   *
   * @return the x and y coordinates followed by the distance
   */
  public String toString() {
    return String.format("x=%d y=%d dist=%d", x, y, dist);
  }
}
