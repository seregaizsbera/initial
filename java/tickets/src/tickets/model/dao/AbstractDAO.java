package tickets.model.dao;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import tickets.model.dao.ConnectionSource;
import tickets.util.Util;
import tickets.model.valueobjects.Currency;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * ”тилитарный класс дл€ от котогорого удобно наследовать
 * DAO модули. ѕредоставл€ет сервис получени€ соединений к базе данных с
 * помощью ConnectionSource, которым конфигурируетс€ в конструкторе. ѕо
 * умолчанию используетс€ имплементаци€ ConnectionSource которую возвращает
 * ContainerConnectionSource.getInstance().
 */
abstract class AbstractDAO {
  private ConnectionSource connectionSource = ContainerConnectionSource.getInstance();

  protected boolean printClosingErrors = true;

  protected AbstractDAO() {}

  protected AbstractDAO(ConnectionSource connectionSource) {
    this.connectionSource = connectionSource;
  }

  protected Connection getConnection() throws DAOException {
    return connectionSource.getConnection();
  }

  protected static final Timestamp getTimestamp(ResultSet rs, String fieldName)
      throws SQLException {
    return DAOUtil.getTimestamp(rs, fieldName);
  }

  protected static final Timestamp getTimestamp(ResultSet rs, int index)
      throws SQLException {
    return DAOUtil.getTimestamp(rs, index);
  }

  protected static final java.util.Date getTime(ResultSet rs, int index)
      throws SQLException {
    return DAOUtil.getTime(rs, index);
  }

  protected static final java.util.Date getDate(ResultSet rs, int index)
      throws SQLException {
    return DAOUtil.getDate(rs, index);
  }

  protected static final java.util.Date getDate(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    return DAOUtil.getDate(rs, fieldName);
  }

  protected static final InputStream getStream(
      ResultSet rs,
      String fieldName)
      throws
  SQLException,
  DAOException {
    return DAOUtil.getStream(rs, fieldName);
  }

  protected static final String getString(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    return DAOUtil.getString(rs, fieldName);
  }

  protected static final String getString(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getString(rs, index);
  }

  protected static final String getCharString(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getCharString(rs, index);
  }

  protected static final String getCharString(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    return DAOUtil.getCharString(rs, fieldName);
  }

  protected static final BigDecimal getBigDecimal(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    return DAOUtil.getBigDecimal(rs, fieldName);
  }

  protected static final BigDecimal getBigDecimal(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getBigDecimal(rs, index);
  }

  protected static final Integer getInt(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getInt(rs, index);
  }

  protected static final Integer getInt(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    return DAOUtil.getInt(rs, fieldName);
  }

  protected static final Long getLong(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getLong(rs, index);
  }

  protected static final Float getFloat(
      ResultSet rs,
      int index)
      throws
  SQLException {
    return DAOUtil.getFloat(rs, index);
  }

  protected static final Float getFloat(ResultSet rs, String str) throws SQLException {
    return DAOUtil.getFloat(rs, str);
  }

  protected void log(String str) {
    Util.debug(str);
  }

  protected void log(Exception e) {
    e.printStackTrace();
  }

  protected boolean close(ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
      return true;
    } catch (SQLException sqle) {
      if (printClosingErrors) {
        log("Exception closing result set: " + sqle);
        log(sqle);
      }
      return false;
    }
  }

  protected boolean close(Statement stmt) {
    try {
      if (stmt != null) {
        stmt.close();
      }
      return true;
    } catch (SQLException sqle) {
      if (printClosingErrors) {
        log("Exception closing statement: " + sqle);
        log(sqle);
      }
      return false;
    }
  }

  protected boolean close(Connection conn) {
    try {
      connectionSource.close(conn);
      return true;
    } catch (DAOException e) {
      if (printClosingErrors) {
        log("Exception closing connection: " + e);
        log(e);
      }
      return false;
    }
  }

  protected static String makeSqlStringValue(String str) {
    if(str == null || str.equals(""))
      str = "null";
    char chars[] = str.toCharArray();
    String result = "";
    for(int i = 0; i < chars.length; i++) {
      result += chars[i];
      if(chars[i] == '\'')
        result += chars[i];
    }
    result = "'" + result + "'";
    return result;
  }

  /**
   * Create SQL expression representing condition for record to
   * contain the same value in specified column as strValue
   *
   * @param str value from the column
   * @return string representing desired expression in SQL
   */
  public String makeSqlBooleanValue(String str) {
    str = makeSqlStringValue(str);
    if(str.equals("null"))
      str = " is null";
    else
      str = '=' + str;
    return str;
  }
  protected static final void setString(
          PreparedStatement pstmt,
          int index,
          String val)
      throws
          SQLException {
      DAOUtil.setString(pstmt, index, val);
  }

  protected static final void setBigDecimal(
          PreparedStatement pstmt,
          int index,
          BigDecimal val)
      throws
          SQLException {
      DAOUtil.setBigDecimal(pstmt, index, val);
  }

  protected static final void setInt(
          PreparedStatement pstmt,
          int index,
          Integer val)
      throws
          SQLException {
      DAOUtil.setInt(pstmt, index, val);
  }

  protected static final void setFloat(
          PreparedStatement pstmt,
          int index,
          Float val)
          throws
          SQLException {
      DAOUtil.setFloat(pstmt, index, val);
  }

  protected static final void setCurrency(
          PreparedStatement pstmt,
          int index,
          Currency val)
          throws
          SQLException {
      DAOUtil.setCurrency(pstmt, index, val);
  }

  protected static final void setTimestamp(
          PreparedStatement pstmt,
          int index,
          java.util.Date val)
      throws
          SQLException {
      DAOUtil.setTimestamp(pstmt, index, val);
  }

  protected static final void setTime(
          PreparedStatement pstmt,
          int index,
          java.util.Date val)
      throws
          SQLException {
      DAOUtil.setTime(pstmt, index, val);
  }

  protected static final void setDate(
          PreparedStatement pstmt,
          int index,
          java.util.Date val)
      throws
          SQLException {
      DAOUtil.setDate(pstmt, index, val);
  }
}
