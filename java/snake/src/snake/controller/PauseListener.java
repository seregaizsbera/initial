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
 * PauseListener listens to the "Pause" button
 */
public class PauseListener implements ActionListener {
  private final SnakeModel model;

  /**
   * Constructor for the PauseListener object
   *
   * @param model the reference to snake model
   */
  public PauseListener(SnakeModel model) {
    this.model = model;
  }

  public void actionPerformed(ActionEvent e) {
    if(model.getState() == ModelState.PAUSED)
      model.resumeGame();
    else
      model.pauseGame();
  }
}
