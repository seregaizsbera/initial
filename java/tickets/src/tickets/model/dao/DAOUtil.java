package tickets.model.dao;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
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
 * DAOUtil содержит утильный статические методы дл€ работы с базой.
 */
abstract public class DAOUtil {

  final public static void log(String str) {
    Util.debug(str);
  }

  final public static void log(Exception e) {
    Util.debug(e);
  }


  public static final String convertString(String s) {
    if (s.indexOf('\'') != -1) {
      StringBuffer buf = new StringBuffer();
      StringTokenizer tok = new StringTokenizer(s, "\'");
      while (tok.hasMoreTokens()) {
        buf.append(tok.nextToken());
        buf.append("''");
      }
      return buf.toString();
    } else {
      return s;
    }
  }

    /*«амен€ет в строке символы '*' на '%' и '?' на '_'*/
  public static final String convertSearchString(String str,
      boolean isLikeSearch) {

    StringBuffer result;

    result = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {

      if (str.charAt(i) == '*') {
        result.append('%');
      } else if (str.charAt(i) == '?') {
        result.append('_');
      } else if (str.charAt(i) == '\'') {
        result.append("''");
      } else if (str.charAt(i) == '%') {
        if (isLikeSearch) {
          result.append("\\%");
        } else {
          result.append(str.charAt(i));
        }
      } else if (str.charAt(i) == '_') {
        if (isLikeSearch) {
          result.append("\\_");
        } else {
          result.append(str.charAt(i));
        }
      } else if (str.charAt(i) == '\\') {
        if (isLikeSearch) {
          result.append("\\\\");
        } else {
          result.append(str.charAt(i));
        }
      } else {
        result.append(str.charAt(i));
      }
    }

    return result.toString();
  }

