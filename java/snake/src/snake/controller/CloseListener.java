package snake.controller;

import java.awt.*;
import java.awt.event.*;
import snake.view.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * CloseListener controls program when user clicks "X" (Close) button
 * on the frame
 */
public class CloseListener extends WindowAdapter {
  private final SnakeFrame frame;

  /**
   * Constructor for the CloseListener object
   *
   * @param frame the reference to frame
   */
  public CloseListener(SnakeFrame frame) {
    this.frame = frame;
  }

  public void windowClosing(WindowEvent e) {
    frame.close();
  }
}
