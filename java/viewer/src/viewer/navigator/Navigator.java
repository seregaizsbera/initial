package viewer.navigator;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import viewer.dataeditor.DataEditorUI;
import viewer.db.DataSource;
import viewer.navigator.controller.AddNewTableAction;
import viewer.navigator.controller.CancelAction;
import viewer.navigator.controller.OkAction;
import viewer.queryviewer.QueryViewerUI;
import viewer.relationwizard.RelationWizardUI;
import viewer.util.Param;
import viewer.util.StringList;
import viewer.util.StringListIterator;
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
 * Navigator class provides user interface to navaigate between
 * different components of the program
 */
public class Navigator extends JFrame {
  private final Param param;
  private final JComboBox tableChooser;
  private final JLabel label;
  private final JButton okButton;
  private final JButton cancelButton;
  private final JButton addNewTableButton;
  private final NavigatorModel model;
  private final DataSource data;
  private final Action okAction;
  private final CancelAction cancelAction;
  private final Action addNewTableAction;
  private final DataEditorUI dataEditor;
  private final QueryViewerUI queryViewer;
  private final RelationWizardUI relationWizard;

  Navigator(Param param) throws ClassNotFoundException, SQLException {
    this.param = param;
    data = new DataSource(param.getJdbcDriverName());
    model = new NavigatorModel(data);

    setTitle("Tables");
    setBounds(Util.centerOnScreen(420, 200));
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    data.connect(param.getUrl(), param.getUserName(), param.getPassword());

    tableChooser = new JComboBox();
    tableChooser.setEditable(false);
    label = new JLabel("Select table to scan");
    label.setLabelFor(tableChooser);

    okAction = new OkAction(this);
    addNewTableAction = new AddNewTableAction(this);
    cancelAction = new CancelAction(this);
    okButton = new JButton(okAction);
    cancelButton = new JButton(cancelAction);
    addNewTableButton = new JButton(addNewTableAction);
    addWindowListener(cancelAction);

    Container panel = getContentPane();
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints(0, 0, 3, 1, 0, 0,
        GridBagConstraints.WEST,
        GridBagConstraints.BOTH,
        new Insets(10, 10, 10, 10),
        0, 0);
    panel.add(label, c);

    c = new GridBagConstraints(0, 1, 3, 1, 0, 0,
                               GridBagConstraints.WEST,
                               GridBagConstraints.BOTH,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(tableChooser, c);

    c = new GridBagConstraints(0, 2, 1, 1, 0, 0,
                               GridBagConstraints.CENTER,
                               GridBagConstraints.NONE,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(okButton, c);

    c = new GridBagConstraints(1, 2, 1, 1, 0, 0,
                               GridBagConstraints.CENTER,
                               GridBagConstraints.NONE,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(cancelButton, c);

    c = new GridBagConstraints(2, 2, 1, 1, 0, 0,
                               GridBagConstraints.CENTER,
                               GridBagConstraints.NONE,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(addNewTableButton, c);

    dataEditor = new DataEditorUI(data, this);
    queryViewer = new QueryViewerUI(data);
    relationWizard = new RelationWizardUI(data);
  }

  private void readTables() {
    StringList tables = model.getTables();
    tableChooser.removeAllItems();
    int j = -1;
    for(StringListIterator i = tables.getIterator(); i.hasNext();) {
      tableChooser.insertItemAt(i.getNext(),++j);
    }
    if(j >= 0)
      tableChooser.setSelectedIndex(0);
    else
      okAction.setEnabled(false);
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void open() {
    readTables();
    setVisible(true);
  }

  /**
   * close navigator window and exit
   */
  public void close() {
      data.disconnect();
      dispose();
      System.exit(0);
  }

  /**
   * show data editor
   */
  public void showDataEditor() {
    try {
      String tableName = tableChooser.getSelectedItem().toString();
      dataEditor.open(tableName);
    }
    catch(SQLException e) {
      Util.showException(e);
    }
    System.gc();
  }

  /**
   * show relation wizard
   */
  public void showRelationWizard() {
    relationWizard.open();
    readTables();
    System.gc();
  }

  /**
   * show query viewer
   */
  public void showQueryViewer() {
    showQueryViewer(null);
  }

  /**
   * show query viewer
   *
   * @param tableNameHint name of table most likely to be included
   * in new query
   */
  public void showQueryViewer(String tableNameHint) {
    queryViewer.open(tableNameHint);
    readTables();
    System.gc();
  }
}
