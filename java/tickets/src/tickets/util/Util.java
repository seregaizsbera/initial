package tickets.util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

abstract public class Util {
  static public void debug(Object o) {
    System.err.println(o);
  }

  static public void debug(String source, Object o) {
    System.err.print(source + ": ");
    System.err.println(o);
  }

  static public void debug(Throwable e) {
    e.printStackTrace();
  }

  static public boolean isEmpty(String str) {
    return str == null || str.equals("");
  }
}
