package snake.model.animal;

import java.awt.*;
import snake.model.*;
import snake.model.animal.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Red frogs cost two scores
 */
public class RedFrog extends Frog {
  /**
   * Constructor for the GreenFrog object
   *
   * @param field reference to field
   */
  public RedFrog(Field field) {
    super(field, Color.red);
  }

  /**
   * This method is invoked, when the "eater", wants to eat this animal.
   *
   * @param eater reference to animal which has eaten the object
   */
  public boolean youAreEaten(Animal eater) {
    stop();
    boolean res = ((Snake)(eater)).eatRedFrog(getPlace());
    return res;
  }

  /**
   * Returns number of scores to be given as a price for eating this animal.
   *
   * @return price for this object
   */
  public int price() {
    return 2;
  }
}
