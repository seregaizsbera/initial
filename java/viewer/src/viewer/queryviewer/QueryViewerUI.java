package viewer.queryviewer;

import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
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
    Font originalFont = queryField.getFont();
    Font newFont = originalFont.deriveFont(originalFont.getSize() + 6.0f);
    queryField.setFont(newFont);
    queryController = new QueryController(model, this);
    resultPanel = new JTable(model);
    resultPanel.getTableHeader().setReorderingAllowed(true);
    resultPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    resultPanel.setColumnSelectionAllowed(false);
    resultPanel.setRowSelectionAllowed(true);
    resultPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    resultPanel.setToolTipText(null);
    runButton = new JButton(queryController);
    addWindowListener(queryController);
    addComponentListener(queryController);

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
  void adjustColumns() {
    TableColumnModel columnModel = resultPanel.getColumnModel();
    Font font = resultPanel.getFont();
    FontMetrics fontMetrics = resultPanel.getFontMetrics(font);
    int columnCount = resultPanel.getColumnCount();
    for(int i = 0; i < columnCount; i++) {
      TableColumn column = columnModel.getColumn(i);
      column.setMinWidth(fontMetrics.stringWidth(model.getColumnName(i)) / 2);
      column.setPreferredWidth(fontMetrics.stringWidth(model.getColumnName(i)) * 2);
    }
    adjustWholeTable();
  }
  void adjustWholeTable() {
    resultPanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    Container parent = resultPanel.getParent();
    parent.doLayout();
    int w = resultPanel.getWidth();
    int wp = parent.getWidth();
    if(w < wp / 2)
      resultPanel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
