package viewer.relationwizard.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import viewer.relationwizard.RelationWizardUI;
import viewer.util.Util;

/**
 * <p>Title: Viewer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * OkAction class is responsible for operations
 * necessary to perform when user desired to create
 * new table with composed structure
 */
public class OkAction extends AbstractAction {
  private final RelationWizardUI relationWizard;

  /**
   * Constructor for OkAction object
   *
   * @param relationWizard reference to relation wizard
   */
  public OkAction(RelationWizardUI relationWizard) {
    super("OK");
    this.relationWizard = relationWizard;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    relationWizard.createTable();
  }
}

