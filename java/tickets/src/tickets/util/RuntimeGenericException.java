package tickets.util;

import java.io.*;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class RuntimeGenericException extends RuntimeException implements Serializable {
  private Throwable parent;

  public RuntimeGenericException() {
    super();
    parent = null;
  }

  public RuntimeGenericException(String message) {
    super(message);
    this.parent = null;
  }

  public RuntimeGenericException(String message, Throwable parent) {
    super(message);
    this.parent = parent;
  }

  public RuntimeGenericException(Throwable parent) {
    super();
    this.parent = parent;
  }

  public final Throwable getParent() {
    return parent;
  }

  public String toString() {
    return super.toString() + ExHelper.getInstance().toString(parent);
  }

  public final void printStackTrace() {
    super.printStackTrace();
    ExHelper.getInstance().printStackTrace(parent);
  }

  public final void printStackTrace(java.io.PrintStream ps) {
    super.printStackTrace(ps);
    ExHelper.getInstance().printStackTrace(ps, parent);
  }

  public final void printStackTrace(java.io.PrintWriter pw) {
    super.printStackTrace(pw);
    ExHelper.getInstance().printStackTrace(pw, parent);
  }
}
