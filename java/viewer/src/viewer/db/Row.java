package viewer.db;

import viewer.util.Util;
import java.util.Vector;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * Row class represents a record of a table
 */
public class Row extends Vector {

  /**
   * Constructor for Row object
   *
   * @param size number of columns in table
   */
  public Row(int size) {
    super(size);
    setSize(size);
    for(int i = 0; i < size; i++)
      set(i, new String());
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * get string value of specified column
   *
   * @param index index of column
   * @return string value
   */
  public String getStr(int index) {
    return (String)super.get(index - 1);
  }

  /**
   * set string value of specified column
   *
   * @param index index of column
   * @param str new value
   */
  public void setStr(int index, String str) {
    super.set(index - 1, str);
  }

  public boolean equals(Object o) {
    return this == o;
  }
}
