package tickets.model.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import tickets.model.dao.DAOException;
import tickets.util.Param;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * SimpleConnectionSource обеспечивает получение соединения с СУБД
 * через JDBC 2.0
 */
class SimpleConnectionSource implements ConnectionSource {
  private static SimpleConnectionSource instance;
  private final Param param;
  private int counter = 0;
  private Connection connection = null;

  private SimpleConnectionSource() {
    param = Param.getInstance();
  }

  public Connection getConnection() {
    if(connection == null)
      counter = 0;
    if(counter > 0) {
      counter++;
      return connection;
    }
    try {
      Class.forName(param.getDbDriverClass());
      connection = DriverManager.getConnection(param.getDbUrl(),
                                               param.getDbUserName(),
                                               param.getDbUserPassword());
      counter++;
    }
    catch(ClassNotFoundException e) {
      Util.debug(e);
      throw new DAOException(e);
    }
    catch(SQLException e) {
      Util.debug(e);
      throw new DAOException(e);
    }
    return connection;
  }

  public void close(Connection conn) {
    if(conn != connection)
      if(conn != null)
      try {
        conn.close();
      }
      catch (SQLException sqle) {
        throw new DAOException(sqle);
      }
    if(connection == null) {
      counter = 0;
      return;
    }
    if(--counter > 0)
      return;
    try {
      connection.close();
    }
    catch (SQLException sqle) {
      throw new DAOException(sqle);
    }
  }

  /**
   * Возвращает экземпляр этого класса
   * @return экземпляр этого класса
   */
  static public SimpleConnectionSource getInstance() {
    if(instance == null)
      instance = new SimpleConnectionSource();
    return instance;
  }
}
