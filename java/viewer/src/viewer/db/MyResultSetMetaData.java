package viewer.db;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
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
 * MyResultSetMetaData class perform check if given
 * ResultSetMetaData certain operations and if doesn't
 * perform some of them itself.
 */
public class MyResultSetMetaData implements ResultSetMetaData {
  private final ResultSetMetaData rsmd;
  private final boolean classNameImplemented;

  static final String STRING_TYPE = "java.lang.String";
  static final String DOUBLE_TYPE = "java.lang.Double";
  static final String OBJECT_TYPE = "java.lang.Object";
  static final String INTEGER_TYPE = "java.lang.Integer";
  static final String DATETIME_TYPE = "java.sql.Timestamp";

  /**
   * Constructor for MyResultSetMetaData object.
   *
   * @param rsmd ResultSetMetaData received from database driver
   */
  public MyResultSetMetaData(ResultSetMetaData rsmd) {
    this.rsmd = rsmd;
    boolean temp = true;
    try {
      rsmd.getColumnClassName(1);
    }
    catch(SQLException e) {
      temp = false;
    }
    catch(AbstractMethodError e) {
      temp = false;
    }
    classNameImplemented = temp;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public int getColumnCount() throws java.sql.SQLException {
    return rsmd.getColumnCount();
  }
  public boolean isAutoIncrement(int parm1) throws java.sql.SQLException {
    return rsmd.isAutoIncrement(parm1);
  }
  public boolean isCaseSensitive(int parm1) throws java.sql.SQLException {
    return rsmd.isCaseSensitive(parm1);
  }
  public boolean isSearchable(int parm1) throws java.sql.SQLException {
    return rsmd.isSearchable(parm1);
  }
  public boolean isCurrency(int parm1) throws java.sql.SQLException {
    return rsmd.isCurrency(parm1);
  }
  public int isNullable(int parm1) throws java.sql.SQLException {
    return rsmd.isNullable(parm1);
  }
  public boolean isSigned(int parm1) throws java.sql.SQLException {
    return rsmd.isSigned(parm1);
  }
  public int getColumnDisplaySize(int parm1) throws java.sql.SQLException {
    return rsmd.getColumnDisplaySize(parm1);
  }
  public String getColumnLabel(int parm1) throws java.sql.SQLException {
    return rsmd.getColumnLabel(parm1);
  }
  public String getColumnName(int parm1) throws java.sql.SQLException {
    return rsmd.getColumnName(parm1);
  }
  public String getSchemaName(int parm1) throws java.sql.SQLException {
    return rsmd.getSchemaName(parm1);
  }
  public int getPrecision(int parm1) throws java.sql.SQLException {
    return rsmd.getPrecision(parm1);
  }
  public int getScale(int parm1) throws java.sql.SQLException {
    return rsmd.getScale(parm1);
  }
  public String getTableName(int parm1) throws java.sql.SQLException {
    return rsmd.getTableName(parm1);
  }
  public String getCatalogName(int parm1) throws java.sql.SQLException {
    return rsmd.getCatalogName(parm1);
  }
  public int getColumnType(int parm1) throws java.sql.SQLException {
    return rsmd.getColumnType(parm1);
  }
  public String getColumnTypeName(int parm1) throws java.sql.SQLException {
    return rsmd.getColumnTypeName(parm1);
  }
  public boolean isReadOnly(int parm1) throws java.sql.SQLException {
    return rsmd.isReadOnly(parm1);
  }
  public boolean isWritable(int parm1) throws java.sql.SQLException {
    return rsmd.isWritable(parm1);
  }
  public boolean isDefinitelyWritable(int parm1) throws java.sql.SQLException {
    return rsmd.isDefinitelyWritable(parm1);
  }
  public String getColumnClassName(int parm1) throws java.sql.SQLException {
    if(classNameImplemented)
      return rsmd.getColumnClassName(parm1);
    int type = rsmd.getColumnType(parm1);
    switch(type){
      case Types.BIT:
      case Types.TINYINT:
      case Types.SMALLINT:
      case Types.INTEGER:
      case Types.BIGINT:
      case Types.NUMERIC:
      case Types.DECIMAL:
        return INTEGER_TYPE;
      case Types.FLOAT:
      case Types.REAL:
      case Types.DOUBLE:
        return DOUBLE_TYPE;
      case Types.CHAR:
      case Types.VARCHAR:
      case Types.LONGVARCHAR:
        return STRING_TYPE;
      case Types.JAVA_OBJECT:
        return OBJECT_TYPE;
      case Types.TIME:
      case Types.TIMESTAMP:
      case Types.DATE:
        return DATETIME_TYPE;
      case Types.BINARY:
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
      case Types.NULL:
      case Types.OTHER:
      case Types.DISTINCT:
      case Types.STRUCT:
      case Types.ARRAY:
      case Types.BLOB:
      case Types.CLOB:
      case Types.REF:
      default:
        throw new UnsupportedOperationException("Method getColumnClassName() not yet implemented.");
    }
  }
}