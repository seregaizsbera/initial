package viewer.db;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
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
 * Attribute class represents one element of table structure.
 * It's determined with three values: name, type and length. And some other
 * options as well.
 */
public class Attribute {
  static private final String VARCHAR = "VARCHAR";
  static private final String INTEGER = "INTEGER";
  static private final HashMap ATTRIBUTE_SQL_TYPES = new HashMap();

  static public final HashMap ATTRIBUTE_TYPES = new HashMap();

  static {
    ATTRIBUTE_TYPES.put(VARCHAR, new Integer(Types.VARCHAR));
    ATTRIBUTE_TYPES.put(INTEGER, new Integer(Types.INTEGER));
    for(Iterator i = ATTRIBUTE_TYPES.keySet().iterator(); i.hasNext();) {
      String key = (String)i.next();
      ATTRIBUTE_SQL_TYPES.put(ATTRIBUTE_TYPES.get(key), key);
    }
  }
  private String label = null;
  private String name = "NewAttribute";
  private String type = VARCHAR;
  private int length = 20;
  private String className = null;
  private boolean editable = true;
  private boolean searchable = true;

  /**
  * Default constructor for the Attribute object
  */
  public Attribute() {}

  /**
  * Construct the Attribute object from another one
  */
  public Attribute(Attribute a) {
    name = a.name;
    type = a.type;
    length = a.length;
    label = a.label;
    className = a.className;
    editable = a.editable;
    searchable = a.searchable;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void fillWith(ResultSetMetaData rsmd, int columnIndex) throws SQLException {
    name = rsmd.getColumnName(columnIndex);
    label = rsmd.getColumnLabel(columnIndex);
    type = (String)ATTRIBUTE_SQL_TYPES.get(new Integer(rsmd.getColumnType(columnIndex)));
    className = rsmd.getColumnClassName(columnIndex);
    length = rsmd.getColumnDisplaySize(columnIndex);
    editable = !rsmd.isReadOnly(columnIndex);
    searchable = rsmd.isSearchable(columnIndex);
  }

  /**
   * create SQL expression representing the value
   *
   * @param strValue
   * @return string represinting value of the specified column
   *                in SQL with respect to its Java class name
   */
  public String createSqlValue(String strValue) {
    if(strValue == null || strValue.equals(""))
      strValue = "null";
    else if(className.equals(MyResultSetMetaData.STRING_TYPE) ||
            className.equals(MyResultSetMetaData.DATETIME_TYPE)) {
      strValue.indexOf('\'');
      char chars[] = strValue.toCharArray();
      strValue = "";
      for(int i = 0; i < chars.length; i++) {
        strValue += chars[i];
        if(chars[i] == '\'')
          strValue += chars[i];
      }
      strValue = "'" + strValue + "'";
    }
    return strValue;
  }

  /**
   * Create SQL expression representing condition for record to
   * contain the same value in specified column as strValue
   *
   * @param strValue value from the column
   * @return string representing desired expression in SQL
   */
  public String createSqlBooleanValue(String strValue) {
    strValue = createSqlValue(strValue);
    if(strValue.equals("null"))
      strValue = " is null";
    else
      strValue = '=' + strValue;
    return strValue;
  }

  /**
  * Get the name of column described by this attribute
  *
  * @retrun column name
  */
  public String getName() {
    return name;
  }

  /**
  * Get the type of column described by this attribute
  *
  * @return integer value from java.sql.Types
  */
  public int getSQLType() {
    return ((Integer)ATTRIBUTE_TYPES.get(type)).intValue();
  }

  /**
  * Get the type of column described by this attribute
  *
  * @return String object containing name of the type
  */
  public String getType() {
    return type;
  }

  /**
  * Get the length of SQL type of column described by this attribute
  *
  * @return length of column type
  */
  public int getLength() {
    return length;
  }

  /**
  * Get label for column described by this attribute
  *
  * @return string label of the column
  */
  public String getLabel() {
    String res = label == null ? name : label;
    return res;
  }

  /**
  * Get Java class name representing data stored in column
  * described by this attribute
  *
  * @return Java class name
  */
  public String getClassName() {
    return className;
  }

  /**
  * Check whether column is updatable
  *
  * @return true if data in the column can be written manually
  */
  public boolean isEditable() {
    return editable;
  }

  /**
  * Check if column can be used in search criterias
  *
  * @return true if this column can be used in where clause
  */
  public boolean isSearchable() {
    return searchable;
  }

  /**
  * Set name of the column described by this attribute
  *
  * @param name the new name
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  * Set length of data in the column described by this attribute
  *
  * @param length new length
  */
  public void setLength(int length) {
    this.length = length;
  }

  /**
  * Set type of data in the column described by this attribute
  *
  * @param type new name of SQL type
  */
  public void setType(String typeName) {
    type = typeName;
  }
}
