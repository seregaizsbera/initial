package snake.model;

import java.util.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * PlaceList provides methods to acces list of Places without
 * annoying castings.
 */
public class PlaceList extends LinkedList {
  /**
   * default constructor for the PlaceList object
   */
  public PlaceList() {}

  /**
   * get the first place
   *
   * @return reference to the first place
   */
  public Place first() {
    return (Place)super.getFirst();
  }

  /**
   * get the last place
   *
   * @return reference to the last place
   */
  public Place last() {
    return (Place)super.getLast();
  }

  /**
   * get the place at specified index
   *
   * @return reference to the place
   */
  public Place at(int index) {
    return (Place)super.get(index);
  }
}