  final public static boolean close(ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
      return true;
    } catch (SQLException sqle) {
      log("Exception closing result set: " + sqle);
      log(sqle);
      return false;
    }
  }

  final public static boolean close(Statement stmt) {
    try {
      if (stmt != null) {
        stmt.close();
      }
      return true;
    } catch (SQLException sqle) {
      log("Exception closing statement: " + sqle);
      log(sqle);
      return false;
    }
  }

  final public static boolean close(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
      return true;
    } catch (SQLException sqle) {
      log("Exception closing connection: " + sqle);
      log(sqle);
      return false;
    }
  }

  private static BigDecimal constraintPrecisionOfBigDecimalValue(BigDecimal val) {
    int MAXIMUM_ALLOWED_PRECISION_IN_DATABASE = 38;
    if (val.toString().length() > MAXIMUM_ALLOWED_PRECISION_IN_DATABASE) {
      return new BigDecimal(val.toString().substring(1, MAXIMUM_ALLOWED_PRECISION_IN_DATABASE));
    } else {
      return val;
    }

  }

  final public static BigDecimal getBigDecimal(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    BigDecimal value = rs.getBigDecimal(fieldName);
    if (rs.wasNull()) {
      value = null;
    }
    return value;
  }

  final public static BigDecimal getBigDecimal(
      ResultSet rs,
      int index)
      throws
  SQLException {
    BigDecimal value = rs.getBigDecimal(index);
    if (rs.wasNull()) {
      value = null;
    }
    return value;
  }

  final public static Timestamp getTimestamp(ResultSet rs, String fieldName) throws SQLException {
    Timestamp value = rs.getTimestamp(fieldName);
    if (rs.wasNull())
      return null;
    else
      return value;
  }

  final public static Timestamp getTimestamp(ResultSet rs, int index) throws SQLException {
    Timestamp value = rs.getTimestamp(index);
    if (rs.wasNull())
      return null;
    else
      return value;
  }

  final public static java.util.Date getDate(
      ResultSet rs,
      int index)
      throws
  SQLException {
    java.util.Date value = rs.getDate(index);
    if (rs.wasNull()) {
      return null;
    } else {
      return value;
    }
  }

  final public static java.util.Date getDate(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    java.util.Date value = rs.getDate(fieldName);
    if (rs.wasNull()) {
      return null;
    } else {
      return value;
    }
  }

  final public static java.util.Date getTime(
      ResultSet rs,
      int index)
      throws
  SQLException {
    java.util.Date value = rs.getTime(index);
    if (rs.wasNull()) {
      return null;
    } else {
      return value;
    }
  }

  final public static InputStream getStream(
      ResultSet rs,
      String fieldName)
      throws
  SQLException,
  DAOException {
    InputStream stream = rs.getBinaryStream(fieldName);
    if (rs.wasNull()) {
      return null;
    } else {
      return stream;
    }
  }

  final public static InputStream getStream(
      ResultSet rs,
      int index)
      throws
  SQLException,
  DAOException {
    InputStream value = rs.getBinaryStream(index);
    if (rs.wasNull()) {
      return null;
    } else {
      return value;
    }
  }

  final public static String getString(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    String value = rs.getString(fieldName);
    if (rs.wasNull()) {
      value = null;
    }
    return value;
  }

  final public static String getString(
      ResultSet rs,
      int index)
      throws
  SQLException {
    String value = rs.getString(index);
    if (rs.wasNull()) {
      value = null;
    }
    return value;
  }

  final public static String getCharString(
      ResultSet rs,
      int index)
      throws
  SQLException {
    String s = getString(rs, index);
    return s != null ? s.trim() : null;
  }

  final public static String getCharString(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    String s = getString(rs, fieldName);
    return s != null ? s.trim() : null;
  }

  final public static Integer getInt(
      ResultSet rs,
      int index)
      throws
  SQLException {
    int value = rs.getInt(index);
    if (rs.wasNull()) {
      return null;
    }
    return new Integer(value);
  }

  final public static Integer getInt(
      ResultSet rs,
      String fieldName)
      throws
  SQLException {
    int value = rs.getInt(fieldName);
    if (rs.wasNull()) {
      return null;
    }
    return new Integer(value);
  }

  final public static Long getLong(
      ResultSet rs,
      int index)
      throws
  SQLException {
    long value = rs.getLong(index);
    if (rs.wasNull()) {
      return null;
    }
    return new Long(value);
  }

  final public static Float getFloat(
      ResultSet rs,
      int index)
      throws
  SQLException {
    float value = rs.getFloat(index);
    if (rs.wasNull()) {
      return null;
    }
    return new Float(value);
  }

  final public static Float getFloat(ResultSet rs, String str) throws SQLException {
    float value = rs.getFloat(str);
    if (rs.wasNull()) {
      return null;
    }
    return new Float(value);
  }

  public final static BigDecimal getCurrentId(Connection conn, String table) throws SQLException {
    BigDecimal result = null;
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select identity_val_local() from " + table);
      if (rs.next()) {
        result = getBigDecimal(rs, 1);
      }
      return result;
    } finally {
      close(stmt);
    }
  }

  public final static BigDecimal getCurrentId(Connection conn, String schema, String table) throws SQLException {
    BigDecimal result = null;
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select identity_val_local() from " + schema + "." + table);
      if (rs.next()) {
        result = getBigDecimal(rs, 1);
      }
      return result;
    } finally {
      close(stmt);
    }
  }

  final public static void setString(
      PreparedStatement pstmt,
      int index,
      String val)
      throws
  SQLException {
    if (val != null) {
      pstmt.setString(index, val);
    } else {
      pstmt.setNull(index, Types.VARCHAR);
    }
  }

  final public static void setBigDecimal(
      PreparedStatement pstmt,
      int index,
      BigDecimal val)
      throws
  SQLException {
    if (val != null) {
      pstmt.setBigDecimal(index, constraintPrecisionOfBigDecimalValue(val));
    } else {
      pstmt.setNull(index, Types.DECIMAL);
    }
  }
  final public static void setInt(
              PreparedStatement pstmt,
              int index,
              Integer val)
          throws
              SQLException {
      if (val != null) {
          pstmt.setInt(index, val.intValue());
      } else {
          pstmt.setNull(index, Types.INTEGER);
      }
  }

  final public static void setFloat(
              PreparedStatement pstmt,
              int index,
              Float val)
          throws
              SQLException {
      if (val != null) {
          pstmt.setFloat(index, val.floatValue());
      } else {
          pstmt.setNull(index, Types.FLOAT);
      }
  }

  final public static void setCurrency(
              PreparedStatement pstmt,
              int index,
              Currency val)
          throws
              SQLException {
      //setBigDecimal(pstmt, index, (val != null) ? val.getCurrency() : null);
              //val.f
      if (val != null) {
                      pstmt.setFloat(index, new Float("" + val.getIntegerValue() + "." + val.getFractionalValue()).floatValue());
      } else {
          pstmt.setNull(index, Types.FLOAT);
      }
   }
   final public static void setTimestamp(
               PreparedStatement pstmt,
               int index,
               java.util.Date val)
           throws
               SQLException {
       if (val != null) {
           pstmt.setTimestamp(index, new java.sql.Timestamp(val.getTime()));
       } else {
           pstmt.setNull(index, Types.TIMESTAMP);
       }
   }

   final public static void setDate(
               PreparedStatement pstmt,
               int index,
               java.util.Date val)
           throws
               SQLException {
       if (val != null) {
           pstmt.setDate(index, new java.sql.Date(val.getTime()));
       } else {
           pstmt.setNull(index, Types.DATE);
       }
   }

   final public static void setTime(
               PreparedStatement pstmt,
               int index,
               java.util.Date val)
           throws
               SQLException {
       if (val != null) {
           pstmt.setTime(index, new java.sql.Time(val.getTime()));
       } else {
           pstmt.setNull(index, Types.TIME);
       }
   }
}
