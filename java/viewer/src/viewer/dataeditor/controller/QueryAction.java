package viewer.dataeditor.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import viewer.dataeditor.DataEditorModel;
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
 * QueryAction class is responsible for operations
 * necessary to perform when user desired to switch to
 * query viewer
 */
public class QueryAction extends AbstractAction {
  private final DataEditorModel model;
  private final Navigator navigator;

  /**
   * Constructor for QueryAction object
   *
   * @param model the data editor model
   * @param navigator navigator object
   */
  public QueryAction(DataEditorModel model, Navigator navigator) {
    super("Query");
    this.model = model;
    this.navigator = navigator;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    navigator.showQueryViewer(model.getTableName());
  }
}
