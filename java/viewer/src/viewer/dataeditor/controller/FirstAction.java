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
 * FirstAction class is responsible for operations
 * necessary to perform when user desired to switch to
 * the 1st record of the table
 */
public class FirstAction extends AbstractAction {
  private final DataEditorModel model;

  /**
   * Constructor for FirstAction object
   *
   * @param model the data editor model
   */
  public FirstAction(DataEditorModel model) {
    super("First");
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.first();
  }
}
