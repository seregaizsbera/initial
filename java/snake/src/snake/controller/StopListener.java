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
 * StopListener listens to the "Stop" button
 */
public class StopListener implements ActionListener {
  private final SnakeModel model;

  /**
   * Constructor for the StopListener object
   *
   * @param model the reference to snake model
   */
  public StopListener(SnakeModel model) {
    this.model = model;
  }
  public void actionPerformed(ActionEvent e) {
    model.stopGame();
  }
}
