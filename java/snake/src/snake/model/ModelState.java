package snake.model;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * ModelState provides constants, representing state of game
 */
public interface ModelState {
  /**
   * Game is stopped
   */
  int STOPPED = 1;

  /**
   * Game is started
   */
  int STARTED = 2;

  /**
   * Game is paused
   */
  int PAUSED = 3;
}
