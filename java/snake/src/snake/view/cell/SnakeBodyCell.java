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
 * SnakeBodyCell is busy and belongs to a snake.
 */
public class SnakeBodyCell extends FrogCell {
  /**
   * Constructor for SnakeBody object
   *
   * @param keeper owner of new cell
   */
  public SnakeBodyCell(Animal keeper) {
    super(keeper, Color.yellow);
  }
}