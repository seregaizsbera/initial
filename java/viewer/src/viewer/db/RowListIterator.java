package viewer.db;

import java.util.ListIterator;
import viewer.util.Util;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * RowListIterator provides methods to iterate through list of Rows
 */
public class RowListIterator implements ListIterator {
  private final ListIterator i;

  /**
   * Constructor for RowListIterator object
   *
   * @param i generic list iterator
   */
  public RowListIterator(ListIterator i) {
    this.i = i;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * get next row
   *
   * @return next row
   */
  public Row getNext() {
    return (Row)next();
  }

  /**
   * get previous row
   *
   * @return previous row
   */
  public Row getPrevious() {
    return (Row)previous();
  }

  /**
   * Replaces the last row returned by getNext() or
   * getPrevious() with the specified row
   *
   * @param row new row
   */
  public void setRow(Row row) {
    set(row);
  }

  /**
   * Inserts the specified row into the list
   *
   * @param row new row
   */
  public void addRow(Row row) {
    add(row);
  }

  /**
   * Check if there is next element
   *
   * @return true if there is next element
   */
  public boolean hasNext() {
    return i.hasNext();
  }

  /**
   * Check if there is previous element
   *
   * @return true if there is previous element
   */
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
