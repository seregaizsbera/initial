package viewer.dataeditor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import viewer.dataeditor.controller.CloseAction;
import viewer.dataeditor.controller.ControlProperties;
import viewer.dataeditor.controller.DeleteAction;
import viewer.dataeditor.controller.FirstAction;
import viewer.dataeditor.controller.LastAction;
import viewer.dataeditor.controller.NewAction;
import viewer.dataeditor.controller.NextAction;
import viewer.dataeditor.controller.PrevAction;
import viewer.dataeditor.controller.QueryAction;
import viewer.dataeditor.controller.UpdateAction;
import viewer.db.DataSource;
import viewer.db.Row;
import viewer.navigator.Navigator;
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
 * DataEditorUI class provides user interface of data editor
 */
public class DataEditorUI extends JDialog
                          implements PropertyChangeListener, ControlProperties {
  private final int ROWS_IN_FIELD = 3;
  private String tableName;
  private final Vector labels;
  private final Vector fields;
  private final JButton firstButton;
  private final JButton prevButton;
  private final JButton nextButton;
  private final JButton lastButton;
  private final JButton updateButton;
  private final JButton deleteButton;
  private final JButton newButton;
  private final JButton queryButton;
  private final Action firstAction;
  private final Action prevAction;
  private final Action nextAction;
  private final Action lastAction;
  private final Action updateAction;
  private final Action deleteAction;
  private final Action newAction;
  private final Action queryAction;
  private final DataEditorModel model;
  private final JPanel columnsPanel;
  private final JLabel positionLabel;
  private final JLabel sizeLabel;

  /**
   * Constructor for DataEditor object
   *
   * @param data reference to an object, holding connection to database
   * @param navigator reference to navigator object
   */
  public DataEditorUI(DataSource data, Navigator navigator) {
    setModal(true);
    setBounds(Util.centerOnScreen(640, 480));
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(false);

    model = new DataEditorModel(data);

    labels = new Vector();
    fields = new Vector();

    firstAction = new FirstAction(model);
    prevAction = new PrevAction(model);
    nextAction = new NextAction(model);
    lastAction = new LastAction(model);
    updateAction = new UpdateAction(model, fields);
    newAction = new NewAction(model);
    deleteAction = new DeleteAction(model);
    queryAction = new QueryAction(model, navigator);

    model.addListener(this);

    firstButton = new JButton(firstAction);
    prevButton = new JButton(prevAction);
    nextButton = new JButton(nextAction);
    lastButton = new JButton(lastAction);
    updateButton = new JButton(updateAction);
    newButton = new JButton(newAction);
    deleteButton = new JButton(deleteAction);
    queryButton = new JButton(queryAction);
    addWindowListener(new CloseAction(model));

    positionLabel = new JLabel("XXXX", JLabel.CENTER);
    sizeLabel = new JLabel("XXXX", JLabel.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout());
    columnsPanel = new JPanel(new GridLayout(1, 1, 15, 15));

    buttonPanel.add(firstButton);
    buttonPanel.add(prevButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(lastButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(newButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(queryButton);

    Container panel = getContentPane();
    panel.setLayout(new BorderLayout());
    panel.add(buttonPanel, BorderLayout.NORTH);
    panel.add(new JScrollPane(columnsPanel), BorderLayout.CENTER);

    JPanel labelPanel = new JPanel(new FlowLayout());
    labelPanel.add(new JLabel("Record "));
    labelPanel.add(positionLabel);
    labelPanel.add(new JLabel(" of "));
    labelPanel.add(sizeLabel);

    panel.add(labelPanel, BorderLayout.SOUTH);
  }

  private void unsetTable() {
    labels.clear();
    fields.clear();
    columnsPanel.removeAll();
  }
  private void setTable(String tableName) throws SQLException {
    unsetTable();
    this.tableName = tableName;
    setTitle("Table - " + tableName);
    model.openTable(tableName);
    StringList columns = model.getStructure();
    columnsPanel.setLayout(new GridBagLayout());
    int size = columns.size();
    labels.setSize(size);
    fields.setSize(size);
    GridBagConstraints cl = new GridBagConstraints(0, 0, 1, 1, 0, 0,
        GridBagConstraints.NORTHEAST,
        GridBagConstraints.NONE,
        new Insets(10, 10, 10, 5),
        0, 0);
    GridBagConstraints cf = new GridBagConstraints(0, 0, 3, ROWS_IN_FIELD, 1, 0,
        GridBagConstraints.NORTHWEST,
        GridBagConstraints.HORIZONTAL,
        new Insets(10, 5, 10, 10),
        0, 0);
    int j = 0;
    for(StringListIterator i = columns.getIterator(); i.hasNext(); j++) {
      String column = i.getNext();
      JLabel label = new JLabel(column);
      JTextArea field = new JTextArea();
      field.setRows(ROWS_IN_FIELD);
      field.setColumns(15);
      field.setLineWrap(true);
      field.setWrapStyleWord(false);
      field.setAutoscrolls(true);
      labels.set(j, label);
      fields.set(j, field);
      GridBagConstraints cl1 = (GridBagConstraints)cl.clone();
      cl1.gridx = (j % 2) * 4;
      cl1.gridy = (j / 2) * ROWS_IN_FIELD;
      columnsPanel.add(label, cl1);
      GridBagConstraints cf1 = (GridBagConstraints)cf.clone();
      cf1.gridx = (j % 2) * 4 + 1;
      cf1.gridy = (j / 2) * ROWS_IN_FIELD;
      columnsPanel.add(new JScrollPane(field,
                                       JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
                                       cf1);
    }
    model.first();
  }
  private void adjustButtons() {
    boolean isThereRecords = !model.isEmpty();
    firstAction.setEnabled(isThereRecords);
    lastAction.setEnabled(isThereRecords);
    nextAction.setEnabled(model.hasNext());
    prevAction.setEnabled(model.hasPrevious());
    updateAction.setEnabled(isThereRecords);
    deleteAction.setEnabled(isThereRecords);
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * open window with data editor
   *
   * @param tableName name of table to be edited
   * @exception SQLException if a database access error occurs
   */
  public void open(String tableName) throws SQLException {
    setTable(tableName);
    show();
  }

  public void propertyChange(PropertyChangeEvent e) {
    String property = e.getPropertyName();
    if(property == NUMBER_OF_RECORDS) {
        adjustButtons();
        sizeLabel.setText(e.getNewValue().toString());
    } else if(property == RECORD_POSITION) {
      nextAction.setEnabled(model.hasNext());
      prevAction.setEnabled(model.hasPrevious());
      positionLabel.setText("" + model.getPosition());
      Row row = (Row)e.getNewValue();
      if(row == null)
        for(int i = 0; i < fields.size(); i++) {
          ((JTextArea)fields.get(i)).setEnabled(false);
          ((JTextArea)fields.get(i)).setText("");
        }
      else
        for(int i = 0; i < fields.size(); i++) {
          ((JTextArea)fields.get(i)).setEnabled(true);
          ((JTextArea)fields.get(i)).setText(row.getStr(i + 1));
        }
    } else if(property == NEW_RECORD_ADDED) {
      boolean newRecord = ((Boolean)e.getNewValue()).booleanValue();
      newAction.setEnabled(!newRecord);
      if(newRecord) {
        firstAction.setEnabled(false);
        lastAction.setEnabled(false);
        nextAction.setEnabled(false);
        prevAction.setEnabled(false);
        updateAction.setEnabled(true);
        deleteAction.setEnabled(true);
      } else
        adjustButtons();
    }
  }
}
