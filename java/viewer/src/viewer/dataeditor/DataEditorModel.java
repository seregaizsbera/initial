package viewer.dataeditor;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import viewer.dataeditor.controller.ControlProperties;
import viewer.db.DataSource;
import viewer.db.MyResultSetMetaData;
import viewer.db.Row;
import viewer.db.RowList;
import viewer.util.StringList;
import viewer.util.StringListIterator;
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
 * DataEditorModel class contains methods to perform high-level
 * operations under records of a table.
 */
public class DataEditorModel implements ControlProperties {
  private final DataSource data;
  private final Action controlAction;
  private final RowList rows;
  private final StringList columnHeaders;
  private final StringList columnClassNames;
  private String tableName;
  private int columnCount;

  DataEditorModel(DataSource data) {
    this.data = data;
    controlAction = new AbstractAction() {
                      public void actionPerformed(ActionEvent e) {}
                    };
    rows = new RowList();
    columnHeaders = new StringList();
    columnClassNames = new StringList();
  }

  private String createWhereClause() {
    String res = "";
    Row row = rows.getRow();
    StringListIterator i = columnHeaders.getIterator();
    StringListIterator j = columnClassNames.getIterator();
    int k = 0;
    while(i.hasNext()) {
      String columnName = i.getNext();
      String columnClassName = j.getNext();
      k++;
      String strValue = row.createSqlBooleanValue(k, columnClassName);
      res += columnName + strValue;
      if(i.hasNext())
        res += " and ";
    }
    return res;
  }
  private String createInsertStatement(Row newRow) {
    String res = "insert into " + tableName + " values (";
    StringListIterator i = columnClassNames.getIterator();
    int j = 0;
    while(i.hasNext()) {
      String columnClassName = i.getNext();
      j++;
      String strValue = newRow.createSqlValue(j, columnClassName);
      res += strValue;
      if(i.hasNext())
        res += ',';
    }
    res += ')';
    return res;
  }
  private String createUpdateStatement(Row newRow) {
    String res = "update " + tableName + " set ";
    StringListIterator i = columnHeaders.getIterator();
    StringListIterator j = columnClassNames.getIterator();
    int k = 0;
    while(i.hasNext()) {
      String columnName = i.getNext();
      String columnClassName = j.getNext();
      k++;
      String strValue = newRow.createSqlValue(k, columnClassName);
      res += columnName + "=" + strValue;
      if(i.hasNext())
        res += ',';
    }
    res += " where " + createWhereClause();
    return res;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void openTable(String tableName) throws SQLException {
    closeTable();
    ResultSet rs = data.getTableForUpdating(tableName);
    ResultSetMetaData rsmd = new MyResultSetMetaData(rs.getMetaData());
    columnCount = rsmd.getColumnCount();
    for(int i = 1; i <= columnCount; i++) {
      columnHeaders.addStrLast(rsmd.getColumnLabel(i));
      columnClassNames.addStrLast(rsmd.getColumnClassName(i));
    }
    rows.fillWith(rs);
    controlAction.putValue(NUMBER_OF_RECORDS, new Integer(getSize()));
    controlAction.putValue(NEW_RECORD_ADDED, Boolean.FALSE);
    this.tableName = tableName;
    data.releaseTable();
  }
  StringList getStructure() {
    return columnHeaders;
  }
  boolean hasPrevious() {
    return rows.hasPrevious();
  }
  boolean hasNext() {
    return rows.hasNext();
  }
  boolean isEmpty() {
    return rows.isEmpty();
  }
  int getPosition() {
    return rows.getPosition();
  }
  int getSize() {
    return rows.getSize();
  }
  int getColumnCount() {
    return columnCount;
  }
  void addListener(PropertyChangeListener listener) {
    controlAction.addPropertyChangeListener(listener);
  }

  /**
   * close table and cleanup all internal data
   */
  public void closeTable() {
    rows.clear();
    columnHeaders.clear();
    columnClassNames.clear();
    tableName = null;
    columnCount = 0;
  }

  /**
   * switch to the first record
   */
  public void first() {
    rows.first();
    controlAction.putValue(RECORD_POSITION, rows.getRow());
  }

  /**
   * switch to previous record
   */
  public void previous() {
    rows.previous();
    controlAction.putValue(RECORD_POSITION, rows.getRow());
  }

  /**
   * switch to next record
   */
  public void next() {
    rows.next();
    controlAction.putValue(RECORD_POSITION, rows.getRow());
  }

  /**
   * switch to the last record
   */
  public void last() {
    rows.last();
    controlAction.putValue(RECORD_POSITION, rows.getRow());
  }

  /**
   * create new record
   */
  public void newRecord() {
    rows.addRowLast(null);
    controlAction.putValue(NUMBER_OF_RECORDS, new Integer(getSize()));
    controlAction.putValue(RECORD_POSITION, new Row(columnCount));
    controlAction.putValue(NEW_RECORD_ADDED, Boolean.TRUE);
  }

  /**
   * insert new record or update current record
   *
   * @param fields array of JTextArea to get data from them
   */
  public void updateRecord(Vector fields) {
    boolean newRecord = ((Boolean)controlAction.getValue(NEW_RECORD_ADDED)).booleanValue();
    String sql;
    Row newRow = new Row(columnCount);
    for(int i = 1; i <= columnCount; i++)
      newRow.setStr(i, ((JTextArea)fields.get(i - 1)).getText());
    if(newRecord)
      sql = createInsertStatement(newRow);
    else
      sql = createUpdateStatement(newRow);
    try {
      data.execSQL(sql);
    } catch(SQLException e) {
      Util.showException(e);
      return;
    }
    rows.setRow(newRow);
    controlAction.putValue(NEW_RECORD_ADDED, Boolean.FALSE);
  }

  /**
   * delete current record
   */
  public void deleteRecord() {
    boolean newRecord = ((Boolean)controlAction.getValue(NEW_RECORD_ADDED)).booleanValue();
    if(!newRecord) {
      String sql = "delete from " + tableName + " where " + createWhereClause();
      try {
        data.execSQL(sql);
      } catch(SQLException e) {
        Util.showException(e);
        return;
      }
    }
    rows.delete();
    controlAction.putValue(NUMBER_OF_RECORDS, new Integer(getSize()));
    controlAction.putValue(RECORD_POSITION, rows.getRow());
    controlAction.putValue(NEW_RECORD_ADDED, Boolean.FALSE);
  }

  /**
   * get table name
   *
   * @return table name
   */
  public String getTableName() {
    return tableName;
  }
}
