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
 * SnakeTailCell is busy and belongs to a snake.
 */
public class SnakeTailCell extends Cell {
  /**
   * Constructor for FrogCell object
   *
   * @param keeper owner of new cell
   * @param color color for new cell
   */
  public SnakeTailCell(Animal keeper) {
    super(keeper);
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
    g.setColor(Color.yellow);
    g.fillOval(x + w / 4, y + h / 4, w / 2, h / 2);
  }

  /**
   * check whether the cell is busy
   *
   * @return true if cell is busy
   */
  public boolean isBusy() {
    return true;
  }
}