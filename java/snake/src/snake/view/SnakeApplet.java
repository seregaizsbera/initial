package snake.view;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
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
 * SnakeApplet is the main class for the application
 */
public class SnakeApplet extends Applet {
  static private final HashMap defaults = new HashMap();
  static {
     for(int i = 0; i < Param.SNAKE_APPLET_PARAMETERS.length; i++)
       defaults.put(Param.SNAKE_APPLET_PARAMETERS[i][0], Param.SNAKE_APPLET_PARAMETERS[i][3]);
  }
  private Button buttonPlay;

  Color getColorParameter(String name) {
    String stringValue = getStringParameter(name);
    Color res = Color.decode(stringValue);
    return res;
  }
  int getIntParameter(String name) {
    String stringValue = getStringParameter(name);
    int res = Integer.parseInt(stringValue);
    return res;
  }
  String getStringParameter(String name) {
    String res = isActive() ? getParameter(name) : null;
    if(res == null) {
      if(!defaults.containsKey(name))
        throw new CanNotFindParameterException("Can not find parameter " + name + '.');
      res = (String)defaults.get(name);
    }
    return res;
  }

  public String[][] getParameterInfo() {
    return Param.SNAKE_APPLET_PARAMETERS;
  }
  public void init() {
    super.init();
    buttonPlay = Factory.getButtonInstance("Play");
    buttonPlay.addActionListener(new PlayListener(this));
    setLayout(new GridBagLayout());
    add(buttonPlay);
  }

  public static void main(String[] args) {
    SnakeApplet applet = new SnakeApplet();
    Frame frame = Factory.getFrameInstance(applet.toString());
    frame.add(applet);
    frame.setBounds(50, 50, 240, 180);
    frame.addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                System.exit(0);
                              }
                            }
                           );
    applet.init();
    applet.start();
    frame.setVisible(true);
  }
}
