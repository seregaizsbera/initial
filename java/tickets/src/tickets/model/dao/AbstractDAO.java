package tickets.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import tickets.model.connection.ConnectionSource;
import tickets.model.connection.ConnectionSourceFactory;
import tickets.model.valueobjects.Currency;
import tickets.util.Util;

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
 * помощью ConnectionSource, которым конфигурируетс€ в конструкторе.
 */
abstract class AbstractDAO {
  private ConnectionSource connectionSource =
      ConnectionSourceFactory.getConnectionSource();

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

  protected static final Float getFloat(ResultSet rs, String str)
      throws SQLException {
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
