package snake.model;

import java.awt.*;
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
 * Cell is super class to every element on the game field.
 * It has methods to operate with animal, which it belongs to,
 * and draw itself in the specifed rectangular.
 */
public abstract class Cell implements GraphicCell {
  private final Animal keeper;
  private boolean changed = true;

  /**
   * get animal owning the cell
   *
   * @return reference to the keeper
   */
  public Animal getKeeper() {
    return keeper;
  }

  protected Cell(Animal keeper) {
    this.keeper = keeper;
  }
  protected void setChanged(boolean changed) {
    this.changed = changed;
  }

  /**
   * check if cell was changed after last drawing
   *
   * @return true if cell was changed
   */
  public boolean isChanged() {
    return changed;
  }

  /**
   * check whether the cell is vacant
   *
   * @return true if cell is vacant
   */
  public final boolean isVacant() {
    return !isBusy();
  }

  /**
   * check whether the cell is busy
   *
   * @return true if cell is busy
   */
  public abstract boolean isBusy();

  public void draw(Graphics g, int x, int y, int w, int h) {
    changed = false;
  }
}
