package viewer.queryviewer;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import viewer.db.DataSource;
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
 * QueryViewerUI class provides user interface of query viewer
 */
public class QueryViewerUI extends JDialog {
  private final JTextArea queryField;
  private final JTable resultPanel;
  private final JButton runButton;
  private final QueryViewerModel model;
  private final QueryController queryController;

  /**
   * Constructor for QueryViewerUI object
   *
   * @param data reference to an object containing connection
   * to database
   */
  public QueryViewerUI(DataSource data) {
    setModal(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setBounds(Util.centerOnScreen(640, 480));
    setTitle("Query viewer");

    model = new QueryViewerModel(data);
    queryField = new JTextArea(4, 60);
    queryField.setLineWrap(true);
    queryField.setWrapStyleWord(true);
    queryField.setAutoscrolls(true);
    queryController = new QueryController(model, this);
    resultPanel = new JTable(model);
    resultPanel.getTableHeader().setReorderingAllowed(true);
    resultPanel.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    resultPanel.setColumnSelectionAllowed(false);
    resultPanel.setRowSelectionAllowed(true);
    resultPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    runButton = new JButton(queryController);
    addWindowListener(queryController);

    Container panel = getContentPane();
    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0, 0,
        GridBagConstraints.SOUTHWEST,
        GridBagConstraints.NONE,
        new Insets(10, 10, 0, 10),
        0, 0);
    panel.add(new JLabel("Query"), c);

    c = new GridBagConstraints(0, 1, 8, 4, 1, 0.5,
                               GridBagConstraints.WEST,
                               GridBagConstraints.BOTH,
                               new Insets(5, 10, 10, 10),
                               0, 0);
    panel.add(new JScrollPane(queryField), c);

    c = new GridBagConstraints(8, 4, 1, 1, 0, 0,
                               GridBagConstraints.SOUTHWEST,
                               GridBagConstraints.NONE,
                               new Insets(10, 10, 10, 10),
                               0, 0);
    panel.add(runButton, c);

    c = new GridBagConstraints(0, 5, 1, 1, 0, 0,
                               GridBagConstraints.SOUTHWEST,
                               GridBagConstraints.NONE,
                               new Insets(10, 10, 0, 10),
                               0, 0);
    panel.add(new JLabel("Result"), c);

    c = new GridBagConstraints(0, 6, 10, 10, 1, 1,
                               GridBagConstraints.WEST,
                               GridBagConstraints.BOTH,
                               new Insets(5, 10, 10, 10),
                               0, 0);
    panel.add(new JScrollPane(resultPanel), c);
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void close() {
    model.closeQuery();
    queryField.setText("");
  }
  String getQuery() {
    return queryField.getText();
  }

  /**
   * Open window with query viewer
   *
   * @param tableNameHint name of table most likely to be included
   * in new query
   */
  public void open(String tableNameHint) {
    if(tableNameHint != null && !tableNameHint.equals(""))
      queryField.setText("select * from " + tableNameHint);
    else
      queryField.setText("");
    setVisible(true);
  }
}
