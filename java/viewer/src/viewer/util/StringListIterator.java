package viewer.util;

import java.util.ListIterator;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * StringListIterator provides methods to iterate through list of Strings
 */
public class StringListIterator implements ListIterator {
  private final ListIterator i;

  /**
   * Constructor for StringListIterator object
   *
   * @param i generic list iterator
   */
  public StringListIterator(ListIterator i) {
    this.i = i;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * get next string
   *
   * @return next srting
   */
  public String getNext() {
    return (String)next();
  }

  /**
   * get previous string
   *
   * @return previous srting
   */
  public String getPrevious() {
    return (String)previous();
  }

  /**
   * Replaces the last string returned by getNext() or
   * getPrevious() with the specified string
   *
   * @param str new srting
   */
  public void setStr(String str) {
    set(str);
  }

  /**
   * Inserts the specified string into the list
   *
   * @param str new string
   */
  public void addStr(String str) {
    add(str);
  }

  public boolean hasNext() {
    return i.hasNext();
  }

  public boolean hasPrevious() {
    return i.hasPrevious();
  }

  public Object next() {
    return i.next();
  }

  public Object previous() {
    return i.previous();
  }
  public int nextIndex() {
    return i.nextIndex();
  }
  public int previousIndex() {
    return i.previousIndex();
  }

  public void remove() {
    i.remove();
  }

  public void set(Object o) {
    i.set(o);
  }

  public void add(Object o) {
    i.add(o);
  }
}
