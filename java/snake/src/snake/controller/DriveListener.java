package snake.controller;

import java.awt.event.*;
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
 * DriveListener listenes to pressing of mouse buttons when mouse
 * is in game field.
 */
public class DriveListener extends MouseAdapter {
  private final SnakeModel model;

  /**
   * Constructor for the DriveListener object
   *
   * @param model the reference to snake model
   */
  public DriveListener(SnakeModel model) {
    this.model = model;
  }

  public void mousePressed(MouseEvent e) {
    if((e.getModifiers() & e.BUTTON1_MASK) == e.BUTTON1_MASK) {
      model.snakeLeft();
      e.consume();
    } else if((e.getModifiers() & e.BUTTON3_MASK) == e.BUTTON3_MASK) {
      model.snakeRight();
      e.consume();
    }
    super.mousePressed(e);
  }
}
