package viewer.navigator;

import viewer.util.Param;
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
 * JDBC Database Viewer application main class
 */
public class Viewer {
  private final Navigator navigator;

  /**
   * Constructor for Viewer object
   *
   * @param args command line arguments
   * @exception ClassNotFoundException if a database driver class not found
   */
  public Viewer(String[] args) throws ClassNotFoundException {
    Param param = new Param(args);
    Navigator temp;
    while(true)
      try {
        param.open();
        temp = new Navigator(param);
        break;
      } catch(Exception e) {
        Util.showException(e);
      }
    navigator = temp;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * Open application
   */
  public void open() {
    navigator.open();
  }

  static public void main(String[] args) {
    try {
      Viewer viewer = new Viewer(args);
      viewer.open();
    }
    catch(Exception e) {
      Util.showException(e);
      System.exit(1);
    }
  }
}
