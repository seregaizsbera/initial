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
import viewer.db.Attribute;
import viewer.db.AttributeList;
import viewer.db.DataSource;
import viewer.db.MyResultSetMetaData;
import viewer.db.Row;
import viewer.db.RowList;
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
  static private final String UNKNOWN_VALUE = "Unknown value";
  private final DataSource data;
  private final Action controlAction;
  private final RowList rows;
  private final AttributeList attributes;
  private String tableName;

  DataEditorModel(DataSource data) {
    this.data = data;
    controlAction = new AbstractAction() {
                      public void actionPerformed(ActionEvent e) {}
                    };
    rows = new RowList();
    attributes = new AttributeList();
  }

  private String createWhereClause(Row row) {
    String res = "";
    int columnCount = attributes.size();
    for(int i = 1; i <= columnCount; i++) {
      Attribute attribute = attributes.getAttribute(i);
      String strValue = row.getStr(i);
      if(!attribute.isSearchable() || strValue == UNKNOWN_VALUE)
        continue;
      if(!res.equals(""))
        res += " and ";
      res += tableName + "." + attribute.getName() + attribute.createSqlBooleanValue(strValue);
    }
    return res;
  }
  private String createInsertStatement(Row row) {
    String res = "";
    String fields = "";
    int columnCount = attributes.size();
    for(int i = 1; i <= columnCount; i++) {
      Attribute attribute = attributes.getAttribute(i);
      if(!attribute.isEditable()) {
        row.setStr(i, UNKNOWN_VALUE);
        continue;
      }
      if(!res.equals("")) {
        res += ", ";
        fields += ", ";
      }
      fields += attribute.getName();
      String strValue = row.getStr(i);
      res += attribute.createSqlValue(strValue);
    }
    res = "insert into " + tableName + " (" + fields + ") values (" + res + ")";
    Util.debug(res);
    return res;
  }
  private String createUpdateStatement(Row row) {
    String res = "";
    int columnCount = attributes.size();
    for(int i = 1; i <= columnCount; i++) {
      Attribute attribute = attributes.getAttribute(i);
      if(!attribute.isEditable())
        continue;
      if(!res.equals(""))
        res += ", ";
      String strValue = row.getStr(i);
      res += attribute.getName() + "=" + attribute.createSqlValue(strValue);
    }
    String where = createWhereClause(rows.getRow());
    res = "update " + tableName + " set " + res + " where " + where;
    Util.debug(res);
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
    attributes.fillWith(rsmd);
    rows.fillWith(rs);
    controlAction.putValue(NUMBER_OF_RECORDS, new Integer(getSize()));
    controlAction.putValue(NEW_RECORD_ADDED, Boolean.FALSE);
    this.tableName = tableName;
    data.releaseTable();
  }
  Attribute getAttribute(int index) {
    return attributes.getAttribute(index);
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
    return attributes.size();
  }
  void addListener(PropertyChangeListener listener) {
    controlAction.addPropertyChangeListener(listener);
  }

  /**
   * close table and cleanup all internal data
   */
  public void closeTable() {
    rows.clear();
    tableName = null;
    attributes.clear();
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
    controlAction.putValue(RECORD_POSITION, new Row(attributes.size()));
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
    int columnCount = attributes.size();
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
      String sql = "delete from " + tableName + " where " + createWhereClause(rows.getRow());
      Util.debug(sql);
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
