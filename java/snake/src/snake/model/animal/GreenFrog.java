package snake.model.animal;

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
 * Green frogs cost one score.
 */
public class GreenFrog extends Frog {
  /**
   * Constructor for the GreenFrog object
   *
   * @param field reference to field
   */
  public GreenFrog(Field field) {
    super(field, Color.green);
  }

  /**
   * This method is invoked, when the "eater", wants to eat this animal.
   *
   * @param eater reference to animal which has eaten the object
   */
  public boolean youAreEaten(Animal eater) {
    stop();
    boolean res = ((Snake)(eater)).eatGreenFrog(getPlace());
    return res;
  }

  /**
   * Returns number of scores to be given as a price for eating this animal.
   *
   * @return price for this object
   */
  public int price() {
    return 1;
  }
}
