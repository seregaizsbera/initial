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
 * Every animal, which can be eaten, must implement interface Eatable
 */
public interface Eatable {
  /**
   * This method is invoked, when the "eater", wants to eat this animal.
   *
   * @param eater reference to animal which has eaten the object
   */
  boolean youAreEaten(Animal eater);

  /**
   * Returns number of scores to be given as a price for eating this animal.
   *
   * @return price for this object
   */
  int price();
}
