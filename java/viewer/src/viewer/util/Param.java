package viewer.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * Param class is responsible to parse application parameters
 */
public class Param extends JDialog {
  private final String SUN_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
  private final String IBM_DRIVER = "COM.ibm.db2.jdbc.app.DB2Driver";
  private final String POSTGRES_DRIVER = "org.postgresql.Driver";
  private String DEFAULT_DRIVER = SUN_DRIVER;
  private String DEFAULT_USER = "db2student8";
  private String DEFAULT_URL = "jdbc:odbc:B8";
  private String DEFAULT_PASSWORD = "8";
  private final JButton okButton;
  private final JButton cancelButton;
  private final JButton resetButton;
  private final JTextField urlField;
  private final JTextField userNameField;
  private final JPasswordField passwordField;
  private final JComboBox driverNameBox;
  private String jdbcDriverName;
  private String url;
  private String userName;
  private String password;

  private static void printObject(Object o) {
    System.out.print(o.getClass().getName() + ": '" + o.toString() +"'");
  }

  /**
   * Constructor for Param object
   *
   * @param args command line parameters
   */
  public Param(String args[]) {
    readParameters(args);
    jdbcDriverName = DEFAULT_DRIVER;
    url = DEFAULT_URL;
    userName = DEFAULT_USER;
    password = DEFAULT_PASSWORD;
    setResizable(false);
    setModal(true);
    setBounds(Util.centerOnScreen(400, 250));
    setTitle("Connect parameters");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    urlField = new JTextField(url, 25);
    userNameField = new JTextField(userName, 25);
    passwordField = new JPasswordField(password, 25);
    driverNameBox = new JComboBox();
    driverNameBox.setEditable(true);
    driverNameBox.addItem(SUN_DRIVER);
    driverNameBox.addItem(IBM_DRIVER);
    driverNameBox.addItem(POSTGRES_DRIVER);
    driverNameBox.setSelectedIndex(-1);
    driverNameBox.getEditor().setItem(jdbcDriverName);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        cancelPressed();
      }
    });
    okButton = new JButton(new AbstractAction("OK") {
      public void actionPerformed(ActionEvent e) {
        okPressed();
      }
    });
    cancelButton = new JButton(new AbstractAction("Cancel") {
      public void actionPerformed(ActionEvent e) {
        cancelPressed();
      }
    });
    resetButton = new JButton(new AbstractAction("Reset") {
      public void actionPerformed(ActionEvent e) {
        resetPressed();
      }
    });

    JComponent panel = (JComponent)getContentPane();

    InputMap inputMap = okButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed");
    inputMap.put(KeyStroke.getKeyStroke("released ENTER"), "released");
    inputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(KeyStroke.getKeyStroke("pressed ESCAPE"), "pressed");
    inputMap.put(KeyStroke.getKeyStroke("released ESCAPE"), "released");
    inputMap.put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed");
    inputMap.put(KeyStroke.getKeyStroke("released ENTER"), "released");

    panel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0);
    panel.add(new JLabel("URL"), c);
    c = new GridBagConstraints(1, 0, 2, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0);
    panel.add(urlField, c);

    c = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0);
    panel.add(new JLabel("User"), c);
    c = new GridBagConstraints(1, 1, 2, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0);
    panel.add(userNameField, c);

    c = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0);
    panel.add(new JLabel("Password"), c);
    c = new GridBagConstraints(1, 2, 2, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0);
    panel.add(passwordField, c);

    c = new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 10, 10, 5), 0, 0);
    panel.add(new JLabel("Driver"), c);
    c = new GridBagConstraints(1, 3, 2, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(10, 0, 10, 10), 0, 0);
    panel.add(driverNameBox, c);

    JPanel temp = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    temp.add(okButton);
    temp.add(cancelButton);
    temp.add(resetButton);

    c = new GridBagConstraints(0, 4, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0);
    panel.add(temp, c);
  }

  private void okPressed() {
    jdbcDriverName = driverNameBox.getEditor().getItem().toString();
    url = urlField.getText();
    userName = userNameField.getText();
    password = String.valueOf(passwordField.getPassword());
    dispose();
  }

  private void cancelPressed() {
    System.exit(0);
  }

  private void resetPressed() {
    jdbcDriverName = DEFAULT_DRIVER;
    url = DEFAULT_URL;
    userName = DEFAULT_USER;
    password = DEFAULT_PASSWORD;
    driverNameBox.setSelectedIndex(-1);
    driverNameBox.getEditor().setItem(jdbcDriverName);
    urlField.setText(url);
    userNameField.setText(userName);
    passwordField.setText(password);
  }

  private void readParameters(String args[]) {
    int n = args.length;
    if(n > 3)
      DEFAULT_DRIVER = args[3];
    if(n > 2)
      DEFAULT_PASSWORD = args[2];
    if(n > 1)
      DEFAULT_USER = args[1];
    if(n > 0)
      DEFAULT_URL = args[0];
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() + ')');
    super.finalize();
  }

  /**
   * Get JDBC driver name
   *
   * @return class name of jdbc driver
   */
  public String getJdbcDriverName() {
    return jdbcDriverName;
  }

  /**
   * get database url
   *
   * @return database url
   */
  public String getUrl() {
    return url;
  }

  /**
   * get user name
   *
   * @return user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * get password
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Open window to confirm parameters
   */
  public void open() {
    setVisible(true);
  }
}
