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
 * StartListener listens to the "Start" button
 */
public class StartListener implements ActionListener {
  private final SnakeModel model;

  /**
   * Constructor for the StartListener object
   *
   * @param model the reference to snake model
   */
  public StartListener(SnakeModel model) {
    this.model = model;
  }

  public void actionPerformed(ActionEvent e) {
    model.startGame();
  }
}
