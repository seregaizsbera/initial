package snake.view;

import java.awt.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Every cell to be drawn on the FieldPanel must implement interface GraphicCell
 */
public interface GraphicCell {
  /**
   * Draw cell in specified rectangule
   *
   * @param g the graphics for drawing
   * @param x the <code>x</code> coordiante of the rectangule
   * @param y the <code>y</code> coordiante of the rectangule
   * @param w width of the rectangular
   * @param h height of the rectangular
   */
  void draw(Graphics g, int x, int y, int w, int h);
}