package viewer.dataeditor.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
 * LastAction class is responsible for operations
 * necessary to perform when user desired to switch to
 * the last record of the table
 */
public class LastAction extends AbstractAction {
  private final DataEditorModel model;

  /**
   * Constructor for LastAction object
   *
   * @param model the data editor model
   */
  public LastAction(DataEditorModel model) {
    super("Last");
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.last();
  }
}
