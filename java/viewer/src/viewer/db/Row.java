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
   * create SQL expression representing the value of specified
   * column
   *
   * @param column number of column
   * @param columnClassName name of column Java class
   * @return string represinting value of the specified column
   *                in SQL with respect to its Java class name
   */
  public String createSqlValue(int column, String columnClassName) {
    String strValue = getStr(column);
    if(strValue == null || strValue.equals(""))
      strValue = "null";
    else if(columnClassName.equals(MyResultSetMetaData.STRING_TYPE))
      strValue = '\'' + strValue + '\'';
    return strValue;
  }

  /**
   * Create SQL expression representing condition for record to
   * contain the same value in specified column as in this Row object
   *
   * @param column number of column
   * @param columnClassName column Java class name
   * @return string representing desired expression in SQL
   */
  public String createSqlBooleanValue(int column, String columnClassName) {
    String strValue = createSqlValue(column, columnClassName);
    if(strValue.equals("null"))
      strValue = " is null";
    else
      strValue = '=' + strValue;
    return strValue;
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
    super.add(index - 1, str);
  }

  public boolean equals(Object o) {
    return this == o;
  }
}
