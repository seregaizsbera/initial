package viewer.dataeditor.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import viewer.dataeditor.DataEditorModel;
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
 * CloseAction class is responsible for operations
 * necessary to perform when data editor window is closed
 */
public class CloseAction extends WindowAdapter {
  private final DataEditorModel model;

  /**
   * Constructor for CloseAction object
   *
   * @param model the data editor model
   */
  public CloseAction(DataEditorModel model) {
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void windowClosing(WindowEvent e) {
    model.closeTable();
  }
}
