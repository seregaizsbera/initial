package snake.model.animal;

import java.awt.*;
import java.util.*;
import snake.model.*;
import snake.model.animal.*;
import snake.view.*;
import snake.view.cell.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Blue frogs are animals with limit life time, because snake dies
 * immediately after eating one of them.
 */
public class BlueFrog extends Frog {
  private final long createTime;

  /**
   * Constructor for the BlueFrog object
   *
   * @param field reference to field
   */
  public BlueFrog(Field field) {
    super(field, Color.blue);
    Calendar calendar = Calendar.getInstance();
    createTime = calendar.getTime().getTime();
  }

  protected boolean step() {
    Calendar calendar = Calendar.getInstance();
    if(calendar.getTime().getTime() - createTime >= Param.blueFrogLife * 100) {
      field.putCell(getPlace(), new EmptyCell());
      return false;
    }
    return super.step();
  }

  /**
   * This method is invoked, when the "eater", wants to eat this animal.
   *
   * @param eater reference to animal which has eaten the object
   */
  public boolean youAreEaten(Animal eater) {
    stop();
    boolean res = ((Snake)(eater)).eatBlueFrog(getPlace());
    return res;
  }

  /**
   * Returns number of scores to be given as a price for eating this animal.
   *
   * @return price for this object
   */
  public int price() {
    return 10;
  }
}
