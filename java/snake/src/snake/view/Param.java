package snake.view;

import java.awt.Color;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * class Param contains all applet's paramters as static fields.
 */
public abstract class Param {
  static String SNAKE_APPLET_PARAMETERS[][] = {
    {"fieldTitle",     "string",   "Title to be shown in frame header.", "Snake the Game!"},
    {"fieldWidth",     "320-1024", "Width of the applet's frame",        "640"},
    {"fieldHeight",    "240-768",  "Height of the applet's frame",       "480"},
    {"hCellSize",      "5-100",    "hSize of cells in the game field",   "25" },
    {"vCellSize",      "5-100",    "vSize of cells in the game field",   "25" },
    {"hCells",         "10-100",   "Number of cells along horizontal",   "23" },
    {"vCells",         "10-100",   "Number of cells along vertical",     "15" },
    {"hSpace",         "1-10",     "Horizontal space between cells",     "1"  },
    {"vSpace",         "1-10",     "Vertical space between cells",       "1"  },
    {"background",     "Color",    "Applet's background color",          "0x8F8F8F"},
    {"foreground",     "Color",    "Applet's foreground color",          "0x8F8F8F"},
    {"numFrogs",       "0-100",    "Number of Frogs",                    "5"},
    {"minSnakeLength", "3-10",     "Minimal length of the snake",        "5"},
    {"maxSnakeLength", "3-100",    "Maximal length of the snake",        "75"},
    {"animalDelay",    "10-10000", "Delay between animals' steps",       "1500"},
    {"snakeDelay",     "10-10000", "Delay between snake's steps",        "500"},
    {"blueFrogLife",   "10-1000",  "How long blue frogs live in sec/10", "95"},
  };
  static public String fieldTitle;
  static public int fieldWidth;
  static public int fieldHeight;
  static public int hCellSize;
  static public int vCellSize;
  static public int hCells;
  static public int vCells;
  static public int hSpace;
  static public int vSpace;
  static public Color background;
  static public Color foreground;
  static public int numFrogs;
  static public int minSnakeLength;
  static public int maxSnakeLength;
  static public int animalDelay;
  static public int snakeDelay;
  static public int blueFrogLife;

  /**
   * Get every parameter value from the applet.
   *
   * @param applet reference to the apllet
   */
  static public void getParameters(SnakeApplet applet) {
    fieldTitle = applet.getStringParameter("fieldTitle");
    fieldWidth = applet.getIntParameter("fieldWidth");
    fieldHeight = applet.getIntParameter("fieldHeight");
    hCellSize = applet.getIntParameter("hCellSize");
    vCellSize = applet.getIntParameter("vCellSize");
    hCells = applet.getIntParameter("hCells");
    vCells = applet.getIntParameter("vCells");
    hSpace = applet.getIntParameter("hSpace");
    vSpace = applet.getIntParameter("vSpace");
    background = applet.getColorParameter("background");
    foreground = applet.getColorParameter("foreground");
    numFrogs = applet.getIntParameter("numFrogs");
    minSnakeLength = applet.getIntParameter("minSnakeLength");
    maxSnakeLength = applet.getIntParameter("maxSnakeLength");
    animalDelay = applet.getIntParameter("animalDelay");
    snakeDelay = applet.getIntParameter("snakeDelay");
    blueFrogLife = applet.getIntParameter("blueFrogLife");
  }
}

class CanNotFindParameterException extends RuntimeException {
  CanNotFindParameterException(String s) { super(s); }
}
