package tickets.util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Util предоставляет набор функций, используемых всеми компонентами приложения
 */
abstract public class Util {
  static public void debug(Object o) {
    if(!Param.getInstance().getDebugMode())
      return;
    System.err.println(o);
  }

  static public void debug(String source, Object o) {
    if(!Param.getInstance().getDebugMode())
      return;
    System.err.print(source + ": ");
    System.err.println(o);
  }

  static public void debug(Throwable e) {
    if(!Param.getInstance().getDebugMode())
      return;
    e.printStackTrace();
  }

  static public boolean isEmpty(String str) {
    return str == null || str.equals("");
  }
}
