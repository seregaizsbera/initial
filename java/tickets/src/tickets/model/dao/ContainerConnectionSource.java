package tickets.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
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
 * @author SergeyLo
 * @date 07.08.2002
 * @time 12:40:10
 */
public class ContainerConnectionSource implements ConnectionSource {
  private static ContainerConnectionSource instance;
  private final Param param;
  private int counter = 0;
  private Connection connection = null;

  private ContainerConnectionSource() {
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
      connection = DriverManager.getConnection(param.getDbUrl(), param.getDbUserName(), param.getDbUserPassword());
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
   * ¬озвращает экземпл€р этого класса
   * @return экземпл€р этого класса
   */
  public static ContainerConnectionSource getInstance() {
    if(instance == null)
      instance = new ContainerConnectionSource();
    return instance;
  }
}
