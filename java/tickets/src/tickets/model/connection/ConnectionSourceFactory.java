package tickets.model.connection;

import java.lang.reflect.InvocationTargetException;
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
 * ConnectionSourceFactory создает объект, реализующий интерфейс
 * ConnectionSource, в соответствии с настройками, полученными из класса Param
 */
public class ConnectionSourceFactory {
  static private ConnectionSource connectionSource = null;

  static public ConnectionSource getConnectionSource() {
    if(connectionSource == null) {
      try {
        Class args[] = new Class[0];
        Param param = Param.getInstance();
        connectionSource =
            (ConnectionSource)Class.forName(
                param.getConnectionSourceClassName())
            .getMethod("getInstance", args).invoke(null, null);
        if(connectionSource == null)
          connectionSource = SimpleConnectionSource.getInstance();
      }
      catch(ClassNotFoundException e) {
        Util.debug(e);
      }
      catch(NoSuchMethodException e) {
        Util.debug(e);
      }
      catch(InvocationTargetException e) {
        Util.debug(e);
      }
      catch(IllegalAccessException e) {
        Util.debug(e);
      }
    }
    return connectionSource;
  }
}