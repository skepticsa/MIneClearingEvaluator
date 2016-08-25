package MineClearing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Field {
  private ArrayList<Mine> mines;
  private int size_x;
  private int size_y;

  private int min_x = Integer.MAX_VALUE;
  private int min_y = Integer.MAX_VALUE;
  
  private int initial_nof_mines;
  private int shots_fired;
  private int moves;
  
  private boolean failed = false;
  
  /**
   * Build the object for the Mines Field. 
   * 
   * @param field_lines represent the field configuration as read from a file.
   */
  public Field(List<String> field_lines) {
    this.mines = new ArrayList<Mine>();
    
    size_y = field_lines.size();
    int y = 0;
    for (String temp: field_lines) {
      size_x = Math.max(size_x, temp.length());
      
      for (int x = 0; x < temp.length(); x++) {
        char ch = temp.charAt(x);        
        if (ch != '.') {
          mines.add(new Mine(x, y, ch)); //  - (size_y / 2)
          
          min_x = Math.min(min_x, x);
          min_y = Math.min(min_y, y);
        }
      }
      y++;
    }
    
    initial_nof_mines = mines.size();
  }
  
  public boolean passed() {
    return !failedAlready() && mines.size() == 0;
  }
  
  public boolean failedAlready() {
    return failed;
  }
  
  /**
   * The score is 0 (zero) for scenario failure.
   * Otherwise it calculates and returns the score. 
   * 
   * @return the score
   */
  public int score() {
    if (failedAlready()) {
      return 0;
    }
    
    return 10 * initial_nof_mines 
        - Math.min(5 * shots_fired, 5 * initial_nof_mines) 
        - Math.min(2 * moves, 3 * initial_nof_mines);
  }
  
  /**
   * Tells the ship to move North
   * which actually means that the mines will move South.
   * 
   * @param drop - true if the ship needs to drop.
   */
  public void moveNorth(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    moves++;
    
    min_y = Integer.MAX_VALUE;

    for (Mine mine : mines) {
      mine.setY(mine.getY() + 2);
      min_y = Math.min(min_y, mine.getY());
      
      if (drop) {
        mine.goUp();
        if (mine.getDist() <= 0) {
          failed = true;
        }
      }
    }
    size_y += 2;
  }

  /**
   * Tells the ship to move South
   * which actually means that the mines will move North.
   * 
   * @param drop - true if the ship needs to drop.
   */
  public void moveSouth(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    moves++;
    
    if (min_y == 0) {
      size_y += 2;
      for (Mine mine : mines) {
        if (drop) {
          mine.goUp();
          if (mine.getDist() <= 0) {
            failed = true;
          }
        }
      }
    } else {
      for (Mine mine : mines) {
        mine.setY(mine.getY() - 1);
        min_y = Math.min(min_y, mine.getY());
        if (drop) {
          mine.goUp();
          if (mine.getDist() <= 0) {
            failed = true;
          }
        }
      }
    }
  }
  
  /**
   * Tells the ship to move West
   * which actually means that the mines will move East.
   * 
   * @param drop - true if the ship needs to drop.
   */
  public void moveWest(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    moves++;
    
    min_x = Integer.MAX_VALUE;
    for (Mine mine : mines) {
      mine.setX(mine.getX() + 2);
      min_x = Math.min(min_x, mine.getX());
      if (drop) {
        mine.goUp();
        if (mine.getDist() <= 0) {
          failed = true;
        }
      }
    }
    size_x += 2;
  }

  /**
   * Tells the ship to move East
   * which actually means that the mines will move West.
   * 
   * @param drop - true if the ship needs to drop.
   */
  public void moveEast(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    moves++;
    
    if (min_x == 0) {
      size_x += 2;
      for (Mine mine : mines) {
        mine.goUp();
      }
    } else {
      for (Mine mine : mines) {
        mine.setX(mine.getX() - 1);
        min_x = Math.min(min_x, mine.getX());
        if (drop) {
          mine.goUp();
          if (mine.getDist() <= 0) {
            failed = true;
          }
        }
      }
    }
  }
  
  /**
   * IShip going down means that actually the mines go up.
   * 
   */
  public void shipGoDown() {
    if (failedAlready()) {
      return;
    }
    
    for (Mine mine : mines) {
      mine.goUp();
      if (mine.getDist() <= 0) {
        failed = true;
      }
    }
  }
  
  /**
   * Implements the alpha firing pattern.
   * 
   * @param drop - true of the ship needs to drop as well. 
   */
  public void alpha(boolean drop) {
    if (failedAlready()) {
      return;
    }

    Point ship = shipLocation();
    
    Point[] pattern = new Point[4];
    pattern[0] = new Point(ship.x - 1, ship.y - 1);
    pattern[1] = new Point(ship.x - 1, ship.y + 1);
    pattern[2] = new Point(ship.x + 1, ship.y - 1);
    pattern[3] = new Point(ship.x + 1, ship.y + 1);
    
    destroyMines(pattern);
    
    if (drop) {
      shipGoDown();
    }
  }
  
  /**
   * Implements the beta firing pattern.
   * 
   * @param drop - true of the ship needs to drop as well. 
   */
  public void beta(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    Point ship = shipLocation();
    
    Point[] pattern = new Point[4];
    pattern[0] = new Point(ship.x - 1, ship.y);
    pattern[1] = new Point(ship.x    , ship.y - 1);
    pattern[2] = new Point(ship.x    , ship.y + 1);
    pattern[3] = new Point(ship.x + 1, ship.y);
    
    destroyMines(pattern);
    
    if (drop) {
      shipGoDown();
    }
  }
  
  /**
   * Implements the delta firing pattern.
   * 
   * @param drop - true of the ship needs to drop as well. 
   */
  public void delta(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    Point ship = shipLocation();
    
    Point[] pattern = new Point[3];
    pattern[0] = new Point(ship.x, ship.y - 1);
    pattern[1] = ship;
    pattern[2] = new Point(ship.x, ship.y + 1);
    
    destroyMines(pattern);
    
    if (drop) {
      shipGoDown();
    }
  }
  
  /**
   * Implements the gamma firing pattern.
   * 
   * @param drop - true of the ship needs to drop as well. 
   */
  public void gamma(boolean drop) {
    if (failedAlready()) {
      return;
    }
    
    Point ship = shipLocation();
    
    Point[] pattern = new Point[3];
    pattern[0] = new Point(ship.x - 1, ship.y);
    pattern[1] = ship;
    pattern[2] = new Point(ship.x + 1, ship.y);
    
    destroyMines(pattern);
    
    if (drop) {
      shipGoDown();
    }
  }
  
  private void destroyMines(Point[] pattern) {
    if (failedAlready()) {
      return;
    }
    
    shots_fired += pattern.length;
    
    for (int i = mines.size() - 1; i >= 0 ; i--) {
      Mine mine = mines.get(i);
      
      if (mine.getDist() <= 0) {
        failed = true;
      }
      
      for (Point point : pattern) {
        if (mine.getX() == point.x && mine.getY() == point.y && mine.getDist() > 0) {
          mines.remove(i);
        }
      }
    }
  }
  
  private Point shipLocation() {
    return new Point(size_x / 2, size_y / 2);
  }
  
  /**
   * Returns a string representation of the Field.
   *
   * @return the String representation of every Mine + other internal class data.
   */
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(String.format("min_x=%d size_x=%d min_y=%d size_y=%d\n", 
        min_x, size_x, min_y, size_y));
    for (Mine mine : this.mines) {
      builder.append(String.format("x=%d y=%d dist=%d\n", 
          mine.getX(), mine.getY(), mine.getDist()));
    }
    
    return builder.toString();
  }
  
  /**
   * Returns a string representation of the Field to be printed.
   *
   * @return the String representation of every Mine
   */
  public String printedField() {
    StringBuilder[] builder = new StringBuilder[size_y];
   
    char[] items = new char[size_x];
    for (int x = 0;x < size_x;x++) {
      items[x] = '.';
    }
   
    for (int y = 0; y < size_y; y++) {
      builder[y] = new StringBuilder();
      builder[y].append(items);
    }
    
    for (Mine mine : mines) {
      builder[mine.getY()].setCharAt(mine.getX(), mine.dist2char());
    }
     
    StringBuilder printed = new StringBuilder();
    for (int y = 0; y < size_y; y++) {
      printed.append(builder[y]).append("\n");
    }
    return printed.toString();
  }
}
