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
 * DeleteAction class is responsible for operations
 * necessary to perform when user desired to delete a record
 */
public class DeleteAction extends AbstractAction {
  private final DataEditorModel model;

  /**
   * Constructor for DeleteAction object
   *
   * @param model data editor model
   */
  public DeleteAction(DataEditorModel model) {
    super("Delete");
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.deleteRecord();
  }
}
