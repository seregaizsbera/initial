package snake.controller;

import java.awt.*;
import java.awt.event.*;
import snake.view.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * PlayListener listens to the "Play" button
 */
public class PlayListener implements ActionListener {
  private final SnakeApplet applet;
  private Frame frame;

  /**
   * Constructor for the PlayListener object
   *
   * @param frame the reference to applet
   */
  public PlayListener(SnakeApplet applet) {
    this.applet = applet;
  }

  public void actionPerformed(ActionEvent e) {
    if(frame == null)
      frame = new SnakeFrame(applet);
    frame.setVisible(true);
    frame.toFront();
  }
}
