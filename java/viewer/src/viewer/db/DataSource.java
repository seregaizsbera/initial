package viewer.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * DataSource provides methods for manipulation with database.
 * It's the only class which uses database driver.
 */
public class DataSource {
  private Connection con;
  private Statement updateTableSt;
  private DatabaseMetaData metaData;
  private String userName;
  private Statement querySt;

  /**
   * Constructor for DataSource object
   *
   * @param driverClass class name of database driver
   * @exception ClassNotFoundException if driver class not found
   */
  public DataSource(String driverClass) throws ClassNotFoundException {
      Class.forName(driverClass);
  }

  private void cleanup() {
    updateTableSt = null;
    querySt = null;
    con = null;
  }

  protected void finalize() throws java.lang.Throwable {
    releaseQuery();
    releaseTable();
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * open connection to database
   *
   * @param url database url
   * @param user user name
   * @param passwd password
   * @exception SQLException if a database access error occurs
   */
  public void connect(String url, String user, String passwd)
      throws SQLException {
    cleanup();
    if(user != null && user.equals("")) {
      user = null;
      userName = null;
      passwd = null;
    } else
      userName = user;
    if(passwd != null && passwd.equals(""))
      passwd = null;
    con = DriverManager.getConnection(url, user, passwd);
    metaData = con.getMetaData();
  }

  /**
   * close connection to database
   */
  public void disconnect() {
    cleanup();
    metaData = null;
    userName = null;
  }

  /**
   * get list of tables owned by current user
   *
   * @return ResultSet object with necessary data
   * @exception SQLException if a database access error occurs
   */
  public ResultSet getTables() throws SQLException {
    String user = userName == null ? null : userName.toUpperCase();
    String types[] = {"TABLE"};
    return metaData.getTables(null, user, null, types);
  }

  /**
   * get data from the specified table for updating
   *
   * @param tableName name of the table
   * @return updatable ResultSet object
   * @exception SQLException if a database access error occurs
   */
  public ResultSet getTableForUpdating(String tableName) throws SQLException {
    releaseTable();
    updateTableSt = con.createStatement();
    ResultSet res = updateTableSt.executeQuery("select * from " + tableName);
    return res;
  }

  /**
   * close table got for updating
   */
  public void releaseTable() {
    try {
      if(updateTableSt != null)
        updateTableSt.close();
    }
    catch (SQLException e) {}
    finally {
      updateTableSt = null;
    }
  }

  /**
   * close query recently executed
   */
  public void releaseQuery() {
    try {
      if(querySt != null)
        querySt.close();
    }
    catch (SQLException e) {}
    finally {
      querySt = null;
    }
  }

  /**
   * get number of records in the specified table
   *
   * @param tableName name of the table
   * @return number of records
   * @exception SQLException if a database access error occurs
   */
  public int getTableSize(String tableName) throws SQLException {
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("select count(*) as COUNT from " + tableName);
    rs.next();
    int res = rs.getInt("COUNT");
    return res;
  }

  /**
   * Check if object is connected to database
   *
   * @return true if connection is established
   */
  public boolean isConnected() {
    return con != null;
  }

  /**
   * Execute SQL query ignoring its result
   *
   * @param sqlString SQL query
   * @exception SQLException if a database access error occurs
   */
  public void execSQL(String sqlString) throws SQLException {
    Statement st = con.createStatement();
    st.execute(sqlString);
  }

  /**
   * Execute SQL query and get its result
   *
   * @param sqlString SQL query
   * @return ResultSet object with result data
   * @exception SQLException if a database access error occurs
   */
  public ResultSet execQuery(String sqlString) throws SQLException {
    releaseQuery();
    try {
      querySt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    } catch(SQLException e) {
      if(e.getClass().getName().equals("org.postgresql.util.PSQLException"))
        querySt = con.createStatement();
      else
        throw e;
    }
    return querySt.executeQuery(sqlString);
  }
}
