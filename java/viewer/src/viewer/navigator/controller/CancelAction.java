package viewer.navigator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import viewer.navigator.Navigator;
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
 * CancelAction class is responsible for operations
 * necessary to perform when user desired to exit from the application
 */
public class CancelAction extends AbstractAction implements WindowListener {
  private final Navigator navigator;

  /**
   * Constructor for CancelAction object
   *
   * @param navigator reference to navigator
   */
  public CancelAction(Navigator navigator) {
    super("Cancel");
    this.navigator = navigator;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    navigator.close();
  }

  public void windowClosing(WindowEvent e) {
    navigator.close();
  }

  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
}
