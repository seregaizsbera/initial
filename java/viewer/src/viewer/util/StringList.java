package viewer.util;

import java.util.LinkedList;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * StringList provides methods to acces list of Strings without
 * annoying castings.
 */
public class StringList extends LinkedList {

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * add string to the end of list
   *
   * @param str new string
   */
  public void addStrLast(String str) {
    super.addLast(str);
  }

  /**
   * add row to the begining of list
   *
   * @param str new string
   */
  public void addStrFirst(String str) {
    super.addFirst(str);
  }

  /**
   * get the first string
   *
   * @return the first string
   */
  public String first() {
    return (String)super.getFirst();
  }

  /**
   * get the last string
   *
   * @return the last string
   */
  public String last() {
    return (String)super.getLast();
  }

  /**
   * get iterator for this StringList object
   *
   * @return new StringListIterator
   */
  public StringListIterator getIterator() {
    return new StringListIterator(super.listIterator());
  }
}
