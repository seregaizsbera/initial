package viewer.relationwizard.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import viewer.relationwizard.RelationWizardModel;
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
 * AddAttributeAction class is responsible for operations
 * necessary to perform when user desired to add new attribute to
 * the structure of new table
 */
public class AddAttributeAction extends AbstractAction {
  private final RelationWizardModel model;

  /**
   * Constructor for AddAttributeAction object
   *
   * @param model reference to relation wizard model
   */
  public AddAttributeAction(RelationWizardModel model) {
    super("Add attribute");
    this.model = model;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void actionPerformed(ActionEvent e) {
    model.addAttribute();
  }
}
