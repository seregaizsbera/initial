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
 * Celllist provides methods to acces list of Cells without annoying
 * castings.
 */
class CellList extends LinkedList {
  CellList() {}
  CellList(Collection c) {
    super(c);
  }

  Cell first() {
    return (Cell)super.getFirst();
  }
  Cell last() {
    return (Cell)super.getLast();
  }
}
