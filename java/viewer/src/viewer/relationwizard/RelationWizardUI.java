package viewer.relationwizard;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import viewer.db.DataSource;
import viewer.db.Attribute;
import viewer.relationwizard.controller.AddAttributeAction;
import viewer.relationwizard.controller.CancelAction;
import viewer.relationwizard.controller.OkAction;
import viewer.relationwizard.controller.TextFocusListener;
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
 * RelationWizardUI class provides user interface of relation wizard
 */
public class RelationWizardUI extends JDialog {
  private final RelationWizardModel model;
  private final JButton okButton;
  private final JButton cancelButton;
  private final JButton addAttributeButton;
  private final JTextField tableNameField;
  private final JTable structurePanel;
  private final Action okAction;
  private final Action cancelAction;
  private final Action addAttributeAction;
  private final JComboBox typeBox;

  /**
   * Constructor for RelationWizard object
   *
   * @param data reference to an object containing connection to database
   */
  public RelationWizardUI(DataSource data) {
    setModal(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setBounds(Util.centerOnScreen(360, 300));
    setTitle("Add new table");

    model = new RelationWizardModel(data);

    okAction = new OkAction(this);
    okAction.setEnabled(false);
    cancelAction = new CancelAction(this);
    addAttributeAction = new AddAttributeAction(model);

    okButton = new JButton(okAction);
    cancelButton = new JButton(cancelAction);
    addAttributeButton = new JButton(addAttributeAction);
    tableNameField = new JTextField();
    typeBox = new JComboBox(Attribute.ATTRIBUTE_TYPES.keySet().toArray());
    structurePanel = new JTable(model);
    structurePanel.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    structurePanel.getTableHeader().setReorderingAllowed(false);
    structurePanel.setColumnSelectionAllowed(false);
    structurePanel.setRowSelectionAllowed(true);
    structurePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    structurePanel.setRowHeight(structurePanel.getRowHeight() + 6);

    TableColumn typeColumn =  structurePanel.getColumnModel().getColumn(1);
    typeColumn.setCellEditor(new DefaultCellEditor(typeBox));

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
    buttonPanel.add(addAttributeButton);

    Container panel = getContentPane();
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0, 0,
        GridBagConstraints.WEST,
        GridBagConstraints.NONE,
        new Insets(10, 10, 10, 5),
        0, 0);
    panel.add(new JLabel("Table name"), c);

    c = new GridBagConstraints(1, 0, 5, 1, 0.5, 0,
                               GridBagConstraints.WEST,
                               GridBagConstraints.HORIZONTAL,
                               new Insets(10, 0, 10, 10),
                               0, 0);
    panel.add(tableNameField, c);

    c = new GridBagConstraints(0, 1, 6, 6, 1, 1,
                               GridBagConstraints.WEST,
                               GridBagConstraints.BOTH,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(new JScrollPane(structurePanel), c);

    c = new GridBagConstraints(0, 7, 6, 1, 0.5, 0,
                               GridBagConstraints.WEST,
                               GridBagConstraints.BOTH,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(buttonPanel, c);
    tableNameField.addFocusListener(new TextFocusListener(this, okAction));
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * Create new table
   */
  public void createTable() {
    try {
      model.createTable(tableNameField.getText());
    } catch(SQLException e) {
      Util.showException(e);
      return;
    }
    close();
  }

  /**
   * open window with relation wizard
   */
  public void open() {
    setVisible(true);
  }

  /**
   * close realtion wizard
   */
  public void close() {
    setVisible(false);
    tableNameField.setText("");
    model.clear();
  }

  /**
   * get name of new table
   *
   * @return table name user entered
   */
  public String getTableName() {
    return tableNameField.getText();
  }
}
