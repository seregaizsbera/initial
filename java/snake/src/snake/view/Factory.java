package snake.view;

import java.awt.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * Factory provides methods for creation every visual component.
 * Changing these methods is easiest way to change program's look.
 */
public class Factory {
  /**
   * create new button
   *
   * @param label the label
   * @return new button
   */
  static public Button getButtonInstance(String label) {
    return new Button(label);
  }

  /**
   * create new label
   *
   * @param label the text
   * @return new label
   */
  static public Label getLabelInstance(String label) {
    return new Label(label);
  }

  /**
   * create new label
   *
   * @param label the text
   * @param alignment text alignment
   * @return new label
   */
  static public Label getLabelInstance(String label, int alignment) {
    return new Label(label, alignment);
  }

  /**
   * create new Panel
   *
   * @return new Panel
   */
  static public Panel getPanelInstance() {
    return new Panel();
  }

  /**
   * create new panel
   *
   * @param layout layout manager for new panel
   * @return new panel
   */
  static public Panel getPanelInstance(LayoutManager layout) {
    return new Panel(layout);
  }

  /**
   * create new frame
   *
   * @return new frame
   */
  static public Frame getFrameInstance() {
    return new Frame();
  }

  /**
   * create new frame
   *
   * @param title the title
   * @return new frame
   */
  static public Frame getFrameInstance(String title) {
    return new Frame(title);
  }

  /**
   * create new ScrollPane
   *
   * @param policy policy of scrollbars appearance
   * @return new ScrollPane
   */
  static public ScrollPane getScrollPaneInstance(int policy) {
    return new ScrollPane(policy);
  }
}
