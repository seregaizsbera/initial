package viewer.relationwizard.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Action;
import viewer.relationwizard.RelationWizardUI;
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
 * TextFocusListener class is responsible for operations
 * necessary to perform when user changes field with table name
 */
public class TextFocusListener implements FocusListener {
  private final Action action;
  private final RelationWizardUI relationWizard;

  /**
   * Constructor for TextFocusListener object
   *
   * @param relationWizard reference to relation wizard
   * @param action action to be enabled when correct text is entered
   */
  public TextFocusListener(RelationWizardUI relationWizard, Action action) {
    this.action = action;
    this.relationWizard = relationWizard;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  public void focusLost(FocusEvent e) {
    String tableName = relationWizard.getTableName();
    action.setEnabled(!tableName.equals(""));
  }

  public void focusGained(FocusEvent e) {
    action.setEnabled(true);
  }
}
