package viewer.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * RowList provides methods to access list of Rows without annoying
 * castings and iterate through them.
 */
public class RowList extends ArrayList {
  private int currentIndex = -1;

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * initialize list with data from ResultSet object
   *
   * @param rs source ResultSet object
   * @exception SQLException if a database access error occurs
   */
  public void fillWith(ResultSet rs) throws SQLException {
    clear();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    while(rs.next()) {
      Row row = new Row(columnCount);
      for(int i = 1; i <= columnCount; i++)
        row.setStr(i, rs.getString(i));
      add(row);
    }
    first();
  }

  /**
   * add row to the end of list and make it current
   *
   * @param row new row
   */
  public void addRowLast(Row row) {
    add(row);
    currentIndex = size() - 1;
  }

  /**
   * make first row in list current, if any
   */
  public void first() {
    currentIndex = isEmpty() ? -1 : 0;
  }

  /**
   * make previous row in list current
   */
  public void previous() {
    currentIndex--;
  }

  /**
   * make next row in list current
   */
  public void next() {
    currentIndex++;
  }

  /**
   * make last row in list current
   */
  public void last() {
    currentIndex = size() - 1;
  }

  /**
   * delete current row and make previous one current
   */
  public void delete() {
    remove(currentIndex);
    if(currentIndex >= size())
      currentIndex--;
  }

  /**
   * get current row
   *
   * @return current row
   */
  public Row getRow() {
    if(currentIndex >= 0)
      return (Row)get(currentIndex);
    else
      return null;
  }

  /**
   * replace current row with new one
   *
   * @param newRow new row
   */
  public void setRow(Row newRow) {
    set(currentIndex, newRow);
  }

  /**
   * get position of current row
   *
   * @return number of current row
   */
  public int getPosition() {
    return currentIndex + 1;
  }

  /**
   * get total number of rows in list
   *
   * @return number of rows
   */
  public int getSize() {
    int s = size();
    return s;
  }

  /**
   * Check if there is next row
   *
   * @return true if there is next row
   */
  public boolean hasNext() {
    return currentIndex + 1 < size() && currentIndex >= 0;
  }

  /**
   * Check if there is previous row
   *
   * @return true if there is previous row
   */
  public boolean hasPrevious() {
    return currentIndex > 0;
  }

  /**
   * get iterator for this RowList object
   *
   * @return new RowListIterator
   */
  public RowListIterator getIterator() {
    return new RowListIterator(super.listIterator());
  }

  public void clear() {
    super.clear();
    currentIndex = -1;
  }
}
