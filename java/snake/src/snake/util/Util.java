package snake.util;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Util provides some useful functions.
 */
public abstract class Util {
  /**
   * get random integer number within specified range
   *
   * @param min left border of the range
   * @param max right border of the range
   * @return random integer not less than <code>min</code> and
   *         less than <code>max</code>
   */
  static public int random(int min, int max) {
    int res =((int)(Math.random() * (max - min)) + min);
    return res;
  }


  /**
   * get random positive integer number not larger than specified
   *
   * @param max right border of the range
   * @return random integer not larger than <code>max</code>
   */
  static public int random(int max) {
    return random(0, max);
  }
}
