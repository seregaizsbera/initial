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
 * CancelAction class is responsible for operations
 * necessary to perform when user desired to exit from
 * relation wizard
 */
public class CancelAction extends AbstractAction {
  RelationWizardUI relationWizard;

  /**
   * constructor for CancelAction object
   *
   * @param relationWizard reference to realtion wizard
   */
  public CancelAction(RelationWizardUI relationWizard) {
    super("Cancel");
    this.relationWizard = relationWizard;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent parm1) {
    relationWizard.close();
  }
}
