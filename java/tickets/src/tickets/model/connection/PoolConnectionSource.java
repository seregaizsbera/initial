package tickets.model.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
 * PoolConnectionSource создает соединение с СУБД, получая его из пула
 * соединений
 */
class PoolConnectionSource implements ConnectionSource {
  private static PoolConnectionSource instance;
  private final Param param;
  private DataSource dataSource = null;

  private PoolConnectionSource() {
    param = Param.getInstance();
  }

  protected DataSource getDataSource() throws DAOException {
    if(dataSource == null)
      try {
        Context ctx = new InitialContext();
        dataSource = (DataSource)ctx.lookup(param.getDataSourceJndiName());
      } catch (NamingException e) {
        throw new DAOException(e);
      }
    return dataSource;
  }

  public Connection getConnection() {
    try {
      return getDataSource().getConnection();
    }
    catch(SQLException e) {
      Util.debug(e);
      throw new DAOException(e);
    }
  }

  public void close(Connection conn) {
    if(conn != null)
      try {
        conn.close();
      }
      catch (SQLException sqle) {
        throw new DAOException(sqle);
      }
  }

  /**
   * Возвращает экземпляр этого класса
   * @return экземпляр этого класса
   */
  static public PoolConnectionSource getInstance() {
    if(instance == null)
      instance = new PoolConnectionSource();
    return instance;
  }
}
