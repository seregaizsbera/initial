package snake.view;

import java.awt.*;
import java.awt.event.*;
import snake.view.awtimpl.*;
import snake.controller.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * SnakeFrame represents main game window, which contains all control buttons and game field.
 */
public class SnakeFrame extends SFrame {
  private final SnakePanel snakePanel;

  /**
   * Constructor for the SnakeFrame object
   *
   * @param applet reference to the applet
   */
  public SnakeFrame(SnakeApplet applet) {
    Param.getParameters(applet);

    int w = Param.fieldWidth;
    int h = Param.fieldHeight;
    int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
    int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
    if(w > sw || w <= 0) w = sw;
    if(h > sh || h <= 0) h = sh;
    int l = (sw - w) / 2;
    int t = (sh - h) / 2;
    setBounds(l, t, w, h);
    setResizable(true);
    setTitle(Param.fieldTitle);
    snakePanel = new SnakePanel();
    add(snakePanel);
    addWindowListener(new CloseListener(this));
  }

  /**
   * close frame
   *
   */
  public void close() {
    snakePanel.close();
    dispose();
  }
}
