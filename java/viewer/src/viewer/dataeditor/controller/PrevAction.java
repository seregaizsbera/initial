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
 * PrevAction class is responsible for operations
 * necessary to perform when user desired to switch to
 * the previous record of the table
 */
public class PrevAction extends AbstractAction {
  private final DataEditorModel model;

  /**
   * Constructor for PrevAction object
   *
   * @param model the data editor model
   */
  public PrevAction(DataEditorModel model) {
    super("Prev");
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.previous();
  }
}
