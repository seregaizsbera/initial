package viewer.navigator.controller;

import java.awt.event.ActionEvent;
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

public class OkAction extends AbstractAction {
  private final Navigator navigator;

  /**
   * Constructor for OkAction object
   *
   * @param navigator reference to navigator
   */
  public OkAction(Navigator navigator) {
    super("OK");
    this.navigator = navigator;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    navigator.showDataEditor();
  }
}
