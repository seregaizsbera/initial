package viewer.queryviewer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import viewer.db.DataSource;
import viewer.util.Util;
import javax.swing.table.*;

/**
 * <p>Title: Viewer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * QueryViewerModel class manipulates data necessary for navigator
 */
class QueryViewerModel extends AbstractTableModel {
  private final int SQL_TYPE_SELECT = 1;
  private final int SQL_TYPE_OTHER = 2;
  private ResultSet rs;
  private ResultSetMetaData rsmd;
  private String resultText;
  private final DataSource data;
  private int columnCount;
  private int rowCount;
  private int lastRow;
  private int lastColumn;
  private String lastValue;

  QueryViewerModel(DataSource data) {
    this.data = data;
  }

  private int getSqlQueryType(String sql) {
    sql=sql.trim();
    String str = sql.substring(0, 6);
    if(str.toUpperCase().equals("SELECT"))
      return SQL_TYPE_SELECT;
    return SQL_TYPE_OTHER;
  }
  private void openGenericQuery(String sql) {
    resultText = "SQL query performed successfully";
    columnCount = 1;
    rowCount = 1;
    try {
      data.execSQL(sql);
    } catch(SQLException e) {
      Util.showException(e);
      resultText = e.toString().trim();
    }
  }
  private void openSelectQuery(String sql) {
    try {
      rs = data.execQuery(sql);
      rsmd = rs.getMetaData();
      columnCount = rsmd.getColumnCount();
      rs.last();
      rowCount = rs.getRow();
      rs.first();
    } catch(SQLException e) {
      Util.showException(e);
      rs = null;
      rsmd = null;
      rowCount = 1;
      columnCount = 1;
      resultText = e.toString().trim();
      return;
    }
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void openQuery(String sql) {
    closeQuery();
    int sqlType = getSqlQueryType(sql);
    switch(sqlType) {
      case SQL_TYPE_SELECT:
        openSelectQuery(sql);
        break;
      case SQL_TYPE_OTHER:
      default:
        openGenericQuery(sql);
        break;
    }
    fireTableStructureChanged();
  }
  void closeQuery() {
    rs = null;
    rsmd = null;
    rowCount = 0;
    columnCount = 0;
    resultText = null;
    data.releaseQuery();
    fireTableStructureChanged();
    lastColumn = -1;
    lastRow = -1;
    lastValue = null;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public Object getValueAt(int row, int column) {
    if(rs != null && lastRow == row && lastColumn == column)
      return lastValue;
    lastRow = row;
    lastColumn = column;
    String res = null;
    if(rs == null)
      return resultText;
    try {
      while(rs.getRow() < row + 1)
        rs.next();
      while(rs.getRow() > row + 1)
        rs.previous();
      res = rs.getString(column + 1);
    } catch(SQLException e) {
      Util.showException(e);
    }
    lastValue = res;
    return res;
  }

  public int getRowCount() {
    return rowCount;
  }

  public String getColumnName(int index) {
    if(rsmd == null)
      return "Result";
    else
      try {
        return rsmd.getColumnLabel(index + 1);
      } catch(SQLException e) {
        Util.showException(e);
        return null;
      }
  }

  public Class getColumnClass(int columnIndex) {
    return "".getClass();
  }
}
