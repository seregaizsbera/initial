package snake.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import snake.controller.*;
import snake.model.*;
import snake.view.awtimpl.*;

/**
 * Title:        Snake
 * Description:  Snake game
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.6
 */

/**
 * SnakePanel represents the "View" entity for SnakeModel.
 * It observes changes in the model and makes visual components show them.
 */
class SnakePanel extends SPanel implements Observer {
  private final Panel buttonPanel;
  private final FieldPanel fieldPanel;
  private final ScrollPane scrollPanel;
  private final Button startButton;
  private final Button pauseButton;
  private final Button stopButton;
  private final Label scoreLabel;
  private final SnakeModel model;

  SnakePanel() {
    setLayout(new BorderLayout(0, 0));

    model = new SnakeModel();
    model.addObserver(this);

    startButton = Factory.getButtonInstance("Start");
    pauseButton = Factory.getButtonInstance("Pause");
    stopButton = Factory.getButtonInstance("Stop");
    startButton.setEnabled(true);
    stopButton.setEnabled(false);
    pauseButton.setEnabled(false);

    scoreLabel = Factory.getLabelInstance("0", Label.CENTER);

    fieldPanel = new FieldPanel();
    buttonPanel = Factory.getPanelInstance(new GridLayout());
    scrollPanel = Factory.getScrollPaneInstance(ScrollPane.SCROLLBARS_AS_NEEDED);

    fieldPanel.setField(model.getField());
    Panel temp = new Panel(new GridBagLayout());
    temp.setBackground(fieldPanel.getBackground());
    temp.setForeground(fieldPanel.getForeground());
    temp.add(fieldPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
        GridBagConstraints.CENTER,
        GridBagConstraints.NONE,
        new Insets(5, 5, 5, 5),
        0, 0));
    scrollPanel.add(temp);
    add("Center",scrollPanel);
    add("South",buttonPanel);

    startButton.addActionListener(new StartListener(model));
    pauseButton.addActionListener(new PauseListener(model));
    stopButton.addActionListener(new StopListener(model));

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(Factory.getLabelInstance("Score:", Label.RIGHT));
    buttonPanel.add(scoreLabel);

    MouseListener driveListener = new DriveListener(model);
    temp.addMouseListener(driveListener);
    fieldPanel.addMouseListener(driveListener);
  }

  void close() {
    model.stopGame();
  }

  public void update(Observable o, Object arg) {
    if(model == o) {
      switch(model.getState()) {
        case ModelState.PAUSED:
          startButton.setEnabled(false);
          stopButton.setEnabled(true);
          pauseButton.setEnabled(true);
          pauseButton.setLabel("Resume");
          break;
        case ModelState.STARTED:
          startButton.setEnabled(false);
          stopButton.setEnabled(true);
          pauseButton.setEnabled(true);
          pauseButton.setLabel("Pause");
          break;
        case ModelState.STOPPED:
        default:
          startButton.setEnabled(true);
          stopButton.setEnabled(false);
          pauseButton.setEnabled(false);
          pauseButton.setLabel("Pause");
          break;
      }
      return;
    }
    fieldPanel.repaint();
    int curScore = Integer.parseInt(scoreLabel.getText());
    if(curScore != model.getScore())
      scoreLabel.setText("" + model.getScore());
  }
}
