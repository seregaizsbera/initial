package snake.view.cell;

import java.awt.*;
import snake.model.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * EmptyCell is vacant and doesn't draw anything.
 */
public class EmptyCell extends Cell {
  /**
   * Constructor for EmptyCell object
   */
  public EmptyCell() {
    super(null);
  }

  /**
   * Draw cell in specified rectangule
   *
   * @param g the graphics for drawing
   * @param x the <code>x</code> coordiante of the rectangule
   * @param y the <code>y</code> coordiante of the rectangule
   * @param w width of the rectangular
   * @param h height of the rectangular
   */
  public void draw(Graphics g, int x, int y, int w, int h) {
    super.draw(g, x, y, w, h);
  }

  /**
   * check whether the cell is busy
   *
   * @return true if cell is busy
   */
  public boolean isBusy() {
    return false;
  }
}
