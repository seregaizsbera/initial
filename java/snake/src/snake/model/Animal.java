package snake.model;

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
 * Animal is super class to animals in the game.
 * It performs all the thread stuff, so that its subclasses don't
 * care about threads.
 */
public abstract class Animal implements Runnable {
  private Thread currentThread;
  private boolean paused = false; // pause flag
  protected final Field field;    // game field

  protected Animal(Field field) {
    this.field = field;
  }

  protected abstract boolean moveTo(Place newPlace);
  protected abstract void putTo(Place place);
  protected abstract boolean step();

  protected void stop() {
    if(currentThread == null)
      return;
    Thread temp = currentThread;
    currentThread = null;
    temp.interrupt();
  }
  protected void start() {
    currentThread = new Thread(this);
    currentThread.start();
  }
  protected int delay() {
    return Param.animalDelay;
  }
  protected boolean isAlive() {
    return currentThread != null;
  }
  protected void pause() {
    if(currentThread == null)
      return;
    paused = true;
  }
  protected void resume() {
    paused = false;
  }
  protected boolean isPaused() {
    return paused;
  }

  public void run() {
    try {
      while(currentThread != null) {
        if(!step())
          stop();
        field.tellToObservers();
        if(!paused)
          Thread.sleep(delay());
        while(paused)
          Thread.sleep(100);
      }
    }
    catch(InterruptedException e) {}
  }
}
