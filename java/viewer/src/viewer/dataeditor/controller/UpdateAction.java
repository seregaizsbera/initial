package viewer.dataeditor.controller;

import java.awt.event.ActionEvent;
import java.util.Vector;
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
 * UpdateAction class is responsible for operations
 * necessary to perform when user desired to update currently
 * edited record or insert new record into the table
 */
public class UpdateAction extends AbstractAction {
  private final DataEditorModel model;
  private final Vector fields;

  /**
   * Constructor for UpdateAction object
   *
   * @param model the data editor model
   * @param fields reference to array of fields containing
   * data to be saved
   */
  public UpdateAction(DataEditorModel model, Vector fields) {
    super("Update");
    this.model = model;
    this.fields = fields;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.updateRecord(fields);
  }
}
