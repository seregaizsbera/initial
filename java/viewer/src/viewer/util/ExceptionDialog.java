package viewer.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * ExceptionDialog provides user friendly message about errors
 */
class ExceptionDialog extends JDialog {
  private final JButton okButton;
  private final JTextArea text;

  ExceptionDialog(Exception e) {
    setModal(true);
    setResizable(true);
    setTitle(e.getClass().getName() + " has occured.");
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    setBounds(Util.centerOnScreen(450, 300));

    okButton = new JButton("OK");
    okButton.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                   dispose();
                                 }
                               }
                              );

    text = new JTextArea();
    text.setWrapStyleWord(false);
    text.setLineWrap(false);
    text.setAutoscrolls(true);
    text.setEditable(false);

    ByteArrayOutputStream writer = new ByteArrayOutputStream();
    PrintStream printer = new PrintStream(writer);
    Util.printException(e, printer);

    text.setText(writer.toString());
    text.setBackground(getBackground());
    JScrollPane pane = new JScrollPane(text);
    Container panel = getContentPane();
    panel.setLayout(new BorderLayout(10, 10));
    panel.add(pane, BorderLayout.CENTER);
    JPanel temp = new JPanel(new FlowLayout());
    temp.add(okButton);
    panel.add(temp, BorderLayout.SOUTH);
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }
}
